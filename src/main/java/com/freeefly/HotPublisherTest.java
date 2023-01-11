package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublisherTest {
    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(0);

        Util.sleepSeconds(2);
        movieStream.subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(4);
        movieStream.subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(60);
    }


    private static Stream<String> getMovie() {
        System.out.println("got the movie streaming request");
        return Stream.of("Scene 1", "Scene 2", "Scene 3", "Scene 4", "Scene 5", "Scene 6", "Scene 7", "Scene 8");

    }

}
