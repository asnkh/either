package com.github.asnkh.either;

/**
 * Holder for values of either type <code>A</code> or <code>B</code>.
 *
 * @param <A>
 *     Type of the value in left.
 * @param <B>
 *     Type of the value in right.
 */
public sealed interface Either<A, B> permits Left, Right {
	/**
	 * Create an instance of {@link Left} containing the given value <code>a</code> of type <code>A</code> .
	 */
	public static <A, B> Left<A, B> left(A a) {
		return new Left<>(a);
	}
	/**
	 * Create an instance of {@link Right} containing the given value <code>b</code> of type <code>A</code> .
	 */
	public static <A, B> Right<A, B> right(B b) {
		return new Right<>(b);
	}
}

