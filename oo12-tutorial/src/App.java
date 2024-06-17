public class App {
    public static void main(String[] args) throws Exception {
        int rows = 1400, columns = 1300;
        Matrix m1 = Matrix.generateMatrix(rows, columns);
        Matrix m2 = Matrix.generateMatrix(columns, rows);

        Matrix result1 = benchmarkMultiplier(m1, m2, new SerialMatrixMultiplier());
        Matrix result2 = benchmarkMultiplier(m1, m2, new ParallelMatrixMultiplier(12));

        if (!result1.equals(result2)) {
            System.err.println("Benchmark failed!");
        }
    }

    public static Matrix benchmarkMultiplier(Matrix a, Matrix b, MatrixMultiplier mult) {
        long start = System.currentTimeMillis();
        Matrix result = mult.multiply(a, b);
        long end = System.currentTimeMillis();

        System.out.println(mult.getClass().getName() + " took " + (end-start) + "ms");
        return result;
    }
}
