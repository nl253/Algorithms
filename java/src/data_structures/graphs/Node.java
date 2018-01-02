package data_structures.graphs;

public interface Node<E extends Comparable<E>> {

    E getId();

    void setId(final E id);

    default E getData() {
        return getId();
    }

    default void setData(final E data) {
        setId(data);
    }
}
