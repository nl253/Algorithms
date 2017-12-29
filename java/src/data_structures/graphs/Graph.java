package data_structures.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author nl253
 */

public interface Graph<E extends Comparable<E>> extends Collection<E> {

    void connect(final Graph<E> a, final Graph<E> b);

    int cost(final E start, final E dest);

    int getOrder();

    Set<E> adjecentNodes(final E node);


    /**
     * Use the Dijkstra's algorithm to compute the shortest route from a to b.
     *
     * @param start the first node
     * @param end the second node
     * @return Optionally an {@link Iterable} of {@link String}s if such a Route exists, an
     * empty {@link Optional} otherwise
     */

    @SuppressWarnings({"DiamondCanBeReplacedWithExplicitTypeArguments", "CollectionWithoutInitialCapacity", "LocalCanBeFinal", "MethodWithMultipleReturnPoints", "LocalVariableOfConcreteClass", "MethodCallInLoopCondition", "ObjectAllocationInLoop", "SuspiciousMethodCalls"})
    default Optional<? extends Iterable<? extends E>> shortestRoute(final E start, final E end) {

        /**
         * To be used by the PriorityQueue below.
         * Part of Dijkstra's Algorithm.
         */

        @SuppressWarnings({"PackageVisibleField", "LimitedScopeInnerClass", "ClassHasNoToStringMethod", "ComparableImplementedButEqualsNotOverridden", "ReturnOfInnerClass"})
        class Path implements Comparable<Path> {

            private static final int DEFAULT_PATH_LEN = 20;
            private final List<E> path;
            private Integer distance;

            private Path(final Iterable<E> nodes, final int distance) {
                this();
                nodes.forEach(path::add);
                this.distance = distance;
            }

            private Path(final E firstComponent) {
                this();
                path.add(firstComponent);
                distance = cost(start, firstComponent);
            }

            private Path() {
                path = new ArrayList<>(DEFAULT_PATH_LEN);
            }

            @SuppressWarnings("CompareToUsesNonFinalVariable")
            @Override
            public final int compareTo(final Path t) {
                return distance.compareTo(t.distance);
            }

            @SuppressWarnings("NonFinalFieldReferencedInHashCode")
            @Override
            public final int hashCode() {
                int result = path.hashCode();
                result = (31 * result) + distance.hashCode();
                return result;
            }

            @SuppressWarnings({"ConditionalExpression", "NonFinalFieldReferenceInEquals"})
            @Override
            public final boolean equals(final Object obj) {
                return (obj instanceof Integer) ? (distance == obj) : distance
                        .equals(obj);
            }
        }

        // @formatter:off
        @SuppressWarnings("RedundantTypeArguments")
        final Queue<Path> unvisited = adjecentNodes(start)
                .stream()
                .map(Path::new)
                .collect(PriorityQueue<Path>::new, PriorityQueue<Path>::add, PriorityQueue<Path>::addAll);

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        final Map<String, Integer> visited = new HashMap<>(100);

        while (!unvisited.isEmpty()) {

            final Path focus = unvisited.poll();
            final String lastComponent = focus.path.get(focus.path.size() - 1);

            if (lastComponent.equals(end))
                return Optional.of(focus.path);

            if (!visited.containsKey(focus) || (visited.get(lastComponent) > focus.distance))
                visited.put(lastComponent, focus.distance);

            // @formatter:on
            nodeTable.get(lastComponent).keySet().forEach((String x) -> {
                final List<String> path = new LinkedList<>(focus.path);
                path.add(x);
                unvisited.add(new Path(path, focus.distance + nodeTable
                        .get(lastComponent).get(x)));
            });
        }
        // the path could not be found
        return Optional.empty();
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
    default void connect(final E nodeA, final E nodeB, final int cost) {
        if (!contains(nodeA)) add(nodeA);
        if (!contains(nodeB)) add(nodeB);
        directedGraph.nodeTable.get(nodeA).put(nodeB, cost);
    }

}
