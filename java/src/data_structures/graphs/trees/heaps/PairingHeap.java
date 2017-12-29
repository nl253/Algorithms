package data_structures.graphs.trees.heaps;

import com.google.common.collect.Streams;
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
import src.data_structures.graphs.trees.AbstractTree;

/**
 * @author nl253
 */

@SuppressWarnings({"StandardVariableNames", "ClassWithTooManyMethods", "LocalVariableHidesMemberVariable", "unchecked", "AccessingNonPublicFieldOfAnotherObject", "LocalVariableOfConcreteClass", "MethodWithMoreThanThreeNegations", "OverlyComplexMethod", "SwitchStatement", "OverloadedVarargsMethod", "QuestionableName", "UnclearExpression"})
public final class PairingHeap<E extends Comparable<E>> extends AbstractHeap<E> implements Comparable<PairingHeap<E>> {

    private SortedSet<AbstractHeap<E>> children;

    public PairingHeap(final Iterable<E> items) {
        final Iterator<E> iterator = items.iterator();
        setPayload(iterator.next());
        iterator.forEachRemaining(this::add);
    }

    public PairingHeap(final E... items) {
        this(items[0]);
        for (int i = 1; i < items.length; i++) add(items[i]);
    }

    private PairingHeap(final E payload) {
        setPayload(payload);
    }

    private PairingHeap(final E payload, final Iterable<PairingHeap<E>> children) {
        this(payload);
        this.children = new TreeSet<>();
        children.forEach(this.children::add);
    }

    private PairingHeap(final E payload, final PairingHeap<E>... node) {
        this(payload);
        children = new TreeSet<>();
        Collections.addAll(children, node);
    }

    @SuppressWarnings("SwitchStatementDensity")
    private static <E extends Comparable<E>> PairingHeap<E> merge(final PairingHeap<E> a, final PairingHeap<E> b) {
        if (a.isEmpty() && !b.isEmpty()) return b;
        else if (b.isEmpty() && !a.isEmpty()) return a;

        switch (a.getPayload().get().compareTo(b.getPayload().get())) {
            case 1:
                if (!b.getChildren().isPresent()) b.children = new TreeSet<>();
                if (a.getChildren().isPresent() && !a.children.isEmpty())
                    b.children.addAll(a.children);
                return b;
            default:
                if (!a.getChildren().isPresent()) a.children = new TreeSet<>();
                if (b.getChildren().isPresent() && !b.children.isEmpty())
                    a.children.addAll(b.children);
                return a;
        }
    }

    private static <E extends Comparable<E>> PairingHeap<E> merge(final PairingHeap<E>... heaps) {
        return Arrays.stream(heaps).reduce(PairingHeap::merge).get();
    }

    private static <E extends Comparable<E>> PairingHeap<E> merge(final Iterable<PairingHeap<E>> heaps) {
        return Streams.stream(heaps).reduce(PairingHeap::merge).get();
    }


    /**
     * Set children to children.
     *
     * @param children {@link AbstractTree} nodes
     */
    @SuppressWarnings("PublicMethodNotExposedInInterface")
    @Override
    public void setChildren(final Iterable<? extends Tree> children) {
        this.children.clear();
        children.forEach(e -> this.children.add((AbstractHeap<E>) e));

    }

    @SuppressWarnings("PublicMethodNotExposedInInterface")
    @Override
    public Optional<? extends Collection<? extends Tree>> getChildren() {
        return Optional.ofNullable(children);
    }

    /**
     * Insert an element of type E into this {@link PairingHeap}.
     */

    @SuppressWarnings("PublicMethodNotExposedInInterface")
    @Override
    public void insert(final E elem) {
        final PairingHeap<E> newRoot = merge(this, new PairingHeap<E>(elem));
        children = newRoot.children;
        setOrder(newRoot.getOrder());
        setPayload(newRoot.getPayload().orElse(null));
    }

    /**
     * Retrieve, and remove the smallest item of type E from the {@link AbstractHeap}.
     *
     * @return the smallest item
     */

    @Override
    E removeMin() {
        return null;
    }

    @SuppressWarnings("OverlyComplexBooleanExpression")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (!getClass().equals(obj.getClass()))) return false;
        if (!super.equals(obj)) return false;

        final PairingHeap<?> that = (PairingHeap<?>) obj;

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

    @Override
    public String toString() {
        return MessageFormat
                .format("PairingHeap<children={0}>", children.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
    }

    @Override
    public int compareTo(final PairingHeap<E> t) {
        return 0;
    }
}

