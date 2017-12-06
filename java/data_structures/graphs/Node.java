package data_structures.graphs;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author norbert
 */

@SuppressWarnings({"ParameterHidesMemberVariable", "InstanceVariableNamingConvention", "ClassNamingConvention", "FieldNotUsedInToString", "unused", "ClassWithoutLogger", "AssignmentToCollectionOrArrayFieldFromParameter", "DesignForExtension", "PublicMethodNotExposedInInterface", "WeakerAccess"})
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

    @Override
    public String toString() {
        return MessageFormat.format("Node<{0}>", id.toString());
    }

}
