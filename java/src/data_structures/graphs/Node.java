package data_structures.graphs;

import java.util.Collection;

public interface Node<E extends Comparable<E>> {

    E getId();

    void setId(final E id);

    default E getData() {
        return getId();
    }

    default void setData(final E data) {
        setId(data);
    }

    Collection<Node<E>> getAdjecentNodes();
}
