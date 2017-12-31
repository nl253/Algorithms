package data_structures.graphs.trees;

import data_structures.graphs.Node;
import java.util.Optional;

/**
 * @author nl253
 */

@SuppressWarnings({"AbstractMethodWithMissingImplementations", "InterfaceWithOnlyOneDirectInheritor", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc"})
public interface BinaryNode<E extends Comparable<E>> extends Node<E>, Comparable<Node<E>> {

    Optional<?> getLeft();

    void setLeft();

    Optional<?> getRight();

    void setRight(BinaryNode<E> node);
}
