package com.microhe.stacks.starter.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.microhe.stacks.starter.handler.QuoteHandler;

@Configuration
public class AppRouter {
    @Bean
    public RouterFunction<ServerResponse> routeCity(QuoteHandler quoteHandler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/quotes")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)),
                quoteHandler::streamQuotes);
    }
}
