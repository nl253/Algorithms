package data_structures.graphs.trees.heaps;

import java.util.Optional;
import src.data_structures.graphs.trees.AbstractTree;

@SuppressWarnings({"AbstractClassWithOnlyOneDirectInheritor", "NonBooleanMethodNameMayNotStartWithQuestion"})
abstract class AbstractHeap<E extends Comparable<E>> extends AbstractTree<E> {

    /**
     * Retrieve, but do not remove the smallest item of type E from the {@link AbstractHeap}.
     *
     * @return the smallest item
     */

    public final Optional<? extends E> findMin() {
        return getPayload();
    }

    /**
     * Retrieve, and remove the smallest item of type E from the {@link AbstractHeap}.
     *
     * @return the smallest item
     */

    abstract E removeMin();

}
