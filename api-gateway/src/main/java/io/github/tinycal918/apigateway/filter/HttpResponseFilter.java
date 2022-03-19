package io.github.tinycal918.apigateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpResponseFilter {
    void filter(FullHttpResponse fullResponse);
}
