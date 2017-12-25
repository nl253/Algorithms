package cryptography.ciphers;

/**
 * @author norbert
 */

@SuppressWarnings({"CallToSuspiciousStringMethod", "AssignmentToLambdaParameter", "WeakerAccess", "JavaDoc", "LambdaBodyCanBeCodeBlock", "TypeMayBeWeakened", "PublicMethodNotExposedInInterface"})
public final class CaesarCipher extends BaseCipher {

    private static final int SHIFT_LEN = 10;

    /**
     * Encode the message.
     *
     * @param message
     * @return
     */

    public static String encode(final String message, final int shift) {
        return message.chars().map(Character::getNumericValue)
                .map((int x) -> x + shift)
                .collect(String::new, (String str, int i) -> str += String
                        .valueOf(i), (String str1, String str2) -> str1 += str2);
    }

    @Override
    public String encode(final String msg) {
        return encode(msg, SHIFT_LEN);
    }

    /**
     * Decode a message.
     *
     * @param message
     * @return
     */

    private static String decode(final String message, final int shift) {
        return message.chars().map(Character::getNumericValue)
                .map((int x) -> x - shift)
                .collect(String::new, (String str, int i) -> str += String
                        .valueOf(i), (String str1, String str2) -> str1 += str2);
    }

    @Override
    public String decode(final String msg) {
        return decode(msg, SHIFT_LEN);
    }
}
