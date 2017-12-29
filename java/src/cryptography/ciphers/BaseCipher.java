package cryptography.ciphers;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Base class for all Ciphers.
 */

@SuppressWarnings({"ClassHasNoToStringMethod", "NewClassNamingConvention", "AssertStatement", "AbstractClassWithOnlyOneDirectInheritor", "AbstractClassWithoutAbstractMethods", "PackageVisibleField", "AlibabaConstantFieldShouldBeUpperCase", "FieldNamingConvention", "UtilityClassCanBeEnum"})
abstract class BaseCipher {

    private static final int ASCII_LOWER_BOUND = 60;
    private static final int ASCII_UPPER_BOUND = 130;
    private static final long DEFAULT_MSG_LEN = 1000L;
    private static final Random random = new SecureRandom();

    /**
     * Encode msg.
     *
     * @param msg message
     * @return encoded message
     */

    @SuppressWarnings("WeakerAccess")
    public abstract String encode(String msg);

    /**
     * Decode msg.
     *
     * @param msg message
     * @return decoded message
     */

    public abstract String decode(String msg);

    public final String encode() {
        return encode(randomMsg());
    }

    @SuppressWarnings({"PackageVisibleField", "WeakerAccess"})
    protected static String randomMsg() {
        return randomMsg(DEFAULT_MSG_LEN);
    }

    /**
     * Generate a random message.
     * To be used internally by {@link BaseCipher} implementations to encode() without parameters.
     *
     * @param msgLen the length of the message
     * @return a random message
     */

    @SuppressWarnings("WeakerAccess")
    protected static String randomMsg(final long msgLen) {
        return random.ints(msgLen, ASCII_LOWER_BOUND, ASCII_UPPER_BOUND)
                .mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
