package data_structures.graphs;

import java.util.Optional;

public interface Node<E extends Comparable<E>, N extends Node<E, N>> {

    Optional<E> getId();

    void setId(final E id);

    default Optional<E> getData() {
        return getId();
    }

    default void setData(final E data) {
        setId(data);
    }
}
