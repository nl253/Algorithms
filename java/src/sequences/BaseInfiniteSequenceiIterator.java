package sequences;

import java.util.Iterator;

/**
 * @author nl253
 */

@SuppressWarnings("AbstractClassWithOnlyOneDirectInheritor")
public abstract class BaseInfiniteSequenceiIterator<D extends Number> implements Iterator<Double> {

    @Override
    public final boolean hasNext() {
        return true;
    }

    @Override
    public final String toString() {
        return getClass().getName();
    }
}

