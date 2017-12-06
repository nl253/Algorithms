package data_structures.trees;

import java.text.MessageFormat;
import java.util.Optional;

@SuppressWarnings("ALL")
public class Node<I extends Comparable<I>, V> {

    protected I id;
    protected V value;

    Node(final I id, final V value) {
        this.id = id;
        this.value = value;
    }

    Optional<V> getValue() {
        return Optional.of(value);
    }

    Optional<I> getId() {
        return Optional.of(id);
    }

    @SuppressWarnings("ConditionalExpression")
    @Override
    public String toString() {
        return MessageFormat.format("Node<{0} -> {1}>", getId().isPresent() ? id
                .toString() : "", getValue().isPresent() ? value
                .toString() : "");
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(final Object o) {
        return id.equals(o);
    }

    public void setId(final I id) {
        this.id = id;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
