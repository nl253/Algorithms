package sorts;

import java.util.List;

@SuppressWarnings({"UtilityClassCanBeEnum", "LocalVariableOfConcreteClass", "AccessingNonPublicFieldOfAnotherObject"})
final class QuickSort<E extends Comparable<E>> extends SortingAlgorithm<E> {

    @Override
    void sort() {
        sort(list, 0, list.size() - 1);
    }

    @SuppressWarnings("LocalCanBeFinal")
    private static <E extends Comparable<E>> void sort(List<E> unsortedList, final int left, final int right) {
        final int pivot = partition(unsortedList, left, right);
        sort(unsortedList, left, pivot - 1);
        sort(unsortedList, pivot, right);
    }

    @SuppressWarnings({"MethodWithMultipleLoops", "LocalCanBeFinal", "MethodCallInLoopCondition", "AssignmentToMethodParameter"})
    private static <E extends Comparable<E>> int partition(List<E> unsortedList, int left, int right) {

        final int pivot = (unsortedList.size() / 2) + left;

        // the bounds are inclusive
        while (left < right) {

            // increment the pointer if already sorted, stop if not
            while (unsortedList.get(left)
                    .compareTo(unsortedList.get(pivot)) == -1) left++;

            while (unsortedList.get(right)
                    .compareTo(unsortedList.get(pivot)) == 1) right--;

            // swap
            if (left < right) {
                final E tmp = unsortedList.get(left);
                unsortedList.set(left, unsortedList.get(right));
                unsortedList.set(right, tmp);
            }
        }
        return left;
    }

    @SuppressWarnings("LocalCanBeFinal")
    public static void main(String... args) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort();
        quickSort.test();
    }
}
// vim:ft=java:sw=2:ts=2:foldmethod=marker:foldmarker={,}:
