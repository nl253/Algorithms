package data_structures.graphs;

import java.text.MessageFormat;

@SuppressWarnings("ALL")
public class Node<I extends Comparable<I>, V> {

    protected final I id;
    protected final V value;

    public Node(final I id, final V value) {
        this.id = id;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public I getId() {
        return id;
    }

    @Override
    public String toString() {
        return MessageFormat
                .format("Node< {0} -> {1}>", id.toString(), value.toString());
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(final Object o) {
        return id.equals(o);
    }
}
