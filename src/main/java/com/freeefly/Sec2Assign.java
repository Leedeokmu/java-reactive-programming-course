package com.freeefly;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Sec2Assign {
    public static Flux<Integer> getPrice() {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> atomicInteger.getAndAccumulate(Util.faker().random().nextInt(-5, 5), Integer::sum));
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        getPrice().subscribeWith(new Subscriber<Integer>() {
            private Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer price) {
                System.out.println(String.format("%s : Price : %s", LocalDateTime.now(), price));
                if (price < 90 || 110 < price) {
                    this.subscription.cancel();
                    latch.countDown();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                latch.countDown();
            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        });
        latch.await();

    }
}
