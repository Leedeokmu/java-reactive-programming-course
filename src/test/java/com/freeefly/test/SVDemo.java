package com.freeefly.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class SVDemo {

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey("user")) {
                return Mono.just("Welcome " + contextView.get("user"));
            } else {
                return Mono.error(new RuntimeException("unauthenticated"));
            }
        });
    }

    @Test
    void testExpectNext() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        StepVerifier.create(just)
            .expectNext(1, 2, 3)
            .verifyComplete()
        ;
    }

    @Test
    void testExpectError() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> error = Flux.error(new RuntimeException("oops"));
        Flux<Integer> concat = just.concatWith(error);

        StepVerifier.create(concat)
            .expectNext(1, 2, 3)
            .verifyError(RuntimeException.class)
//            .verifyErrorMatches(e -> {
//                if (!(e instanceof RuntimeException)) {
//                    return false;
//                }
//                if (!e.getMessage().equals("oops")) {
//                    return false;
//                }
//                return true;
//            })
        ;
    }

    @Test
    void testNullContext() {
        StepVerifier.create(getWelcomeMessage())
            .verifyError(RuntimeException.class)
        ;
    }

    @Test
    void testContext() {
        StepVerifierOptions option = StepVerifierOptions.create()
            .withInitialContext(Context.of("user", "sam"));
        StepVerifier.create(getWelcomeMessage(), option)
            .expectNext("Welcome sam")
            .verifyComplete()
        ;
    }
}
