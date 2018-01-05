package sequences;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ClassHasNoToStringMethod", "AbstractClassWithOnlyOneDirectInheritor", "ProtectedField"})
abstract class BaseInfiniteSequenceiIteratorTest {

    abstract Double[] expected();

    abstract BaseInfiniteSequenceiIterator iteratorToTest();

    @SuppressWarnings({"AssertEqualsBetweenInconvertibleTypes", "LawOfDemeter"})
    @Test
    final void next() {

        final BaseInfiniteSequenceiIterator iterator = iteratorToTest();

        // @formatter:off
        final String expectedStr = Arrays
                .stream(expected())
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        final String gotStr = IntStream
                .range(0, expected().length)
                .mapToDouble((int x) -> iterator.next())
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(
                expectedStr,
                gotStr,
                MessageFormat.format("Got {0} expected {1}", gotStr, expectedStr));
    // @formatter:on
    }

}

