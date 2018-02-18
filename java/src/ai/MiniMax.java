package ai;

import java.util.Collection;

/**
 *
 */

@SuppressWarnings("OverlyLongLambda")
public final class MiniMax {

    private interface Node {

        Collection<Node> getAdjecentNodes();

        int getValue();
    }

    /**
     * @param root
     * @param depth
     * @return
     */

    Node depthLimitedMiniMax(final Node root, final int depth) {
        return depthLimitedMiniMax(root, depth, true);
    }

    /**
     * @param root
     * @param depth
     * @param max
     * @return
     */

    Node depthLimitedMiniMax(final Node root, final int depth, final boolean max) {

        if (depth == 0) return root;

        final Collection<Node> adjNodes = root.getAdjecentNodes();

        if (adjNodes.isEmpty()) return root;

        return adjNodes.stream()
                .map(x -> depthLimitedMiniMax(x, depth - 1, !max)).max((n1, n2) -> {
                    if (n1.getValue() == n2.getValue()) return 1;
                    else if (max)
                        return Integer.max(n1.getValue(), n2.getValue());
                    else return Integer.min(n2.getValue(), n1.getValue());
                }).get();
    }
}
