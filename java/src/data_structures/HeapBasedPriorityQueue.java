package data_structures;

import java.text.MessageFormat;
import java.util.Optional;
import org.jetbrains.annotations.Contract;

/**
 * PriorityQueues can be used to sort - draw elements until the queue is not empty.
 * <p>
 * This implementation uses a heap, but it can also be implemented using arrays
 * where getting to the left child is k * 2 and the right child: 2 * k + 1. (rounding down)
 * <p>
 * Getting from the child to the parent would be reversing those operations.
 * <p>
 * This allows to sort an array in-place.
 * <p>
 * Heap is a form of a BinaryTree. In the Max-Heap implementation,
 * the largest element is at the root.
 * <p>
 * The depths of Heaps is logarithmic ie at most log2(N) generations.
 * <p>
 * Heap algorithms operate along some path from root to the bottom.
 * To maintain the Heap every time you break the structure you have to fix it ie
 * reorganize the Heap. Eg when:
 * -----------------------------------------------------------------------------
 * - upheap - if the parent is larger
 * you'd swap it with the parent, repeated recursively until you find the place
 * where it settles or when you reach the root.
 * - downheap - the element is too small for it's position ie one of it's children
 * is bigger then it. You look at the largest of children and you recursively swap places
 * with the largest of children until it settles.
 *
 * @author nl253
 */

@SuppressWarnings({"NonBooleanMethodNameMayNotStartWithQuestion", "InnerClassMayBeStatic", "NewClassNamingConvention", "NonStaticInnerClassInSecureContext", "PackageVisibleField", "ClassHasNoToStringMethod"})
public class HeapBasedPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {

    HeapBasedPriorityQueue() {}

    private MaxHeap<E> root;

    /**
     * @return
     */

    final boolean isEmpty() {
        return root().isPresent();
    }

    /**
     * @return The root element.
     */

    @SuppressWarnings("WeakerAccess")
    @Contract(pure = true)
    final Optional<BaseHeap<E>> root() {
        return Optional.ofNullable(root);
    }

    /**
     * @param <T>
     */

    @SuppressWarnings({"AssignmentToSuperclassField", "PackageVisibleInnerClass"})
    final class MaxHeap<T extends Comparable<T>> extends BaseHeap<T> {

        MaxHeap() {}

        /**
         * @param collection
         */

        MaxHeap(final Iterable<T> collection) {
            collection.forEach(this::insert);
        }

        /**
         * @param rootValue
         */

        MaxHeap(final T rootValue) {
            value = rootValue;
        }

        /**
         * @param heap
         */

        MaxHeap(final MaxHeap<T> heap) {
            while (heap.size > 0) {
                final Optional<T> next = heap.remove();
                next.ifPresent(this::insert);
            }
        }

        /**
         *
         */

        @Override
        void upheap() {

        }

        /**
         *
         */

        @Override
        void downheap() {

        }

        /**
         * @param item
         */

        @Override
        void insert(final T item) {
            if (!getLeft().isPresent()) left = new MaxHeap<>(item);
            else if (!getRight().isPresent()) right = new MaxHeap<>(item);
        }

        /**
         * @param oldItem
         * @param newItem
         */

        @Override
        void replace(final T oldItem, final T newItem) {

        }

        /**
         * The larger of the two children needs to become the new root.
         *
         * @return T
         */

        @SuppressWarnings({"MethodWithMultipleReturnPoints", "LocalVariableHidesMemberVariable"})
        @Override
        Optional<T> remove() {
            if (value != null) {
                final T root = value;

                if (getLeft().isPresent() && !getRight().isPresent())


                    return Optional.of(value);
            }
            return Optional.empty();
        }
    }

    @SuppressWarnings("AbstractClassWithOnlyOneDirectInheritor")
    private abstract class BaseHeap<K extends Comparable<K>> {

        /**
         *
         */

        abstract void upheap();

        /**
         *
         */

        abstract void downheap();

        BaseHeap<K> left, right;
        K value;
        int size;

        /**
         * @param item
         */

        abstract void insert(final K item);

        /**
         * @param oldItem
         * @param newItem
         */

        abstract void replace(final K oldItem, final K newItem);

        /**
         * @return
         */

        abstract Optional<K> remove();

        /**
         * @return
         */

        final int size() {
            return size;
        }

        /**
         * @return
         */

        final Optional<BaseHeap<K>> getLeft() {
            return Optional.ofNullable(left);
        }

        /**
         * @return
         */

        final Optional<BaseHeap<K>> getRight() {
            return Optional.ofNullable(right);
        }

        @SuppressWarnings("ConditionalExpression")
        @Override
        public final String toString() {
            // @formatter:off
            return MessageFormat.format("BinaryTree<{0} <- {1} -> <{2}>",
                                        getLeft().isPresent() ? left.toString() : "",
                                        getLeft(),
                                        getRight().isPresent() ? right.toString() : "");
        }
    }
}
