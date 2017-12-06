package sorts;

import java.text.MessageFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Base class for all sorting algorithms, defines the {@code void sort()}
 * method that needs to be implemented and provides utility functions.
 *
 * @param <E>
 */

@SuppressWarnings({"ClassHasNoToStringMethod", "AbstractClassWithOnlyOneDirectInheritor", "AbstractClassNeverImplemented", "ProtectedField", "PackageVisibleField", "NestedAssignment", "UnsecureRandomNumberGeneration", "unchecked", "RedundantTypeArguments"})
abstract class BaseSortingAlgorithm<E extends Comparable<E>> {

    @SuppressWarnings("FieldNamingConvention")
    private final Logger log = Logger.getAnonymousLogger();

    private static final long RAND_LIST_LEN = 10L;

    List<E> list = (List<E>) new Random(Clock.systemUTC().millis())
            .ints(RAND_LIST_LEN).boxed()
            .collect(LinkedList<Integer>::new, LinkedList<Integer>::add, LinkedList<Integer>::addAll);

    /**
     * The method that sorting algorithms need to implement.
     */

    abstract void sort();

    /**
     * Prints sorted list.
     */

    @SuppressWarnings({"rawtypes", "UseOfSystemOutOrSystemErr", "unchecked", "ResultOfMethodCallIgnored", "WeakerAccess"})
    final void print() {
        System.out.println(MessageFormat.format("[{0}]", list.stream()
                .map(E::toString)
                .collect(String::new, (String x, String y) -> MessageFormat
                        .format("{0}, {1}", x, y), String::join)));
    }

    /**
     * Test if sorted properly - iterate from the first element onwards,
     * make sure each next element is larger than the previous one.
     */

    @SuppressWarnings({"AssertStatement", "AssertionCanBeIf", "MethodCallInLoopCondition", "Convert2streamapi", "ComparatorResultComparison", "WeakerAccess", "LawOfDemeter"})
    protected final void test() {
        for (int i = 1; i < list.size(); i++)
            assert list.get(i).compareTo(list.get(i - 1)) == -1 : MessageFormat
                    .format("[ERROR] badly sorted in {0}. Element {1} should be larger than {2} but it isn't", list
                            .getClass().getName(), list.get(i), list
                                    .get(i - 1));

        log.info("The sort was successful");
    }

    /**
     * Utility method, generate a {@link List} of random {@link Integer}s.
     *
     * @param lenght lenght of the random list
     * @return {@link List} of random {@link Integer}s
     */

    @SuppressWarnings({"UnsecureRandomNumberGeneration", "WeakerAccess", "SameParameterValue", "RedundantTypeArguments", "SpellCheckingInspection", "DesignForExtension"})
    void setRandomList(final long lenght) {
        list = (List<E>) new Random(Clock.systemUTC().millis()).ints(lenght)
                .boxed()
                .collect(ArrayList<Integer>::new, ArrayList<Integer>::add, ArrayList<Integer>::addAll);
    }

    /**
     * A version or randomList with supplied arguments.
     *
     * @return {@link List} of random {@link Integer}s
     */

    @SuppressWarnings({"UnsecureRandomNumberGeneration", "WeakerAccess", "DesignForExtension"})
    void setRandomList() {
        setRandomList(RAND_LIST_LEN);
    }
}

