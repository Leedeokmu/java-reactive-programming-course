package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackpressureTest {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
//        /**
//         * Drop strategy
//         */
//        Flux.create(fluxSink -> {
//                    for (int i = 1; i < 201; i++) {
//                        fluxSink.next(i);
//                        System.out.println("Pushed : " + i);
//                        Util.sleepMillis(1);
//                    }
//                    fluxSink.complete();
//                })
//                .onBackpressureDrop()
//                .publishOn(Schedulers.boundedElastic())
//                .doOnNext(i -> Util.sleepMillis(10))
//                .subscribe(Util.subscriber())
//        ;
//        /**
//         * Latest strategy
//         */
//        Flux.create(fluxSink -> {
//                    for (int i = 1; i < 201; i++) {
//                        fluxSink.next(i);
//                        System.out.println("Pushed : " + i);
//                        Util.sleepMillis(1);
//                    }
//                    fluxSink.complete();
//                })
//                .onBackpressureLatest()
//                .publishOn(Schedulers.boundedElastic())
//                .doOnNext(i -> Util.sleepMillis(10))
//                .subscribe(Util.subscriber());
        /**
         * buffer with size
         */
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed : " + i);
                        Util.sleepMillis(1);
                    }
                    fluxSink.complete();
                })
                .onBackpressureBuffer(100)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(30);
    }
}
