package sorts;

import java.util.List;

@SuppressWarnings({"UtilityClassCanBeEnum", "LocalVariableOfConcreteClass", "AccessingNonPublicFieldOfAnotherObject"})
final class QuickSort extends SortingAlgorithm<Integer> {

    @Override
    void sort() {
        sort(list, 0, list.size() - 1);
    }

    private static void sort(List<Integer> unsortedList, final int left, final int right) {
        final int pivot = partition(unsortedList, left, right);
        sort(unsortedList, left, pivot - 1);
        sort(unsortedList, pivot, right);
    }

    private static int partition(List<Integer> unsortedList, int left, int right) {

        final int pivot = (unsortedList.size() / 2) + left;

        // the bounds are inclusive
        while (left < right) {

            // increment the pointer if already sorted, stop if not
            while (unsortedList.get(left) <= unsortedList.get(pivot)) left++;

            while (unsortedList.get(right) > unsortedList.get(pivot)) right--;

            // swap
            if (left < right) {
                final int tmp = unsortedList.get(left);
                unsortedList.set(left, unsortedList.get(right));
                unsortedList.set(right, tmp);
            }
        }
        return left;
    }

    @SuppressWarnings("LocalCanBeFinal")
    public static <E extends Comparable<E>> void main(String... args) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort();
        quickSort.test();
    }
}
// vim:ft=java:sw=2:ts=2:foldmethod=marker:foldmarker={,}:
