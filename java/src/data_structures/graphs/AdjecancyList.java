package data_structures.graphs;

import java.util.Set;

/**
 * @author nl253
 */

public interface AdjecancyList<E extends Comparable<E>> {

    Set<Edge<E>> getEdges();

    void addEdge(E nodeA, E nodeB, int cost);

    default void addEdge(final E nodeA, final E nodeB) {
        addEdge(nodeA, nodeB, 1);
    }
}
