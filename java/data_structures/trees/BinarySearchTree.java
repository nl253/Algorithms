package data_structures.trees;

@SuppressWarnings({"unused", "ClassWithoutLogger", "PublicConstructor", "ParameterHidesMemberVariable", "ClassTooDeepInInheritanceTree", "InstanceMethodNamingConvention", "DesignForExtension"})
class BinarySearchTree<I extends Comparable<I>, V> extends BinaryTree<I, V> {

    @SuppressWarnings("WeakerAccess")
    BinarySearchTree(final I id, final V value) {
        super(id, value);
    }

    @SuppressWarnings({"ComparatorResultComparison", "ConstantConditions", "WeakerAccess", "MethodWithMultipleReturnPoints"})
    boolean add(final I id, final V value) {
        if (id.compareTo(getId().get()) == 0) return false;
        if (id.compareTo(getId().get()) == -1) {
            if (getLeft().isPresent())
                return ((BinarySearchTree<I, V>) getLeft().get())
                        .add(id, value);
            else setLeft(new BinarySearchTree<>(id, value));
        } else {
            if (getRight().isPresent())
                return ((BinarySearchTree<I, V>) getRight().get())
                        .add(id, value);
            else setRight(new BinarySearchTree<>(id, value));
        }
        return true;
    }
}
