package com.github.asnkh.either;

import java.util.function.Function;

public interface ThrowingFunction<T, R, E extends Exception> {
    /**
     * Apply this function object to argument <code>x</code> of type <code>T</code>.
     */
    R apply(T x) throws E;

    /**
     * Apply this function object to argument of type either <code>T</code>, <code>E</code>,
     * or any of their subclasses.
     */
    default Either<E, R> apply(Either<? extends E, ? extends T> either) {
        return switch (either) {
            case Right(T t) -> {
                try {
                    R r = apply(t);
                    yield Either.right(r);
                } catch (Exception e) {
                    @SuppressWarnings("unchecked")
                    E ee = (E) e;
                    yield Either.left(ee);
                }
            }
            case Left(E e) -> Either.left(e);
        };
    }

    /**
     * Convert a function <code>T -> R</code> that throws <code>E</code> to
     * a function <code>T -> Either&lt;E, R&gt;</code>.
     *
     * @param f   the function to convert.
     * @param <T> the argument type of the given function.
     * @param <R> the return type of the given function.
     * @param <E> the type of the checked exception that the given function throws.
     * @return the converted function that does not throw.
     */
    static <T, R, E extends Exception> Function<T, Either<E, R>> toFunctionReturningEither(ThrowingFunction<T, R, E> f) {
        return x -> {
            try {
                return Either.right(f.apply(x));
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                // This cast is justified because f can throw either E or RuntimeException
                // Note that we cannot declare catch (E e) here.
                @SuppressWarnings("unchecked")
                E ee = (E) e;
                return Either.left(ee);
            }
        };
    }

}
