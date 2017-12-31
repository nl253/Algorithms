package data_structures.graphs;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author nl253
 */

@SuppressWarnings({"ParameterHidesMemberVariable", "InstanceVariableNamingConvention", "ClassNamingConvention", "FieldNotUsedInToString", "unused", "ClassWithoutLogger", "AssignmentToCollectionOrArrayFieldFromParameter", "DesignForExtension", "PublicMethodNotExposedInInterface", "WeakerAccess", "LocalVariableOfConcreteClass", "AccessingNonPublicFieldOfAnotherObject"})
public final class MultiNode<E extends Comparable<E>> implements Node<E> {

    private Collection<? extends Node<? extends E>> children;
    private E id;

    @SuppressWarnings({"ImplicitCallToSuper", "PublicConstructor"})
    public MultiNode(final E id, final Collection<? extends Node<? extends E>> children) {
        setChildren(new HashSet<>(children));
        setId(id);
    }

    @SuppressWarnings({"PublicConstructor", "ImplicitCallToSuper", "CollectionWithoutInitialCapacity"})
    public MultiNode(final E id) {
        this(id, new HashSet<>());
    }

    @Override
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public Collection<? extends Node<? extends E>> getChildren() {
        return children;
    }

    @Override
    public void setChildren(final Collection<? extends Node<? extends E>> children) {
        this.children = children;
    }

    @Override
    public E getId() {
        return id;
    }

    @Override
    public void setId(final E id) {
        this.id = id;
    }

    @SuppressWarnings({"ConditionalExpression", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        else if (!(obj instanceof MultiNode) || !getClass()
                .equals(obj.getClass())) return false;
        final MultiNode<?> node = (MultiNode<?>) obj;
        return (id != null) ? id.equals(node.id) : (node.id == null);
    }

    @SuppressWarnings({"ConditionalExpression", "NonFinalFieldReferencedInHashCode"})
    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}<{1}>", id.toString(), children.stream()
                .map(Object::toString).collect(Collectors.joining(", ")));
    }

}
