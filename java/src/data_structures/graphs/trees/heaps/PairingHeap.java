package data_structures.graphs.trees.heaps;

import data_structures.graphs.trees.Tree;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author nl253
 */

@SuppressWarnings({"StandardVariableNames", "ClassWithTooManyMethods", "LocalVariableHidesMemberVariable", "unchecked", "AccessingNonPublicFieldOfAnotherObject", "LocalVariableOfConcreteClass", "MethodWithMoreThanThreeNegations", "OverlyComplexMethod", "SwitchStatement", "OverloadedVarargsMethod", "QuestionableName", "UnclearExpression"})
public final class PairingHeap<E extends Comparable<E>> implements Heap<E, PairingHeap<E>> {

    private SortedSet<PairingHeap<E>> children;
    private int order;
    private E id;

    public PairingHeap(final Iterable<E> items) {
        final Iterator<E> iterator = items.iterator();
        setId(iterator.next());
        iterator.forEachRemaining(this::add);
    }

    public PairingHeap(final E... items) {
        this(items[0]);
        for (int i = 1; i < items.length; i++)
            add(items[i]);
    }

    private PairingHeap(final E payload) {
        setId(payload);
    }

    private PairingHeap(final E payload, final Collection<PairingHeap<E>> children) {
        this(payload);
        this.children = new TreeSet<>();
        this.children.addAll(children);
    }

    private PairingHeap(final E payload, final PairingHeap<E>... node) {
        this(payload);
        children = new TreeSet<>();
        Collections.addAll(children, node);
    }

    @SuppressWarnings("SwitchStatementDensity")
    private static <E extends Comparable<E>> PairingHeap<E> merge(final PairingHeap<E> a, final PairingHeap<E> b) {
        if (a.getId().isPresent() && !b.getId().isPresent()) return b;
        else if (b.getId().isPresent() && !a.getId().isPresent()) return a;

        switch (a.getId().get().compareTo(b.getId().get())) {
            case 1:
                if (!b.getChildren().isEmpty()) b.children = new TreeSet<>();
                if (a.getChildren().isEmpty() && !a.children.isEmpty())
                    b.children.addAll(a.children);
                return b;
            default:
                if (!a.getChildren().isEmpty()) a.children = new TreeSet<>();
                if (b.getChildren().isEmpty() && !b.children.isEmpty())
                    a.children.addAll(b.children);
                return a;
        }
    }

    private static <E extends Comparable<E>> Optional<PairingHeap<E>> merge(final PairingHeap<E>... heaps) {
        return Arrays.stream(heaps).reduce(PairingHeap::merge);
    }

    private static <E extends Comparable<E>> Optional<PairingHeap<E>> merge(final Collection<PairingHeap<E>> heaps) {
        return heaps.stream().reduce(PairingHeap::merge);
    }


    /**
     * Set children to children.
     *
     * @param children
     */

    @SuppressWarnings("PublicMethodNotExposedInInterface")
    public void setChildren(final Collection<PairingHeap<E>> children) {
        this.children.clear();
        this.children.addAll(children);
    }

    /**
     * @return
     */

    @Override
    public int getOrder() {
        return order;
    }

    /**
     * @param val
     */

    @Override
    public void setOrder(final int val) {
        order = val;
    }

    /**
     * Insert an element of type E into this {@link PairingHeap}.
     */

    @SuppressWarnings({"PublicMethodNotExposedInInterface", "LawOfDemeter"})
    @Override
    public void add(final E item) {
        final PairingHeap<E> newRoot = merge(this, new PairingHeap<E>(item));
        children = newRoot.children;
        order = newRoot.order;
        setId(newRoot.getId().orElse(null));
    }


    @SuppressWarnings("OverlyComplexBooleanExpression")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (!getClass().equals(obj.getClass()))) return false;
        if (!super.equals(obj)) return false;

        final Tree that = (Tree) obj;

        return ((getChildren() != null) && getChildren()
                .equals(that.getChildren())) || ((getChildren() == null) && (that
                .getChildren() == null));
    }

    @SuppressWarnings("ConditionalExpression")
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = (31 * result) + ((getChildren() != null) ? getChildren()
                .hashCode() : 0);
        return result;
    }

    @SuppressWarnings("ConditionalExpression")
    @Override
    public Optional<E> getId() {
        return (id != null) ? Optional.of(id) : Optional.empty();
    }

    @Override
    public void setId(final E id) {

    }

    @Override
    public Collection<PairingHeap<E>> getChildren() {
        return children;
    }

    @Override
    public Optional<E> findMin() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return MessageFormat
                .format("PairingHeap<children={0}>", children.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
    }
}

