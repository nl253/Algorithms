package sequences;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ClassHasNoToStringMethod", "AbstractClassWithOnlyOneDirectInheritor"})
abstract class BaseInfiniteSequenceiIteratorTest {

    @SuppressWarnings("WeakerAccess")
    protected Double[] expected;
    @SuppressWarnings("WeakerAccess")
    protected BaseInfiniteSequenceiIterator<Double> iterator;

    @SuppressWarnings("AssertEqualsBetweenInconvertibleTypes")
    @Test
    final void next() {

        final String expectedStr = Arrays.stream(expected).map(Object::toString)
                .collect(Collectors.joining(", "));

        final String gotStr = IntStream.range(0, expected.length)
                .mapToObj(x -> iterator.next()).map(Object::toString)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(expected, gotStr, MessageFormat
                .format("Got {0} expected {1}", gotStr, expectedStr));
    }

}

