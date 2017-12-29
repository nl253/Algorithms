package src.data_structures.graphs.trees.binary;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

/**
 * A 2-tuple ie a Pair. A wrapper for a value with an id.
 *
 * @author nl253
 */

public class KeyValPair<I extends Comparable<I>, V> {

    private I id;
    private V value;

    KeyValPair(final I id, final V value) {
        this.id = id;
        this.value = value;
    }

    final Optional<V> getValue() {
        return Optional.of(value);
    }

    final Optional<I> getId() {
        return Optional.of(id);
    }

    @SuppressWarnings({"ConditionalExpression", "DesignForExtension"})
    @Override
    public String toString() {
        return MessageFormat.format("Node<{0} -> {1}>", getId().isPresent() ? id
                .toString() : "", getValue().isPresent() ? value
                .toString() : "");
    }

    @SuppressWarnings({"EqualsWhichDoesntCheckParameterClass", "DesignForExtension", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (!getId().isPresent()) throw new NullPointerException();
        return id.equals(o);
    }

    public final void setId(final I id) {
        this.id = id;
    }

    public final void setValue(final V value) {
        this.value = value;
    }

    @SuppressWarnings({"ObjectInstantiationInEqualsHashCode", "NonFinalFieldReferencedInHashCode", "DesignForExtension"})
    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
