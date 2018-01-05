package data_structures.graphs;

import java.text.MessageFormat;

/**
 * @author nl253
 */

public class Edge<E extends Comparable<E>> {

    private final int weight;
    private final E nodeA;
    private final E nodeB;

    public Edge(final E nodeA, final int weight, final E nodeB) {
        this.weight = weight;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public Edge(final E nodeA, final E nodeB) {
        this(nodeA, 1, nodeB);
    }


    public final int getWeight() {
        return weight;
    }

    public final E getNodeA() {
        return nodeA;
    }

    public final E getNodeB() {
        return nodeB;
    }

    @Override
    public final String toString() {
        return MessageFormat
                .format("Edge<{1} - {2} - {3}>", nodeA.toString(), weight, nodeB
                        .toString());
    }
}
