package data_structures.graphs;

import java.util.List;
import java.util.Optional;

public interface WeightedGraph<E extends Comparable<E>> extends Graph<E> {

    int getWeight(final E nodeA, final E nodeB);

    Optional<List<E>> shortestRoute(final E start, final E dest);

}
