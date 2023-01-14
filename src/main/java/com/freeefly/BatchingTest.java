package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BatchingTest {
    public static void main(String[] args) {
        //        /**
        //         * buffer
        //         */
        //        eventStream()
        //                .buffer(5)
        //                .subscribe(Util.subscriber());
        //        /**
        //         * window
        //         */
        //        eventStream()
        //                .window(5)
        //                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> String.format("event %s", i));
    }
}
