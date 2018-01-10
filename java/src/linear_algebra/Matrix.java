package linear_algebra;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;

/**
 * @author nl253
 */

@SuppressWarnings({"AssertStatement", "StandardVariableNames", "MethodCanBeVariableArityMethod", "MethodWithMultipleLoops", "NewMethodNamingConvention"})
public final class Matrix {

    private Matrix() {}

    @SuppressWarnings("ConstantConditions")
    public static double product(final double[][] matrix) {
        return fold(matrix, (x, y) -> x * y);
    }

    @SuppressWarnings("ConstantConditions")
    public static double sum(final double[][] matrix) {
        return fold(matrix, (x, y) -> x + y);
    }

    @SuppressWarnings("ConstantConditions")
    public static double max(final double[][] matrix) {
        return fold(matrix, Math::max);
    }

    @SuppressWarnings("ConstantConditions")
    public static double min(final double[][] matrix) {
        return fold(matrix, Math::min);
    }

    /**
     * @param matrix
     * @param funct
     * @return
     */

    @SuppressWarnings("ConstantConditions")
    public static double fold(final double[][] matrix, final DoubleBinaryOperator funct) {
        return Arrays.stream(matrix)
                .map((double[] x) -> Arrays.stream(x).reduce(funct)
                        .getAsDouble()).reduce(funct::applyAsDouble).get();
    }


    /**
     * @param matrix
     * @return
     */

    public static String toString(final double[][] matrix) {
        return Arrays.stream(matrix)
                .map((double[] x) -> Arrays.stream(x).mapToObj(String::valueOf)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(System.lineSeparator()));
    }


    /**
     * @param vects
     * @return
     */

    public static double[][] stackVectors(final double[]... vects) {

        for (int i = 1; i < vects.length; i++)
            assert vects[i].length == vects[i - 1].length : "Vectors are not of the same length";

        final double[][] result = new double[vects.length][vects[0].length];

        for (int i = 0; i < vects.length; i++) result[i] = vects[i];

        return result;
    }

    public static double[][] zeros(final int dim1, final int dim2) {
        return fill(dim1, dim2, 0.0d);
    }

    public static double[][] ones(final int dim1, final int dim2) {
        return fill(dim1, dim2, 1.0d);
    }

    @SuppressWarnings("WeakerAccess")
    public static double[][] fill(final int dim1, final int dim2, final double value) {
        final double[][] matrix = new double[dim1][dim2];
        for (int i = 0; i < dim1; i++)
            for (int j = 0; j < dim2; j++)
                matrix[i][j] = value;
        return matrix;
    }

    public static double[][] rotate90(final double[][] matrix) {
        return matrix;
    }

    /**
     * @param matrix (2d double array)
     * @return
     */

    public static double[][] swapAxes(final double[][] matrix) {
        saneSize(matrix, 2);

        final double[][] result = new double[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                result[j][i] = matrix[i][j];

        return result;
    }


    @SuppressWarnings("ConstantConditions")
    public static double[][] applyToAll(final double[][] matrix, final DoubleFunction<Double> func) {
        final double[][] result = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result[i].length; j++)
                result[i][j] = func.apply(result[i][j]);

        return result;
    }

    @SuppressWarnings("ConstantConditions")
    public static void forEach(final double[][] matrix, final DoubleConsumer consumer) {
        for (final double[] i : matrix)
            for (final double j : i)
                consumer.accept(j);
    }


    /**
     * Apply a {@link DoubleBinaryOperator} along the second axis.
     *
     * @param matrix (2d double array)
     * @param func a function that accepts two doubles and returns a double
     * @return a vector with the inner dimension folded using func
     */

    @SuppressWarnings("ConstantConditions")
    public static double[] applyAlongAxis(final double[][] matrix, final DoubleBinaryOperator func) {
        final double[] result = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++)
            result[i] = Arrays.stream(matrix[i]).reduce(func).getAsDouble();

        return result;
    }

    @SuppressWarnings({"NewMethodNamingConvention", "ConditionalExpression"})
    public static double[][] identity(final int size) {
        final double[][] result = new double[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                result[i][j] = (i == j) ? 1.0d : 0.0d;

        return result;
    }

    /**
     * Compute the trace ie  the sum of elements along the main axis.
     *
     * @param matrix (2d double array)
     * @return the trace ie the sum of elements along the main axis
     */

    public static double trace(final double[][] matrix) {
        saneTrace(matrix);

        double result = 0.0d;

        for (int i = 0; i < matrix.length; i++)
            result += matrix[i][i];

        return result;
    }

    /**
     * Matrix multiplication.
     *
     * @param matrixA first matrix (2d double array)
     * @param matrixB second matrix (2d double array)
     * @return resultant matrix (2d double array)
     */

    public static double[][] multiply(final double[][] matrixA, final double[][] matrixB) {
        saneMult(matrixA, matrixB);

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
     * @param matrix input Matrix (2d double array)
     * @return determinant of matrix
     */

    public static double determinant(final double[][] matrix) {
        saneDeterminant(matrix);

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

    private static void saneMult(final double[][] matrixA, final double[][] matrixB) {
        saneShape(matrixA);
        saneShape(matrixB);
    }

    private static void saneTrace(final double[][] matrix) {
        saneShape(matrix);
        saneSize(matrix, 1);
    }

    private static void saneDeterminant(final double[][] matrix) {
        saneShape(matrix);
        saneSize(matrix, 2);
    }


    private static void saneShape(final double[][] matrix) {
        assert (matrix.length > 0) && (matrix.length == matrix[0].length) : MessageFormat
                .format("The matrix must be square to compute the trace you passed a {0} x {1}", matrix.length, matrix[0].length);
    }

    private static void saneSize(final double[][] matrix, final int minSize) {
        assert (matrix.length >= minSize) : MessageFormat
                .format("The matrix must have at leas {0} elements", minSize);
    }

}
