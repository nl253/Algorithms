package sorts;

class QuickSortTest<E extends Comparable<E>> extends BaseSortingAlgorithmTest<E> {

    @Override
    protected final BaseSortingAlgorithm<E> getSortingAlgorithm() {
        return new QuickSort<E>(BaseSortingAlgorithmTest.getRandomIntegers(10));
    }
}
