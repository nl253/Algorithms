package data_structures.graphs.trees;

import data_structures.graphs.Node;
import java.util.Optional;

/**
 * @author nl253
 */

@SuppressWarnings({"AbstractMethodWithMissingImplementations", "InterfaceWithOnlyOneDirectInheritor", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc"})
public interface BinaryNode<E extends Comparable<E>, N extends BinaryNode<E, N>> extends Node<E>, Comparable<N> {

    Optional<N> getLeft();

    void setLeft(final N node);

    Optional<N> getRight();

    void setRight(final N node);
}
