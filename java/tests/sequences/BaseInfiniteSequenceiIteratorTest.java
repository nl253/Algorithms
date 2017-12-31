package sequences;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ClassHasNoToStringMethod", "AbstractClassWithOnlyOneDirectInheritor", "ProtectedField"})
abstract class BaseInfiniteSequenceiIteratorTest<E extends Number> {

    abstract E[] expectedString();

    abstract BaseInfiniteSequenceiIterator<E> iteratorToTest();

    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    @Test
    final void next() {

        final BaseInfiniteSequenceiIterator<E> iterator = iteratorToTest();

        // @formatter:off
        final String expectedStr = Arrays
                .stream(expectedString())
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        final String gotStr = IntStream
                .range(0, expectedString().length)
                .mapToObj((int x) -> iterator.next())
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(
                expectedString(),
                gotStr,
                MessageFormat.format("Got {0} expected {1}", gotStr, expectedStr));
    // @formatter:on
    }

}

