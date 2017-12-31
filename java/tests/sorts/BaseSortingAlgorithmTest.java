package sorts;

import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ImplicitNumericConversion", "ClassHasNoToStringMethod"})
class BaseSortingAlgorithmTest<E extends Comparable<E>> {

    private final BaseSortingAlgorithm<E> sortingAlgorithm;

    BaseSortingAlgorithmTest(final BaseSortingAlgorithm<E> algorithm) {
        sortingAlgorithm = algorithm;
    }

    private static final int DEFAULT_RANDOM_INTEGERS_LEN = 10;

    /**
     * Produce a {@link List} of random {@link Integer}s.
     *
     * @return a {@link List} of random {@link Integer}s.
     */

    @SuppressWarnings("WeakerAccess")
    protected final List<E> getRandomIntegers() {
        return getRandomIntegers(DEFAULT_RANDOM_INTEGERS_LEN);
    }

    /**
     * Produce a {@link List} of random {@link Integer}s of lenght randIntegersLen.
     *
     * @param randIntegersLen lenght of the {@link List} of {@link Integer}s
     * @return a {@link List} of random {@link Integer}s.
     */

    // @formatter:off
    @SuppressWarnings("WeakerAccess")
    protected final List<E> getRandomIntegers(final int randIntegersLen) {
        return new SecureRandom()
                .ints(randIntegersLen)
                .boxed()
                .collect(ArrayList<E>::new, ArrayList::add, ArrayList<E>::addAll);
    }

    /**
     * Shortcuts if sorted properly - iterate from the first element onwards,
     * make sure each next element is larger than the previous one.
     */

    @Test
    @SuppressWarnings({"AssertStatement", "AssertionCanBeIf", "MethodCallInLoopCondition", "Convert2streamapi", "ComparatorResultComparison", "WeakerAccess", "LawOfDemeter"})final
    void sort() {

        final List<E> data = sortingAlgorithm.sort(getRandomIntegers());

        for (int i = 1; i < data.size(); i++)
            Assertions.assertTrue(data.get(i).compareTo(data.get(i - 1)) >= 0,
                                  getSortingErrorMsg(data.get(i), data.get(i - 1), data));
    }

    private String getSortingErrorMsg(final E badlySortedItem, final E thePrevItem, final Collection<E> data) {
        return MessageFormat.format(new StringBuilder(10000)
                                            .append("---------------- SORTING ERROR -------------")
                                            .append("{0}{0}")
                                            .append("DATA{0}{0}")
                                            .append("---------------------------------------------")
                                            .append("{1}{0}{0}")
                                            .append("---------------------------------------------")
                                            .append("badly sorted item {2}")
                                            .append("Element {2} should be larger than {3} but it isn't")
                                            .toString(),
                                    System.lineSeparator(),
                                    data.stream()
                                            .map(Object::toString)
                                            .collect(Collectors.joining("," + System.lineSeparator())),
                                    badlySortedItem.toString(),
                                    thePrevItem.toString());
    }
}
