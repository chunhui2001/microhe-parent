package com.microhe.stacks.starter.handler;

import java.time.Duration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.microhe.stacks.starter.domain.Quote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class QuoteHandler {

    private final Flux<Quote> quoteStream;

    public QuoteHandler(QuoteGenerator quoteGenerator) {
        this.quoteStream = quoteGenerator.fetchQuoteStream(Duration.ofMillis(1000)).share();
    }

    @SuppressWarnings("deprecation")
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello Spring!"));
    }

    public Mono<ServerResponse> echo(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(request.bodyToMono(String.class), String.class);
    }

    public Mono<ServerResponse> streamQuotes(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(this.quoteStream, Quote.class);
    }

    public Mono<ServerResponse> fetchQuotes(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.quoteStream.take(size), Quote.class);
    }

}
