package com.github.asnkh.either.demo;

import com.github.asnkh.either.Either;
import com.github.asnkh.either.Left;
import com.github.asnkh.either.Right;
import com.github.asnkh.either.ThrowingFunction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static com.github.asnkh.either.ThrowingFunction.toFunctionReturningEither;

public class DemoCat {

    public static void main(String[] args) {
        ThrowingFunction<InputStream, String, IOException> readAll = is -> new String(is.readAllBytes(), StandardCharsets.UTF_8);
        Stream.of(args)
                .map(toFunctionReturningEither(FileInputStream::new))
                .map(readAll::apply)
                .forEachOrdered(either -> {
                    switch (either) {
                        case Right(String s) -> System.out.println(s);
                        case Left(IOException e) -> System.err.println(e.getMessage());
                    }
                });
    }

}
