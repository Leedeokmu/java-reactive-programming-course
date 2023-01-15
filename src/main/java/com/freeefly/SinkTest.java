package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkTest {
    public static void main(String[] args) {
//        /**
//         * Sink.many - unicast
//         */
//        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
//        Flux<String> flux = sink.asFlux();
//        flux.subscribe(Util.subscriber("sam"));
//
//        sink.tryEmitNext("hi");
//        sink.tryEmitNext("how are you");
//        sink.tryEmitNext("?");
        /**
         * Sink.many - multicast
         */
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<String> flux = sink.asFlux();
        flux.subscribe(Util.subscriber("sam"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

    }
}
