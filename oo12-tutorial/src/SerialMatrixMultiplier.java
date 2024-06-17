public class SerialMatrixMultiplier implements MatrixMultiplier {
    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        Matrix result = emptyResult(a, b);

        for (int i = 0; i < a.getRowCount(); ++i) {
            for (int j = 0; j < b.getColumnCount(); ++j) {
                result.setValue(i, j, computeEntry(a, b, i, j));
            }
        }

        return result;
    }

    public static Matrix emptyResult(Matrix a, Matrix b) {
        if (a.getColumnCount() != b.getRowCount()) {
            throw new IllegalArgumentException(
                "Matrix multiplication is not defined for matrix with " + a.getColumnCount() +
                " columns and " + b.getRowCount() + " rows!");
        }

        return new Matrix(a.getRowCount(), b.getColumnCount());
    }

    public static double computeEntry(Matrix a, Matrix b, int row, int column) {
        double sum = 0;
        for (int i = 0; i < a.getColumnCount(); ++i) {
            sum += a.getValue(row, i) * b.getValue(i, column);
        }
        return sum;
    }
}
