package data_structures.graphs.trees.heaps;

import data_structures.graphs.trees.Tree;
import java.util.Optional;

/**
 * @author nl253
 */

@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface Heap<E extends Comparable<E>, H extends Heap<E, H>> extends Tree<E, H> {

    Optional<E> findMin();

}
