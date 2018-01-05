package data_structures;

/**
 * @author nl253
 */

@SuppressWarnings({"AssertStatement", "StandardVariableNames", "MethodCanBeVariableArityMethod"})
public final class Matrix {

    private Matrix() {}

    /**
     * Matrix multiplication.
     *
     * @param matrixA first matrix (2d array)
     * @param matrixB second matrix (2d array)
     * @return resultant matrix (2d array)
     */

    @SuppressWarnings({"MethodWithMultipleLoops", "SuspiciousArrayMethodCall"})
    public static double[][] multiply(final double[][] matrixA, final double[][] matrixB) {
        assert canMultiply(matrixA, matrixB) : "[ERROR] Matrix multiplication is not defined on these matrices";

        final double[][] result = new double[matrixA[0].length][matrixB.length];

        for (int row = 0; row < result.length; row++)
            for (int column = 0; column < result[row].length; column++)
                for (int scalarIndex = 0; scalarIndex < matrixA[row].length; scalarIndex++)
                    result[row][column] += (matrixA[row][scalarIndex] * matrixB[scalarIndex][column]);
        return result;
    }

    /**
     * Calculate the determinant of a square matrix (a 2d array of size n x n where n is greater than 1).
     *
     * @param matrix input Matrix (2d array)
     * @return determinant of matrix
     */

    @SuppressWarnings({"AlibabaUndefineMagicConstant", "ContinueStatement", "WeakerAccess", "MethodWithMultipleLoops"})
    public static double determinant(final double[][] matrix) {
        assert isSquare(matrix) : "[ERROR] The matrix is not square.";
        assert matrix.length > 1 : "You must have a n x n matrix with n > 1";

        if (matrix.length == 2)
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);

        double result = 0.0d;

        final int matrixEdgeLen = matrix.length;
        final int subMatrixEdgeLen = matrixEdgeLen - 1;

        for (int i = 0; i < matrixEdgeLen; i++) {

            final double[][] subMatrix = new double[subMatrixEdgeLen][subMatrixEdgeLen];

            for (int dim1 = 1; dim1 < matrixEdgeLen; dim1++) {

                boolean seen = false;

                for (int dim2 = 0; dim2 < matrixEdgeLen; dim2++) {

                    if (dim2 == i) seen = true;

                    else if (seen)
                        subMatrix[dim1 - 1][dim2 - 1] = matrix[dim1][dim2];

                    else subMatrix[dim1 - 1][dim2] = matrix[dim1][dim2];
                }
            }

            //noinspection UnclearExpression,ConditionalExpression,ImplicitNumericConversion
            result += ((i % 2 == 0) ? 1 : -1) * matrix[0][i] * determinant(subMatrix);
        }
        return result;
    }

    private static boolean canMultiply(final double[][] matrixA, final double[][] matrixB) {
        return isSquare(matrixA) && isSquare(matrixB) && (matrixA.length == matrixB[0].length);
    }

    private static boolean isSquare(final double[][] matrix) {
        return (matrix.length > 0) && (matrix.length == matrix[0].length);
    }
}
