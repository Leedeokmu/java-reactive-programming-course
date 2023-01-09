package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class MonoSupplier {
    public static void main(String[] args) {
        Mono<String> mono = Mono.fromSupplier(() -> getName());
        mono.subscribe(System.out::println);
    }

    private static String getName() {
        System.out.println("Generating name...");
        return Util.faker().name().fullName();
    }
}
