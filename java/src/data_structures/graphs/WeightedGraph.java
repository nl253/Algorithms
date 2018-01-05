package data_structures.graphs;

import java.util.List;
import java.util.Optional;

/**
 * @author nl253
 */

@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface WeightedGraph<E extends Comparable<E>, W extends WeightedGraph<E, W>> extends Graph<E, W> {

    int getWeight(final E nodeA, final E nodeB);

    Optional<List<E>> shortestRoute(final E start, final E dest);

}
