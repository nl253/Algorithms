package data_structures.graphs;

/**
 * @author nl253
 */

@SuppressWarnings({"InterfaceNeverImplemented", "InterfaceWithOnlyOneDirectInheritor"})
public interface Graph<E extends Comparable<E>, G extends Graph<E, G>> {

    int getOrder();

    void setOrder(int val);

    void add(E item);

}
