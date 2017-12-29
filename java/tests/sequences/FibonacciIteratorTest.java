package sequences;

import org.junit.jupiter.api.BeforeEach;

class FibonacciIteratorTest extends BaseInfiniteSequenceiIteratorTest {

    @BeforeEach
    void setUp() {
        expected = new Double[]{3d, 5d, 8d, 13d, 21d};
        iterator = new FibonacciIterator(1, 2);
    }

}
