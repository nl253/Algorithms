package cryptography.ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class CaesarCipherTest {

    private CaesarCipher cipher;


    @BeforeEach
    void setUp() {
        cipher = new CaesarCipher();
    }

    @Test
    void encode() {
        CaesarCipher.decode(CaesarCipher.encode());
    }

    @Test
    void decode() {
        final String encoded = CaesarCipher.encode();
        CaesarCipher.decode(encoded).equals(CaesarCipher.message);
    }

}
