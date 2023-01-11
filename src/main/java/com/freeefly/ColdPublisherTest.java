package com.freeefly;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class ColdPublisherTest {
    public static void main(String[] args) {
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2));

        movieStream.subscribe(Util.subscriber("sam"));
        Util.sleepSeconds(5);

        movieStream.subscribe(Util.subscriber("mike"));
        Util.sleepSeconds(60);
    }

    private static Stream<String> getMovie() {
        System.out.println("got the movie streaming request");
        return Stream.of("Scene 1", "Scene 2", "Scene 3", "Scene 4", "Scene 5", "Scene 6");

    }
}
