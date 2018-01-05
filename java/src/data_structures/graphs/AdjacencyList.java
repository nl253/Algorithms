package data_structures.graphs;

import java.util.Set;

/**
 * @author nl253
 */

@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface AdjacencyList<E extends Comparable<E>, A extends AdjacencyList<E, A>> {

    Set<Edge<E>> getEdges();

    Set<E> getAdjecentNodes(final E start);

    void addEdge(final E nodeA, final int cost, final E nodeB);

    default void addEdge(final E nodeA, final E nodeB) {
        addEdge(nodeA, 1, nodeB);
    }

    @SuppressWarnings("FeatureEnvy")
    default void addEdge(final Edge<E> edge) {
        addEdge(edge.getNodeA(), edge.getWeight(), edge.getNodeB());
    }
}
