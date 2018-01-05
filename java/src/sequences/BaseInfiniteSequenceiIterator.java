package sequences;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author nl253
 */

@SuppressWarnings({"AbstractClassWithOnlyOneDirectInheritor", "rawtypes"})
public abstract class BaseInfiniteSequenceiIterator implements Iterator<Double> {

    @Override
    public abstract Double next();

    /**
     * Infinite sequences can always produce another number, hence, hasNext() always returns true.
     *
     * @return true
     */

    @Override
    public final boolean hasNext() {
        return true;
    }

    /**
     * {@link String} representation of the {@link BaseInfiniteSequenceiIterator}. Eg "Fibonacci { 1, 2, 3, 5, 8 ... }"
     *
     * @return String representation of the sequence
     */

    @Override
    public final String toString() {
        return MessageFormat
                .format("{0} { {1} ... } ", getClass().getName(), IntStream
                        .range(0, 5).mapToObj(x -> next()).map(Object::toString)
                        .collect(Collectors.joining(", ")));
    }
}

