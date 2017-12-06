package sorts;

import java.text.MessageFormat;
import java.time.Clock;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

@SuppressWarnings({"ClassHasNoToStringMethod", "AbstractClassWithOnlyOneDirectInheritor", "AbstractClassNeverImplemented"})
abstract class BaseSortingAlgorithm<E extends Comparable<E>> {

    private static final long RAND_LIST_LEN = 100L;
    @SuppressWarnings({"FieldMayBeFinal", "WeakerAccess", "ProtectedField"})
    protected List<E> list;
    @SuppressWarnings({"StaticVariableNamingConvention", "ConstantNamingConvention", "WeakerAccess"})
    protected static final Logger log = Logger.getAnonymousLogger();

    @SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
    BaseSortingAlgorithm(final List<E> unsortedList) {
        list = unsortedList;
    }

    @SuppressWarnings("LocalCanBeFinal")
    BaseSortingAlgorithm() {
        list = randomList();
    }

    abstract void sort();

    @SuppressWarnings({"rawtypes", "UseOfSystemOutOrSystemErr", "unchecked", "ResultOfMethodCallIgnored"})
    static <E extends Comparable<E>> void print(final Collection<E> sortedList) {
        print(sortedList.stream());
    }

    @SuppressWarnings({"rawtypes", "UseOfSystemOutOrSystemErr", "unchecked", "ResultOfMethodCallIgnored", "WeakerAccess"})
    static <E extends Comparable<E>> void print(final Stream<E> sortedList) {
        System.out.println(MessageFormat
                                   .format("[{0}]", sortedList.map(E::toString)
                                           .collect(String::new, (String x, String y) -> MessageFormat
                                                   .format("{0}, {1}", x, y), String::join)));
    }

    @SuppressWarnings({"rawtypes", "UseOfSystemOutOrSystemErr", "unchecked", "ResultOfMethodCallIgnored", "WeakerAccess"})
    static <E extends Comparable<E>> void print(final Iterable<E> sortedList) {
        final Builder<E> builder = Stream.builder();
        sortedList.forEach(builder::add);
        print(builder.build());
    }

    @SuppressWarnings({"AssertStatement", "AssertionCanBeIf", "MethodCallInLoopCondition", "Convert2streamapi", "ComparatorResultComparison", "WeakerAccess"})
    protected static <E extends Comparable<E>> void test(final List<E> unsortedList) {
        for (int i = 0; i < unsortedList.size(); i++)
            assert unsortedList.get(i)
                    .compareTo(unsortedList.get(i - 1)) == -1 : MessageFormat
                    .format("[ERROR] badly sorted in {0}. Element {1} should be larger than {2} but it isn't", unsortedList
                            .getClass().getName(), unsortedList
                                    .get(i), unsortedList.get(i - 1));
        log.info("The sort was successful");
    }

    @SuppressWarnings({"TypeParameterHidesVisibleType", "DesignForExtension"})
    <E extends Comparable<E>> void test() {
        test(list);
    }

    @SuppressWarnings({"UnsecureRandomNumberGeneration", "WeakerAccess", "SameParameterValue", "RedundantTypeArguments", "SpellCheckingInspection"})
    static <E extends Comparable<E>> List<E> randomList(final long lenght) {
        return new Random(Clock.systemUTC().millis()).ints(lenght).boxed()
                .collect(LinkedList<E>::new, List::add, List<E>::addAll);
    }

    @SuppressWarnings({"UnsecureRandomNumberGeneration", "WeakerAccess"})
    static <E extends Comparable<E>> List<E> randomList() {
        return randomList(RAND_LIST_LEN);
    }
}

