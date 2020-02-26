package com.staxrt.tutorial.service;

import com.staxrt.tutorial.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Component
public class GreetingHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public Mono<User> getUser(String name) {
        return Mono.just(new User("Jonas")).flatMap(s -> {

            MDC.put("getUser", name);
            log.info("public Mono<User> getUser()");
            return Mono.subscriberContext().map(c -> {
                return new User(s + " " + c.getOrDefault("context", "no_data"));
            });
        }).subscriberContext(Context.of("context", "context_data "+ System.currentTimeMillis()));
    }
}
