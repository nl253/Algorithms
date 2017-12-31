package cryptography.ciphers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ClassHasNoToStringMethod", "FeatureEnvy", "CallToSuspiciousStringMethod", "AbstractClassWithOnlyOneDirectInheritor"})
abstract class BaseCipherTest {

    @SuppressWarnings("PackageVisibleField")
    BaseCipher cipher;

    // @formatter:off

    @Test
    final void encode() {

        final String randomMsg = BaseCipher.randomMsg();

        Assertions.assertEquals(
                cipher.decode(cipher.encode(randomMsg)),
                randomMsg,
                "Encoding / Decoding failed");
    }

    @Test
    final void decode() {

        final String encoded = cipher.encode();

        Assertions.assertEquals(
                cipher.encode(cipher.decode(encoded)),
                encoded, "Encoding / Decoding failed");
    }

}
