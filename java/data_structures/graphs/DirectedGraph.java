package data_structures.graphs;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "DesignForExtension", "AssignmentToCollectionOrArrayFieldFromParameter", "unused", "ParameterHidesMemberVariable", "PublicMethodNotExposedInInterface", "InstanceVariableMayNotBeInitialized", "InstanceVariableNamingConvention", "ClassNamingConvention", "PublicConstructor", "ClassWithoutLogger", "FieldNotUsedInToString", "MethodReturnOfConcreteClass", "ImplicitCallToSuper", "NonBooleanMethodNameMayNotStartWithQuestion", "UnusedReturnValue", "CallToSuspiciousStringMethod", "AbstractClassNeverImplemented", "AbstractClassWithoutAbstractMethods"})
abstract class DirectedGraph {

    /**
     * a Map that emulates a 2-dimensional matrix for lookup.
     */

    @SuppressWarnings({"FieldMayBeFinal", "CollectionWithoutInitialCapacity"})
    private Map<String, Map<String, Integer>> nodeTable = new HashMap<>();

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
        initialNodes.forEach(this::addNode);
    }

    /**
     * Compute the number of nodes in the Graph sometimes called order.
     *
     * @return number of nodes in the Graph sometimes called order
     */

    int getOrder() {
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
        return Optional.of(nodeTable.get(nodeA).get(nodeB));
    }

    /**
     * Add a node to this Graph. You need to provide a String identifier.
     *
     * @param node a String identifier
     * @return the Graph itself
     */

    @SuppressWarnings("rawtypes")
    void addNode(final String node) {
        if (!nodeTable.keySet().contains(node)) {
            nodeTable.put(node, new HashMap<>(nodeTable.size() + 1));
            nodeTable.forEach((String key, Map dict) -> nodeTable.get(node)
                    .put(key, -1));
        }
    }

    /**
     * Removes the node specified by a String identifier from this Graph.
     *
     * @param node String id that represents a Node
     * @return the Graph itself
     */

    @SuppressWarnings("rawtypes")
    void removeNode(final String node) {
        nodeTable.remove(node);
        nodeTable.forEach((String key, Map dict) -> dict.remove(node));
    }

    /**
     * Make a connection between two nodes. If they don't already exist, add
     * them.
     *
     * @param nodeA the first node
     * @param nodeB the second node
     * @param cost the cost of going from the first to the second node
     * @return the graph itself
     */

    @SuppressWarnings("SameParameterValue")
    void connect(final String nodeA, final String nodeB, final int cost) {
        if (!nodeTable.containsKey(nodeA)) addNode(nodeA);
        if (!nodeTable.containsKey(nodeB)) addNode(nodeA);
        nodeTable.get(nodeA).put(nodeB, cost);
    }

    /**
     * A variant of connect() that assumes the cost is 1.
     *
     * @param nodeA the first node
     * @param nodeB the second node
     * @return the graph itself
     */

    void connect(final String nodeA, final String nodeB) {
        connect(nodeA, nodeB, 1);
    }

    @Override
    public String toString() {
        return "Graph<>";
    }

    /**
     * Use the Dijkstra's algoright to compute the shortest route from a to b.
     *
     * @param start the first node
     * @param end the second node
     * @return Optionally an Iterable of Strings if such a route exists, an
     * empty Optional otherwise
     */

    @SuppressWarnings({"DiamondCanBeReplacedWithExplicitTypeArguments", "CollectionWithoutInitialCapacity", "LocalCanBeFinal", "MethodWithMultipleReturnPoints", "LocalVariableOfConcreteClass", "MethodCallInLoopCondition"})
    Optional<Iterable<String>> shortestRoute(final String start, final String end) {

        /**
         * To be used by the PriorityQueue below.
         * Part of Dijkstra's Algorithm.
         */

        @SuppressWarnings({"PackageVisibleField", "LimitedScopeInnerClass", "ClassHasNoToStringMethod", "ComparableImplementedButEqualsNotOverridden", "ReturnOfInnerClass"})
        class Route implements Comparable<Integer> {

            List<String> nodes;
            Integer distance;

            Route(final List<String> nodes, final int distance) {
                this.nodes = nodes;
                this.distance = distance;
            }

            Route() {
                nodes = new LinkedList<>();
            }

            boolean dest(String destination) {
                return nodes.get(nodes.size() - 1).equals(destination);
            }

            Route extend(String pathComponent, int cost) {
                nodes.add(pathComponent);
                distance += cost;
                return this;
            }

            @SuppressWarnings("CompareToUsesNonFinalVariable")
            @Override
            public int compareTo(final Integer t) {
                return distance.compareTo(t);
            }

            @SuppressWarnings({"ConditionalExpression", "NonFinalFieldReferenceInEquals"})
            @Override
            public boolean equals(final Object o) {
                return (o instanceof Integer) ? (distance == o) : distance
                        .equals(o);
            }
        }

        Queue<Route> heap = new PriorityQueue<>();

        Set<String> visited = new HashSet<>();
        Set<String> unvisited = new HashSet<>(nodeTable.keySet());

        @SuppressWarnings("TooBroadScope") Route focus;

        while (!heap.isEmpty()) {

            focus = heap.remove();

        }

        return Optional.empty();
    }
}


