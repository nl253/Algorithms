package sorts;

import java.util.ArrayList;
import java.util.Collection;
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

    @SuppressWarnings("FieldNamingConvention")
    private final Logger log = Logger.getAnonymousLogger();

    /**
     * The method that sorting algorithms need to implement.
     */

    @SuppressWarnings("OverloadedMethodsWithSameNumberOfParameters")
    final List<E> sort(final Collection<E> data) {
        return sort(new ArrayList<>(data));
    }

    abstract List<E> sort(List<E> data);
}

