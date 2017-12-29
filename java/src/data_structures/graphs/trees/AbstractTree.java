package src.data_structures.graphs.trees;

import data_structures.graphs.trees.Tree;
import src.data_structures.graphs.AbstractGraph;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author nl253
 */

@SuppressWarnings({"AbstractClassNeverImplemented", "ReturnOfNull", "StandardVariableNames", "unchecked", "LocalVariableOfConcreteClass", "QuestionableName", "AbstractClassWithOnlyOneDirectInheritor", "ClassWithTooManyMethods", "PublicMethodNotExposedInInterface", "DesignForExtension", "AccessingNonPublicFieldOfAnotherObject"})
public abstract class AbstractTree<E extends Comparable<E>> extends AbstractGraph<E> implements Comparable<Tree>, data_structures.graphs.trees.Tree {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private int order;
    private E payload;

    /**
     * Store an item in this {@link AbstractTree}.
     *
     * @return item stored in this {@link AbstractTree}.
     */

    @SuppressWarnings("WeakerAccess")
    public final void setPayload(final E newItem) {
        payload = newItem;
    }

    /**
     * Set children to children.
     *
     * @param children {@link AbstractTree} nodes
     */

    protected abstract void setChildren(final Iterable<? extends Tree> children);

    /**
     * Set children to children.
     *
     * @param children {@link AbstractTree} nodes
     */

    @SuppressWarnings("OverloadedVarargsMethod")
    final void setChildren(final Tree... children) {
        setChildren(Arrays.asList(children));
    }

    /**
     * Retrieve item stored in this {@link AbstractTree}.
     *
     * @return item stored in this {@link AbstractTree}.
     */

    public Optional<? extends E> getPayload() {
        return Optional.ofNullable(payload);
    }

    /**
     * The height of the {@link AbstractTree} - the length of longest branch.
     *
     * @return height of the {@link AbstractTree}
     */

    @Override
    @SuppressWarnings({"ConditionalExpression", "WeakerAccess"})
    public final int getHeight() {
        // @formatter:off
        if (!getChildren().isPresent()) return 1;
        return 1 + getChildren().get().stream()
                .map(AbstractTree::getHeight)
                .reduce(1, (Integer x, Integer y) -> (x >= y) ? x : y);
        // @formatter:on
    }

    // /**
    //  * The number of nodes.
    //  *
    //  * @return the number of nodes in the {@link AbstractTree}
    //  */
    //
    // @Override
    // @SuppressWarnings({"UnnecessarilyQualifiedInnerClassAccess", "WeakerAccess"})
    // public final int getOrder() {
    //     if (!getChildren().isPresent() || (getChildren().get().iterator()
    //             .next() == null)) return 1;
    //     final Stream.Builder<AbstractTree<E>> builder = Stream.builder();
    //     getChildren().get().forEach(builder::add);
    //     final Stream<AbstractTree<E>> children = builder.build();
    //
    //     return children.collect(Collectors.toList()).size() + children
    //             .map(AbstractTree::getOrder)
    //             .reduce(0, (Integer x, Integer y) -> x + y);
    // }

    // /**
    //  * Direct descendants of this {@link AbstractTree}.
    //  *
    //  * @return children
    //  */
    //
    // @SuppressWarnings("WeakerAccess")
    // public final Optional<SortedSet<AbstractTree<E>>> getChildren() {
    //     return Optional.ofNullable(children);
    // }

    public abstract Optional<? extends Collection<? extends Tree>> getChildren();

    /**
     * Descendants of this {@link AbstractTree}.
     *
     * @return descendants
     */

    @Override
    @SuppressWarnings("WeakerAccess")
    final Optional<Collection<Tree>> getDescendants() {
        if (!getChildren().isPresent()) return Optional.empty();
        final Deque<? super Tree> descendants = new LinkedList<>();
        descendants.addAll(getChildren().get());

        final Collection<Tree> result = new HashSet<>(getOrder());

        while (!descendants.isEmpty()) {
            final AbstractTree<?> focus = (AbstractTree<?>) descendants.pollFirst();
            result.add(focus);
            if (focus.getDescendants().isPresent())
                descendants.addAll(focus.getChildren().get());
        }
        return Optional.of(result);
    }


    protected abstract void insert(final E elem);

    final void insert(final E... elems) {
        for (final E e : elems) insert(e);
    }

    final void insert(final Iterable<? extends E> elems) {
        elems.forEach(this::insert);
    }

    @Override
    public final boolean removeAll(final Collection collection) {
        if (!getDescendants().isPresent()) return false;
        forEach((E x) -> {
            if (collection.contains(x)) remove(x);
        });
        return true;
    }

    @Override
    public final boolean contains(final Object o) {
        return getPayload().get().equals(o) || (getChildren()
                .isPresent() && getChildren().get().stream()
                .anyMatch((Collection<? extends E> x) -> x.contains(o)));
    }

    @Override
    public final boolean isEmpty() {
        return !getPayload().isPresent();
    }

    @SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
    @Override
    public final Stream<E> stream() {
        final Stream.Builder<E> builder = Stream.builder();
        getPayload().ifPresent(builder::add);
        getDescendants()
                .ifPresent((Iterable<? extends Tree> x) -> {
                    x.forEach((AbstractTree<? extends E> y) -> y.getPayload()
                            .ifPresent(builder::add));
                });
        return builder.build();
    }


    @Override
    public int size() {
        return getOrder();
    }

    @Override
    public boolean add(final E e) {
        insert(e);
        return true;
    }

    @Override
    public final void clear() {
        setChildren(new TreeSet<>());
        setPayload(null);
    }

    @SuppressWarnings("TypeMayBeWeakened")
    @Override
    public final void forEach(final Consumer<? super E> action) {
        getPayload().ifPresent(action);
        getChildren()
                .ifPresent((Collection<? extends Tree> x) -> x
                        .forEach((AbstractTree<? extends E> y) -> y
                                .forEach(action)));
    }

    @Override
    public final int compareTo(final AbstractTree<? extends E> t) {
        return getPayload().get().compareTo(t.getPayload().get());
    }

    @SuppressWarnings({"OverlyComplexBooleanExpression", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (!getClass().equals(obj.getClass()))) return false;

        final AbstractTree<?> that = (AbstractTree<?>) obj;

        return (order == that.order) && (((payload != null) && payload
                .equals(that.payload)) || ((payload == null) && (that.payload == null)));
    }

    @Override
    public int hashCode() {
        return order;
    }
}
