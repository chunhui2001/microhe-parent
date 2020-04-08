package com.microhe.stacks.starter.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AppHandler {
    @SuppressWarnings("deprecation")
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .header("Content-Type", "text/plain; charset=utf-8")
                .body(BodyInserters.fromObject("Hello, City! 张春辉"));
    }
}
