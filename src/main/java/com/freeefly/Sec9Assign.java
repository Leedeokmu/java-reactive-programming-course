package com.freeefly;

import com.rp.courseutil.Util;
import com.rp.sec09.assignment.OrderProcessor;
import com.rp.sec09.assignment.OrderService;
import com.rp.sec09.assignment.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Sec9Assign {
    public static void main(String[] args) {
        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
                "Kids", OrderProcessor.kidsProcessing(),
                "Automotive", OrderProcessor.automotiveProcessing()
        );

        OrderService.orderStream()
                .filter(p -> map.containsKey(p.getCategory()))
                .groupBy(PurchaseOrder::getCategory) // 2 keys
                .flatMap(gf -> map.get(gf.key()).apply(gf))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }
}
