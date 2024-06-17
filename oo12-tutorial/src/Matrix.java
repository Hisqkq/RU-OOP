public class Matrix {
    private double[][] data;
    private final int rows, columns;

    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Row and column count must be strictly postive!");
        }

        data = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public double getValue(int row, int column) {
        return data[row][column];
    }

    public void setValue(int row, int column, double value) {
        data[row][column] = value;
    }

    public int getRowCount() {
        return rows;
    }

    public int getColumnCount() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getRowCount(); ++i) {
            builder.append("[\t");
            for (int j = 0; j < getColumnCount(); ++j) {
                builder.append(getValue(i, j) + "\t");
            }
            builder.append("]\n");
        }

        return builder.toString();
    }

    public Matrix transpose() {
        Matrix result = new Matrix(getColumnCount(), getRowCount());

        for (int i = 0; i < getRowCount(); ++i) {
            for (int j = 0; j < getColumnCount(); ++j) {
                result.setValue(j, i, getValue(i, j));
            }
        }

        return result;
    }

    public static Matrix generateMatrix(int rows, int columns) {
        Matrix result = new Matrix(rows, columns);

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                result.setValue(i, j, Math.random());
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;

        Matrix other = (Matrix)obj;

        for (int i = 0; i < getRowCount(); ++i)
            for (int j = 0; j < getColumnCount(); ++j)
                if (getValue(i, j) != other.getValue(i, j))
                    return false;

        return true;
    }
}
