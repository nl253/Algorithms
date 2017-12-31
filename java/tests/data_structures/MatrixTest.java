package data_structures;

import java.text.MessageFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"PackageVisibleField", "MagicNumber", "ClassHasNoToStringMethod"})
class MatrixTest {

    // @formatter:off

    private final double[][] matrix = {
            {1.0, 2.0, 3.0},
            {2.0, 1.0, 1.0},
            {3.0, 2.0, 3.0}
    };

    @Test
    final void multiply() {

        final double[][] expectedMatMult = {
                {14.0, 10.0, 14.0},
                {7.0, 7.0, 10.0},
                {16.0, 14.0, 20.0}
        };

        final String errorMsg = "Failed to perform matrix multiplication.";

        Assertions.assertArrayEquals(
                expectedMatMult, Matrix.multiply(matrix, matrix), errorMsg);
    }

    @Test
    final void determinant() {
        final double expectedDeterminant = -2.0d;
        final double gotDeterminant = Matrix.determinant(matrix);
        final String errorMsg = MessageFormat.format("Determinant expected: {0} but got: {1}", expectedDeterminant, gotDeterminant);

        Assertions.assertTrue(gotDeterminant <= expectedDeterminant, errorMsg);
    }

    // @formatter:on
}
