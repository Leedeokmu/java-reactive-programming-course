package com.freeefly;

import reactor.core.publisher.Mono;

import java.util.List;

public class MonoJust {
    public static void main(String[] args) {
        Mono<List<Integer>> just = Mono.just(List.of(1,2,3));
        just.subscribe();

    }
}
