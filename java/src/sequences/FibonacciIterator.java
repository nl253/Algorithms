package sequences;

/**
 * @author nl253
 */

@SuppressWarnings("StandardVariableNames")
public class FibonacciIterator extends BaseInfiniteSequenceiIterator<Double> {

    private double a;
    private double b;

    public FibonacciIterator() {
        this(0, 1);
    }

    public FibonacciIterator(final double b) {
        this(0, b);
    }

    @SuppressWarnings("WeakerAccess")
    public FibonacciIterator(final double a, final double b) {
        this.a = a;
        this.b = b;
    }

    @SuppressWarnings("IteratorNextCanNotThrowNoSuchElementException")
    @Override
    public final Double next() {
        final double next = b + a;
        a = b;
        b = next;
        return next;
    }
}
