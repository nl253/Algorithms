package sorts;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Implementation of the compulsory {@code void sort()} method.
     */

    @Override
    List<E> sort(final List<E> data) {
        sort(0, data.size() - 1, data);
        return data;
    }

    /**
     * @param left left boundary
     * @param right right boundary
     */

    private void sort(final int left, final int right, final List<E> data) {
        if (left < right) {
            final int pivotIndex = partition(left, right, data);
            sort(left, pivotIndex - 1, data);
            sort(pivotIndex + 1, right, data);
        }
    }

    /**
     * @param left the left boundary
     * @param right the right boundary
     * @return the pivot
     */

    @SuppressWarnings({"AssignmentToMethodParameter", "ForLoopWithMissingComponent"})
    private int partition(int left, final int right, final List<E> data) {
        final int pivotIndex = (right - left) / 2;
        final E pivot = data.get(pivotIndex);
        final List<E> sublist = data.subList(left, right + 1);
        final List<E> result = Stream.concat(sublist.stream().filter(x -> x
                .compareTo(pivot) <= -1), sublist.stream().filter(x -> x
                .compareTo(pivot) >= 0)).collect(Collectors.toList());
        for (; left <= right; left++) data.set(left, result.get(left));
        return pivotIndex;
    }
}
// vim:ft=java:sw=2:ts=2:foldmethod=marker:foldmarker={,}:
