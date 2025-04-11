package com.github.asnkh.either;

public record Right<A, B>(B value) implements Either<A, B> {
}

