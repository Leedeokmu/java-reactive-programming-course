package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FluxTest {
    public static void main(String[] args) {
//        Flux<Integer> just = Flux.just(1,2,3,4);
//        just.subscribe(Util.onNext());
//        Flux<Integer> integerFlux = Flux.fromIterable(List.of(1, 2, 3));
//        integerFlux.subscribe(Util.onNext());
        Flux<Integer> streamFlux = Flux.fromStream(Stream.of(1, 2, 3));

        streamFlux.subscribe(Util.onNext());
        streamFlux.subscribe(Util.onNext());
    }
}
