package sequences;

/**
 * @author nl253
 */

@SuppressWarnings("StandardVariableNames")
public class FibonacciIterator extends BaseInfiniteSequenceiIterator {

    private double a;
    private double b;

    /**
     * {@link FibonacciIterator} with the first two numbers already supplied
     */

    public FibonacciIterator() {
        this(0, 1);
    }

    /**
     * {@link FibonacciIterator} with the first number already supplied
     *
     * @param b the second number to supply
     */

    public FibonacciIterator(final double b) {
        this(0, b);
    }

    /**
     * @param a the first number
     * @param b the second number
     */

    @SuppressWarnings("WeakerAccess")
    public FibonacciIterator(final double a, final double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * @return the next number from this {@link FibonacciIterator}
     */

    @SuppressWarnings("IteratorNextCanNotThrowNoSuchElementException")
    @Override
    public final Double next() {
        final double next = b + a;
        a = b;
        b = next;
        return next;
    }
}
