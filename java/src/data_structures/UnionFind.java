package data_structures;

import java.util.Collection;

/**
 * In mathematics, two sets are said to be disjoint sets if they have no element
 * in common. Equivalently, disjoint sets are sets whose intersection is the
 * empty set. (https://en.wikipedia.org/wiki/Disjoint_sets)
 * <p>
 * For now on, unless specified otherwise, the information is a direct citation
 * from https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 * <p>
 * Disjoint-set data structure, also called a union–find data structure or
 * merge–find set, is a data structure that keeps track of a set of elements
 * partitioned into a number of disjoint (non-overlapping) subsets.
 * <p>
 * It provides near-constant-time operations (bounded by the inverse Ackermann
 * function) to add new sets, to merge existing sets, and to determine whether
 * elements are in the same set. In addition to many other uses (see the
 * Applications section), disjoint-sets play a key role in Kruskal's algorithm
 * for finding the minimum spanning tree of a graph.
 *
 * @author nl253
 */

@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface UnionFind<E extends Comparable<E>> {

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

    void makeSet(final Collection<? extends E> items);

    /**
     * Union by rank always attaches the shorter tree to the root of the
     * taller tree. Thus, the resulting tree is no taller than the originals
     * unless they were of equal height, in which case the resulting tree is
     * taller by one node.
     *
     * @param first
     * @param second
     */

    void union(final E first, final E second);

    /**
     * Find follows the chain of parent pointers from x upwards through the
     * tree until an element is reached whose parent is itself.
     * <p>
     * This element is the root of the tree and is the representative member
     * of the set to which the item belongs, and may be the item itself.
     *
     * @param node
     * @return root
     */

    E find(E node);
}
