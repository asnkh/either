# Either
Either data type for Java 21+

# Demo 1: Lucky Draw
Run `demo-lucky-draw.sh` at the best timing and get the highest score!

```java
import com.github.asnkh.either.Either;
import com.github.asnkh.either.Left;
import com.github.asnkh.either.Right;

public class DemoMain {

    // Returns long as right and error message as left.
    static Either<String, Long> drawLucky() {
        long x = some_random_function();
        if (x < THRESHOLD) {
            return Either.right(x);
        } else {
            return Either.left("some message");
        }
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

# Demo 2: Cat
Unix command "cat" can be implemented essentially in less than 10 lines!
Run `demo-cat.sh file1 file2 file3 ...` to output the concatenated contents of the files.

```java
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
```

See `DemoCat.java` for the full code.

# Use Case
Can be used as the return type of any function that might fail.
It is conventional to return the main result as right and the error object (typically an error message) as left
(because 'right' implies 'correct'!)

Although one could use checked exceptions to represent failures, using `Either` has several benefits:

* Creating an object of type `Either` is cheaper than creating an exception, which needs retrieval of stack trace.
* One can use methods returning `Either` inside lambda functions without writing lengthy try-catch blocks.

# Similar Types In Other Languages

* Haskell: [Data.Either](https://hackage.haskell.org/package/base-4.21.0.0/docs/Data-Either.html)
* Scala: [scala.util.Either](https://www.scala-lang.org/api/2.13.6/scala/util/Either.html)

