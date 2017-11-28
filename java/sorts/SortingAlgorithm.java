package sorts;

import java.text.MessageFormat;
import java.time.Clock;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@SuppressWarnings({"AbstractClassWithOnlyOneDirectInheritor", "ConfusingMainMethod", "AbstractClassWithoutAbstractMethods", "AssignmentToCollectionOrArrayFieldFromParameter", "ClassHasNoToStringMethod"})
abstract class SortingAlgorithm<E extends Comparable<E>> {

    private static final long RAND_LIST_LEN = 100L;
    @SuppressWarnings({"FieldMayBeFinal", "WeakerAccess", "ProtectedField"})
    protected List<E> list;
    @SuppressWarnings({"StaticVariableNamingConvention", "ConstantNamingConvention", "WeakerAccess"})
    protected static final Logger log = Logger.getAnonymousLogger();

    @SuppressWarnings("LocalCanBeFinal")
    SortingAlgorithm(List<E> unsortedList) {
        list = unsortedList;
    }

    @SuppressWarnings("LocalCanBeFinal")
    SortingAlgorithm() {
        list = randomList();
    }

    abstract void sort();

    @SuppressWarnings({"rawtypes", "UseOfSystemOutOrSystemErr", "unchecked"})
    static void print(final Iterable sortedList) {
        sortedList.forEach(System.out::println);
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

    @SuppressWarnings({"UnsecureRandomNumberGeneration", "WeakerAccess", "SameParameterValue"})
    static <E extends Comparable<E>> List<E> randomList(final long lenght) {
        final Random random = new Random(Clock.systemUTC().millis());
        return random.ints(lenght)
                .collect(LinkedList::new, List::add, List::addAll);
    }

    @SuppressWarnings({"UnsecureRandomNumberGeneration", "WeakerAccess"})
    static <E extends Comparable<E>> List<E> randomList() {
        return randomList(RAND_LIST_LEN);
    }
}

