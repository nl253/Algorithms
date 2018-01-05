package sequences;

@SuppressWarnings("MagicNumber")
class FibonacciIteratorTest extends BaseInfiniteSequenceiIteratorTest {

    @Override
    final Double[] expected() {
        return new Double[]{3.0d, 5.0d, 8.0d, 13.0d, 21.0d};
    }

    @Override
    final BaseInfiniteSequenceiIterator iteratorToTest() {
        return new FibonacciIterator(1.0, 2.0);
    }
}
