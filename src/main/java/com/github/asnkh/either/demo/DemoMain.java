package com.github.asnkh.either.demo;

import com.github.asnkh.either.Either;
import com.github.asnkh.either.Left;
import com.github.asnkh.either.Right;

import java.time.Instant;

public class DemoMain {

    private static final long THRESHOLD = 1_000L;
    private static final long WINDOW_SIZE = 10_000L;

    static Either<String, Long> drawLucky() {
        long epochMilli = Instant.now().toEpochMilli();
        long score = epochMilli % 10_000L;
        if (score < THRESHOLD) {
            return Either.right(score);
        } else {
            double remaining = WINDOW_SIZE * Math.ceil((double) epochMilli / WINDOW_SIZE) - epochMilli;
            return Either.left("Fail! Try again after " + remaining + "[ms]!");
        }
    }

    public static void main(String[] args) {
        switch (drawLucky()) {
            case Right(Long score) -> System.err.println("Congrats! Scored " + score + " points.");
            case Left(String message) -> System.err.println(message);
        }
    }
}
