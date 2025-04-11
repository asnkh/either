# Either
Either data type for Java 21+

# Demo

```java
import com.github.asnkh.either.Either;
import com.github.asnkh.either.Left;
import com.github.asnkh.either.Right;

public class DemoMain {

    // Returns long as right and error message as left.
    static Either<String, Long> drawLucky() {
        // ... implementation omitted
    }

    public static void main(String[] args) {
        switch (drawLucky()) {
            case Right(Long score) -> System.err.println("Congrats! Scored " + score + " points.");
            case Left(String message) -> System.err.println(message);
        }
    }
}

```

See `DemoMain.java` for the full code.

# Use Case
Can be used as the return type of any function that might fail.
It is conventional to return the main result as right and the error object (typically an error message) as left
(because 'right' impilies 'correct'!)

Although one could use checked exceptions to represent failures, using `Either` has several benefits:

* Creating an object of type `Either` is cheaper than creating an exception, which needs retrieval of stack trace.
* One can use methods returning `Either` inside lambda functions without writing lengthy try-catch blocks.

# Similar Types In Other Languages

* Haskell: [Data.Either](https://hackage.haskell.org/package/base-4.21.0.0/docs/Data-Either.html)
* Scala: [scala.util.Either](https://www.scala-lang.org/api/2.13.6/scala/util/Either.html)

