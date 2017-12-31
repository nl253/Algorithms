package data_structures.graphs.trees.heaps;

import data_structures.graphs.trees.Tree;
import java.util.Optional;

/**
 * @author nl253
 */

@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface Heap<E extends Comparable<E>> extends Tree<E> {

    Optional<E> findMin();
}
