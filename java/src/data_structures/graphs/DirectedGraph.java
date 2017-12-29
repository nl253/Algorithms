package data_structures.graphs;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings({"WeakerAccess", "DesignForExtension", "AssignmentToCollectionOrArrayFieldFromParameter", "unused", "ParameterHidesMemberVariable", "PublicMethodNotExposedInInterface", "InstanceVariableMayNotBeInitialized", "InstanceVariableNamingConvention", "ClassNamingConvention", "PublicConstructor", "ClassWithoutLogger", "FieldNotUsedInToString", "MethodReturnOfConcreteClass", "ImplicitCallToSuper", "NonBooleanMethodNameMayNotStartWithQuestion", "UnusedReturnValue", "CallToSuspiciousStringMethod", "AbstractClassNeverImplemented", "AbstractClassWithoutAbstractMethods", "AlibabaAbstractClassShouldStartWithAbstractNaming"})
abstract class DirectedGraph<E extends Comparable<E>> implements Graph, Collection<E> {

    /**
     * a Map that emulates a 2-dimensional matrix for lookup.
     */

    @SuppressWarnings({"FieldMayBeFinal", "CollectionWithoutInitialCapacity"})
    private E nodeTable = new HashMap<>();

    /**
     * Allow empty constructor.
     */

    DirectedGraph() {}

    /**
     * A version of the Constructor with initial nodes.
     *
     * @param initialNodes An iterable of nodes
     */

    DirectedGraph(final Iterable<String> initialNodes) {
        initialNodes.forEach(this::add);
    }

    /**
     * Compute the number of nodes in the Graph sometimes called order.
     *
     * @return number of nodes in the Graph sometimes called order
     */

    @Override
    public int getOrder() {
        return nodeTable.size();
    }

    /**
     * Lookup the cost of going from nodeA to nodeB. O(1).
     *
     * @param nodeA the first node
     * @param nodeB the second node
     * @return the cost of going from the first to the second node
     */

    @SuppressWarnings("MethodWithMultipleReturnPoints")
    Optional<Integer> getCost(final String nodeA, final String nodeB) {
        if (!nodeTable.containsKey(nodeA) || !nodeTable.containsKey(nodeB))
            return Optional.empty();

        return Optional.of(nodeTable.get(nodeA).getOrDefault(nodeB, -1));
    }

    /**
     * Add a node to this Graph. You need to provide a String identifier.
     *
     * @param node a String identifier
     * @return the Graph itself
     */

    @SuppressWarnings("rawtypes")
    void add(final String node) {
        if (!nodeTable.keySet().contains(node)) {
            nodeTable.put(node, new HashMap<>(nodeTable.size() + 1));
            // the distance from any node to itself is 0
            nodeTable.get(node).put(node, 0);
        }
    }

    /**
     * Removes the node specified by a String identifier from this Graph.
     *
     * @param node String id that represents a Node
     * @return the Graph itself
     */

    @SuppressWarnings("rawtypes")
    void remove(final String node) {
        if (nodeTable.containsKey(node)) nodeTable.remove(node);

        nodeTable.values().stream()
                .filter((Map<String, Integer> x) -> x.containsKey(node))
                .forEach((Map<String, Integer> x) -> x.remove(node));
    }

    /**
     * A variant of connect() that assumes the cost is 1.
     *
     * @param nodeA the first node
     * @param nodeB the second node
     * @return the graph itself
     */

    void connect(final String nodeA, final String nodeB) {
        connect(this, nodeA, nodeB, 1);
    }

    @Override
    public String toString() {
        return MessageFormat.format("Graph<{0}>", nodeTable.keySet().stream()
                .map(Object::toString).collect(Collectors.joining(", ")));
    }

}


