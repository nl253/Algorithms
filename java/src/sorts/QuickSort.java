package sorts;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

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

@SuppressWarnings({"UtilityClassCanBeEnum", "LocalVariableOfConcreteClass", "AccessingNonPublicFieldOfAnotherObject", "MethodWithMultipleLoops"})
final class QuickSort<E extends Comparable<E>> extends BaseSortingAlgorithm<E> {

    QuickSort(final List<E> unsortedData) {
        super(unsortedData);
    }

    /**
     * Implementation of the compulsory {@code void sort()} method.
     */

    @Override
    void sort() {
        sort(0, getUnsortedData().size() - 1);
    }

    /**
     * @param left left boundary
     * @param right right boundary
     */

    private void sort(final int left, final int right) {
        // don't sort 1 element Lists
        if ((right - left) > 1) {
            final int pivotIndex = partition(left, right);
            sort(left, pivotIndex);
            sort(pivotIndex + 1, right);
        }
    }

    /**
     * @param leftBound the left boundary
     * @param rightBound the right boundary
     * @return the pivot
     */

    @SuppressWarnings({"AssignmentToMethodParameter", "ForLoopWithMissingComponent"})
    private int partition(int leftBound, final int rightBound) {

        final int pivotIndex = leftBound + ((rightBound - leftBound) / 2);
        final E pivot = getUnsortedData().get(pivotIndex);

        final int size = rightBound - leftBound;

        final Deque<E> smaller = new ArrayDeque<>(size);
        final Deque<E> equal = new ArrayDeque<>(size / 2);
        final Deque<E> larger = new ArrayDeque<>(size);

        for (int i = leftBound; i <= rightBound; i++) {
            final E e = getUnsortedData().get(i);
            final int compResult = e.compareTo(pivot);
            if (compResult >= 1) larger.addLast(e);
            else if (compResult <= (-1)) smaller.addLast(e);
            else equal.addLast(e);
        }

        while (!smaller.isEmpty()) {
            getUnsortedData().set(leftBound, smaller.removeFirst());
            leftBound++;
        }

        while (!equal.isEmpty()) {
            getUnsortedData().set(leftBound, equal.removeFirst());
            leftBound++;
        }

        while (!larger.isEmpty()) {
            getUnsortedData().set(leftBound, larger.removeFirst());
            leftBound++;
        }

        return pivotIndex;
    }
}
// vim:ft=java:sw=2:ts=2:foldmethod=marker:foldmarker={,}:
