package data_structures.graphs;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "DesignForExtension", "AssignmentToCollectionOrArrayFieldFromParameter", "unused", "ParameterHidesMemberVariable", "PublicMethodNotExposedInInterface", "InstanceVariableMayNotBeInitialized", "InstanceVariableNamingConvention", "ClassNamingConvention", "PublicConstructor", "ClassWithoutLogger", "FieldNotUsedInToString", "MethodReturnOfConcreteClass", "ImplicitCallToSuper", "NonBooleanMethodNameMayNotStartWithQuestion", "UnusedReturnValue", "CallToSuspiciousStringMethod"})
abstract class DirectedGraph {

    @SuppressWarnings({"FieldMayBeFinal", "CollectionWithoutInitialCapacity"})
    private Map<String, Map<String, Integer>> nodeTable = new HashMap<>();

    DirectedGraph() {}

    DirectedGraph(final Iterable<String> initialNodes) {
        initialNodes.forEach(this::addNode);
    }

    int getOrder() {
        return nodeTable.size();
    }

    @SuppressWarnings("MethodWithMultipleReturnPoints")
    Optional<Integer> getCost(final String nodeA, final String nodeB) {
        if (!nodeTable.containsKey(nodeA) || !nodeTable.containsKey(nodeB))
            return Optional.empty();
        return Optional.of(nodeTable.get(nodeA).get(nodeB));
    }

    @SuppressWarnings("rawtypes")
    DirectedGraph addNode(final String node) {
        if (!nodeTable.keySet().contains(node)) {
            nodeTable.put(node, new HashMap<>(nodeTable.size() + 1));
            nodeTable.forEach((String key, Map dict) -> nodeTable.get(node)
                    .put(key, -1));
        }
        return this;
    }

    @SuppressWarnings("rawtypes")
    DirectedGraph removeNode(final String node) {
        nodeTable.remove(node);
        nodeTable.forEach((String key, Map dict) -> dict.remove(node));
        return this;
    }

    @SuppressWarnings("SameParameterValue")
    DirectedGraph connect(final String nodeA, final String nodeB, final int cost) {
        if (!nodeTable.containsKey(nodeA)) addNode(nodeA);
        if (!nodeTable.containsKey(nodeB)) addNode(nodeA);
        nodeTable.get(nodeA).put(nodeB, cost);
        return this;
    }

    DirectedGraph connect(final String nodeA, final String nodeB) {
        connect(nodeA, nodeB, 1);
        return this;
    }

    @Override
    public String toString() {
        return "Graph<>";
    }

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

            if (focus.getLast().equals(end)) return Optional.of(focus.nodes);

            focus.nodes.fo

        }

        return Optional.empty();
    }
}


