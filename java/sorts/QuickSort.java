package sorts;

/**
 * All content in javadocs, unless specified otherwise, is from https://en.wikipedia.org/wiki/Quicksort.
 * <p>
 * Quicksort (sometimes called partition-exchange sort) is an efficient sorting algorithm, serving
 * as a systematic method for placing the elements of an array in order
 * <p>
 * Quicksort is a comparison sort, meaning that it can sort items of any type for which a "less-than" relation (formally, a total order) is defined.
 * In efficient implementations it is not a stable sort, meaning that the relative order of equal sort items is not preserved.
 * Quicksort can operate in-place on an array, requiring small additional amounts of memory to perform the sorting.
 * <p>
 * Mathematical analysis of quicksort shows that, on average, the algorithm takes O(n log n) comparisons to sort n items.
 * In the worst case, it makes O(n2) comparisons, though this behavior is rare.
 *
 * @param <E>
 */

@SuppressWarnings({"UtilityClassCanBeEnum", "LocalVariableOfConcreteClass", "AccessingNonPublicFieldOfAnotherObject"})
final class QuickSort<E extends Comparable<E>> extends BaseSortingAlgorithm<E> {

    @Override
    void sort() { sort(0, list.size() - 1);}

    /**
     * @param left left boundary
     * @param right right boundary
     */

    private void sort(final int left, final int right) {
        if (left < right) {
            final int pivotIndex = partition(left, right);
            sort(left, pivotIndex - 1);
            sort(pivotIndex + 1, right);
        }
    }

    /**
     * @param left the left boundary
     * @param right the right boundary
     * @return the pivot
     */

    @SuppressWarnings({"MethodWithMultipleLoops", "LocalCanBeFinal", "MethodCallInLoopCondition", "AssignmentToMethodParameter", "ProhibitedExceptionThrown", "NewExceptionWithoutArguments"})
    private int partition(int left, int right) {

        final int pivotIndex = (right / 2) + left;

        // the bounds are inclusive
        while (left < right) {

            // increment the pointer if already sorted, stop if not
            while (list.get(left).compareTo(list.get(pivotIndex)) == -1) left++;

            while (list.get(right).compareTo(list.get(pivotIndex)) == 1)
                right--;

            // swap
            if (left < right) {
                final E tmp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, tmp);
                // prevent getting stuck when left and right pointer point to the same value
                left++;
                right--;
            }
        }
        return pivotIndex;
    }

    public static void main(final String... args) {
        final QuickSort<Integer> quickSort = new QuickSort<>();
        quickSort.sort();
        quickSort.test();
        quickSort.print();
    }
}
// vim:ft=java:sw=2:ts=2:foldmethod=marker:foldmarker={,}:
