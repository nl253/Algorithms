package sorts;

import java.util.List;
import java.util.logging.Logger;

/**
 * Base class for all sorting algorithms, defines the {@code void sort()}
 * method that needs to be implemented and provides utility functions.
 *
 * @param <E>
 */

@SuppressWarnings({"ClassHasNoToStringMethod", "AbstractClassWithOnlyOneDirectInheritor", "AbstractClassNeverImplemented", "ProtectedField", "PackageVisibleField", "NestedAssignment", "UnsecureRandomNumberGeneration", "unchecked", "RedundantTypeArguments", "PublicConstructorInNonPublicClass", "ConstructorNotProtectedInAbstractClass", "WeakerAccess"})
abstract class BaseSortingAlgorithm<E extends Comparable<E>> {

    private final List<E> unsortedData;

    /**
     * @param unsortedData {@link List} of {@link Comparable} items to sort
     */

    protected BaseSortingAlgorithm(final List<E> unsortedData) {
        this.unsortedData = unsortedData;
    }

    /** {@link Logger} for the class */
    @SuppressWarnings("FieldNamingConvention")
    private final Logger log = Logger.getAnonymousLogger();

    /**
     * @return unsorted data ({@link List} of {@link Comparable}s)
     */

    final List<E> getUnsortedData() {
        return unsortedData;
    }

    /**
     * Implementation of the compulsory {@code void sort()} method.
     */

    abstract void sort();

    final List<E> sorted() {
        sort();
        return unsortedData;
    }
}

