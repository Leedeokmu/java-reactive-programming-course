package com.freeefly;

import com.rp.courseutil.DefaultSubscriber;
import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FluxTest {
    public static void main(String[] args) {
        /**
         * just
         */
        //        Flux<Integer> just = Flux.just(1,2,3,4);
        //        just.subscribe(Util.onNext());
        /**
         * fromIterable
         */
        //        Flux<Integer> integerFlux = Flux.fromIterable(List.of(1, 2, 3));
        //        integerFlux.subscribe(Util.onNext());
        /**
         * fromStream
         */
        //        Flux<Integer> streamFlux = Flux.fromStream(Stream.of(1, 2, 3));
        //        streamFlux.subscribe(Util.onNext());
        /**
         * create
         */
        Flux<Object> sink = Flux.create(fluxSink -> {
            while (true) {
                String name = Util.faker().country().name();
                fluxSink.next(name);
                if (name.equals("India")) {
                    fluxSink.complete();
                }
            }
        });
        sink.subscribe(Util.subscriber());
        /**
         * generate
         */
        Flux<Object> sink2 = Flux.generate(synchronousSink -> {
            while (true) {
                String name = Util.faker().country().name();
                synchronousSink.next(name);
                if (name.equals("India")) {
                    synchronousSink.complete();
                }
            }
        });
        sink2.subscribe(Util.subscriber());

    }
}
