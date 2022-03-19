package io.github.tinycal918.apigateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpResponse fullResponse) {
        fullResponse.headers().set("kk", "huang");
    }
}
