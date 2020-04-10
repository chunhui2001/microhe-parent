package com.microhe.stacks.starter.action;

import java.time.Duration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.microhe.stacks.starter.domain.Quote;
import com.microhe.stacks.starter.handler.QuoteGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/quotes2")
public class QuoteRestful {


    private final Flux<Quote> quoteStream;

    public QuoteRestful(QuoteGenerator quoteGenerator) {
        this.quoteStream = quoteGenerator.fetchQuoteStream(Duration.ofMillis(1000)).share();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Mono<ServerResponse> streamQuotes() {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(this.quoteStream, Quote.class);
    }

}
