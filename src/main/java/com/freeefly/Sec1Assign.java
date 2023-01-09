package com.freeefly;

import com.rp.courseutil.Util;
import org.apache.commons.lang3.time.StopWatch;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sec1Assign {
    private static final Path PATH = Paths.get("src/main/resources/assignment/sec01");
    public static Mono<String> readAsync(String fileName) {
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    public static Mono<Void> writeAsync(String fileName, String content) {
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    public static Mono<Void> deleteAsync(String fileName) {
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    private static String readFile(String fileName) {
        try {
            return Files.readString(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeFile(String fileName, String content) {
        try {
            Files.writeString(PATH.resolve(fileName), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteFile(String fileName) {
        try {
            Files.delete(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        long cost = Util.measureTimeMillis(() -> {
            for (int i = 0; i < 10; i++) {
                String fileName = String.format("file_test%s.txt", i);
                writeAsync(fileName, String.format("this is test%s", i))
                        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
                readAsync(fileName)
                        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
                deleteAsync(fileName)
                        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
            }
        });
        System.out.println(String.format("time cost: %s", cost));

    }
}
