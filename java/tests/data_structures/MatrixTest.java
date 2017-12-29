package data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 */

@SuppressWarnings({"PackageVisibleField", "MagicNumber", "ClassHasNoToStringMethod"})
class MatrixTest {

    // @formatter:off
    private final double[][] matrix = {
            {1.0, 2.0, 3.0},
            {2.0, 1.0, 1.0},
            {3.0, 2.0, 3.0}
    };

    private final double[][] expectedMatMult = {
            {14.0, 10.0, 14.0},
            {7.0, 7.0, 10.0},
            {16.0, 14.0, 20.0}
    };
    // @formatter:on

    @Test
    final void multiply() {
        Assertions.assertArrayEquals(expectedMatMult, src.data_structures.Matrix
                .multiply(matrix, matrix), "Failed to perform matrix multiplication.");
    }

    @Test
    final void determinant() {
        Assertions.assertTrue(src.data_structures.Matrix
                                      .determinant(matrix) <= -2.0);
    }
}
