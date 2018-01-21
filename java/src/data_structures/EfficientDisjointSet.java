package data_structures;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author nl253
 */

@SuppressWarnings("ClassHasNoToStringMethod")
public final class EfficientDisjointSet<E extends Comparable<E>> implements UnionFind<E> {

    /**
     * Every entry is Node => Root.
     */

    private Map<E, E> lookupTable;

    /**
     * @param nodes
     */

    public EfficientDisjointSet(final Collection<E> nodes) {
        makeSet(nodes);
    }

    /**
     * You need to call makeSet afterwards.
     */

    public EfficientDisjointSet() {}

    /**
     * The MakeSet operation makes a new set by creating a new element with a
     * unique id, a rank of 0, and a parent pointer to itself.
     * <p>
     * The parent pointer to itself indicates that the element is the
     * representative member of its own set.
     * <p>
     * The MakeSet operation has O(1) time complexity.
     *
     * @param items you want to insert
     */

    @Override
    public void makeSet(final Collection<? extends E> items) {
        lookupTable = new HashMap<>(items.size());
        // at the beginning, each node is a separate tree
        // ie each node is the root of it's own tree
        items.forEach(x -> lookupTable.put(x, x));
    }

    /**
     * Union by rank always attaches the shorter tree to the root of the
     * taller tree. Thus, the resulting tree is no taller than the originals
     * unless they were of equal height, in which case the resulting tree is
     * taller by one node.
     *
     * @param first
     * @param second
     */

    @Override
    public void union(final E first, final E second) {
        final E shorter;
        final E taller;
        if (getHeight(first) >= getHeight(second)) {
            taller = first;
            shorter = second;
        } else {
            taller = second;
            shorter = first;
        }
        lookupTable.put(shorter, find(taller));
    }

    @SuppressWarnings("MethodCallInLoopCondition")
    private int getHeight(final E node) {
        int height = 0;
        while (!Objects.equals(lookupTable.get(node), node)) height++;
        return height;
    }

    /**
     * Find follows the chain of parent pointers from `node` upwards through the
     * tree until an element is reached whose parent is itself.
     * <p>
     * This element is the root of the tree and is the representative member
     * of the set to which the item belongs, and may be the item itself.
     *
     * @return root
     */

    @SuppressWarnings("MethodCallInLoopCondition")
    @Override
    public E find(final E node) {
        E parent = lookupTable.get(node);
        while (!Objects.equals(node, parent)) {
            parent = lookupTable.get(parent);
            lookupTable.put(node, parent);
        }
        return parent;
    }
}
