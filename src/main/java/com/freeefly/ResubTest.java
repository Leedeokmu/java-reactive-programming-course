package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class ResubTest {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    public static void main(String[] args) {
//        /**
//         * repeat
//         */
//        getIntegers()
//                .repeat(2)
//                .subscribe(Util.subscriber());
        /**
         * retry
         */
        getIntegers()
                .retryWhen(Retry.from(
                        retrySignalFlux -> {
                            retrySignalFlux.handle((retrySignal, synchronousSink) -> {

                            })
                        }
                ))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(30);
    }

    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("--Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println("--error"))
                ;
    }

}
