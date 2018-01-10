package sorts;

import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ImplicitNumericConversion", "ClassHasNoToStringMethod", "AbstractClassWithOnlyOneDirectInheritor"})
abstract class BaseSortingAlgorithmTest<E extends Comparable<E>> {

    protected abstract BaseSortingAlgorithm<E> getSortingAlgorithm();

    private static final int DEFAULT_RANDOM_INTEGERS_LEN = 10;

    /**
     * Produce a {@link List} of random {@link Integer}s.
     *
     * @return a {@link List} of random {@link Integer}s.
     */

    @SuppressWarnings("WeakerAccess")
    protected static <E extends Comparable<E>> List<E> getRandomIntegers() {
        return getRandomIntegers(DEFAULT_RANDOM_INTEGERS_LEN);
    }

    /**
     * Produce a {@link List} of random {@link Integer}s of lenght randIntegersLen.
     *
     * @param randIntegersLen lenght of the {@link List} of {@link Integer}s
     * @return a {@link List} of random {@link Integer}s.
     */

    // @formatter:off
    @SuppressWarnings({"WeakerAccess", "unchecked"})
    protected static <E extends Comparable<E>> List<E> getRandomIntegers(final int randIntegersLen) {
        return ((List<E>) new SecureRandom()
                .ints(randIntegersLen)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList()));
    }

    /**
     * Shortcuts if sorted properly - iterate from the first element onwards,
     * make sure each next element is larger than the previous one.
     */

    @Test
    @SuppressWarnings({"AssertStatement", "AssertionCanBeIf", "MethodCallInLoopCondition", "Convert2streamapi", "ComparatorResultComparison", "WeakerAccess", "LawOfDemeter"})final
    void sort() {

        final List<E> data = getSortingAlgorithm().sorted();

        for (int i = 1; i < data.size(); i++)
            Assertions.assertTrue(data.get(i).compareTo(data.get(i - 1)) >= 0,
                                  getSortingErrorMsg(data.get(i), data.get(i - 1), data));
    }

    private String getSortingErrorMsg(final E badlySortedItem, final E thePrevItem, final Collection<E> data) {
        return MessageFormat.format(new StringBuilder(10000)
                                            .append("{0}")
                                            .append("---------------- SORTING ERROR -------------")
                                            .append("{0}{0}")
                                            .append("INCORRECTLY SORTED DATA{0}")
                                            .append("---------------------------------------------")
                                            .append("{0}{1}{0}")
                                            .append("---------------------------------------------")
                                            .append("{0}Incorrectly sorted item: `{2}`{0}")
                                            .append("Element `{2}` should be larger or equal to `{3}` but it's smaller.")
                                            .append(System.lineSeparator())
                                            .toString(),
                                    System.lineSeparator(),     // {0}
                                    data.stream()               // {1}
                                            .map(Object::toString)
                                            .collect(Collectors.joining("," + System.lineSeparator())),
                                    badlySortedItem.toString(), // {2}
                                    thePrevItem.toString());    // {3}
    }
}
