package com.github.asnkh.either;

public final record Left<A, B>(A value) implements Either<A, B> {
}

