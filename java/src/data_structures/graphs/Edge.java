package data_structures.graphs;

public interface Edge<E extends Comparable<E>> {
    int getWeight();
    E getNodeA();
    E getNodeB();
}
