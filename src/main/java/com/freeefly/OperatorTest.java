package com.freeefly;

import com.rp.courseutil.Util;
import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OperatorTest {

    public static void main(String[] args) {
        Flux.range(1, 10)
            .log()
            .delayElements(Duration.ofSeconds(1))
            .timeout(Duration.ofSeconds(2))
            .subscribe(Util.subscriber())
        ;
        Util.sleepSeconds(60);
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(1, 10));
    }

}
