package data_structures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

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
        class Route implements Comparable<Route> {

            public static final int DEFAULT_PATH_LEN = 20;
            List<String> path;
            Integer distance;

            Route(final Iterable<String> nodes, final int distance) {
                this();
                nodes.forEach(path::add);
                this.distance = distance;
            }

            Route(final String firstComponent, final int distance) {
                this();
                path.add(firstComponent);
            }

            Route() {
                path = new ArrayList<>(DEFAULT_PATH_LEN);
            }

            @SuppressWarnings("CompareToUsesNonFinalVariable")
            @Override
            public int compareTo(final Route t) {
                return distance.compareTo(t.distance);
            }

            @SuppressWarnings({"ConditionalExpression", "NonFinalFieldReferenceInEquals"})
            @Override
            public boolean equals(final Object o) {
                return (o instanceof Integer) ? (distance == o) : distance
                        .equals(o);
            }

            @Override
            public int hashCode() {
                int result = path.hashCode();
                result = (distance != null) ? ((31 * result) + distance
                        .hashCode()) : ((31 * result));
                return result;
            }
        }

        // @formatter:off
        final Queue<Route> unvisited = new PriorityQueue<>();

        nodeTable.get(start).keySet()
                .stream()
                .map(x -> new Route(x, nodeTable.get(start).get(x)))
                .forEach(unvisited::add);

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        final Map<String, Integer> visited = new HashMap<>(100);

        while (!unvisited.isEmpty()) {

            final Route focus = unvisited.poll();
            final String lastComponent = focus.path.get(focus.path.size() - 1);

            if (lastComponent.equals(end))
                return Optional.of(focus.path);

            if (!visited.containsKey(focus) || (visited.get(lastComponent) > focus.distance))
                visited.put(lastComponent, focus.distance);

            // @formatter:on
            nodeTable.get(lastComponent).keySet().forEach((String x) -> {
                final List<String> path = new LinkedList<>(focus.path);
                path.add(x);
                unvisited.add(new Route(path, focus.distance + nodeTable
                        .get(lastComponent).get(x)));
            });
        }
        // the path could not be found
        return Optional.empty();
    }
}


