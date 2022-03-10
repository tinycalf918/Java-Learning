package com.example.nio.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

public class HttpHandler extends ChannelInboundHandlerAdapter {

     @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            if(uri.contains("/test")){
                handlerTest(fullRequest, ctx, "hello, test");
            }else{
                handlerTest(fullRequest, ctx, "hello, others");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String body) {
        FullHttpResponse fullResponse = null;
        try {
            String value = body;
            fullResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            fullResponse.headers().set("Content-Type", "application/json");
            fullResponse.headers().setInt("Content-Length", fullResponse.content().readableBytes());
        } catch (Exception e) {
            System.out.println("出现错误：" + e.getMessage());
            fullResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            if(fullResponse != null){
                if(!HttpUtil.isKeepAlive(fullRequest)){
                    ctx.write(fullResponse).addListener(ChannelFutureListener.CLOSE);
                }else{
                    fullResponse.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullResponse);
                }
                ctx.flush();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
