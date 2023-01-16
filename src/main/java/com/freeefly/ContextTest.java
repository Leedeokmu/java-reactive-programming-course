package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class ContextTest {

    public static void main(String[] args) {
        getWelcomeMessage()
            .subscribe(Util.subscriber());

    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey("user")) {
                return Mono.just("Welcome " + contextView.get("user"));
            } else {
                return Mono.error(new RuntimeException("unauthenticated"));
            }
        });
    }

}
