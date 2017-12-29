package data_structures.graphs;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author nl253
 */

@SuppressWarnings({"ParameterHidesMemberVariable", "InstanceVariableNamingConvention", "ClassNamingConvention", "FieldNotUsedInToString", "unused", "ClassWithoutLogger", "AssignmentToCollectionOrArrayFieldFromParameter", "DesignForExtension", "PublicMethodNotExposedInInterface", "WeakerAccess", "LocalVariableOfConcreteClass", "AccessingNonPublicFieldOfAnotherObject"})
public class Node<E> {

    private Set<Node<E>> neighbours;
    private E id;

    @SuppressWarnings({"ImplicitCallToSuper", "PublicConstructor"})
    public Node(final E id, final Collection<Node<E>> neighbours) {
        this.neighbours = new HashSet<>(neighbours);
        this.id = id;
    }

    @SuppressWarnings({"PublicConstructor", "ImplicitCallToSuper", "CollectionWithoutInitialCapacity"})
    public Node(final E id) {
        neighbours = new HashSet<>();
        this.id = id;
    }

    E getId() {
        return id;
    }

    void setId(final E id) {
        this.id = id;
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    Set<Node<E>> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(final Set<Node<E>> neighbours) {
        this.neighbours = neighbours;
    }

    @SuppressWarnings({"ConditionalExpression", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final Node<?> node = (Node<?>) o;

        return (id != null) ? id.equals(node.id) : (node.id == null);
    }

    @SuppressWarnings({"ConditionalExpression", "NonFinalFieldReferencedInHashCode"})
    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return MessageFormat
                .format("{0}<{1}>", id.toString(), neighbours.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
    }

}
