package data_structures.graphs;

/**
 * @author nl253
 */

@SuppressWarnings({"InterfaceNeverImplemented", "InterfaceWithOnlyOneDirectInheritor"})
public interface Graph<E extends Comparable<E>> {

    /**
     * Make a connection between two nodes. If they don't already exist, add
     * them.
     *
     * @param a the first node
     * @param b the second node
     * @param cost the cost of going from the first to the second node
     * @return the graph itself
     */

    void connect(final E a, final E b, final int cost);

    default void connect(final E a, final E b) {
        connect(a, b, 1);
    }

    int getOrder();

}
