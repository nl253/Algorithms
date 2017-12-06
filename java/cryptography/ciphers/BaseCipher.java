package cryptography.ciphers;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Base class for all Ciphers.
 */

@SuppressWarnings({"ClassHasNoToStringMethod", "NewClassNamingConvention", "AssertStatement", "AbstractClassWithOnlyOneDirectInheritor", "AbstractClassWithoutAbstractMethods", "PackageVisibleField", "AlibabaConstantFieldShouldBeUpperCase", "FieldNamingConvention", "UtilityClassCanBeEnum"})
abstract class BaseCipher {

    private static final long MSGLEN = 1000L;
    private static final Random random = new SecureRandom();
    @SuppressWarnings("PackageVisibleField")
    static final CharSequence message = random.ints(MSGLEN, 60, 130)
            .mapToObj(String::valueOf).collect(Collectors.joining());
}
