package data_structures;

import java.text.MessageFormat;

@SuppressWarnings({"WeakerAccess", "EqualsAndHashcode", "DesignForExtension", "PublicMethodNotExposedInInterface"})
public class TreeNode<I extends Comparable<I>, V> {

    private final I id;
    private final V value;

    public TreeNode(final I id, final V value) {
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
