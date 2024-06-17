public class ParallelMatrixMultiplier implements MatrixMultiplier {
    private final int threadCount;

    public ParallelMatrixMultiplier(int threadCount) {
        this.threadCount = threadCount;
    }

    public class RowMultiplier implements Runnable {
        private final int thread;
        private Matrix a, b, result;
        public RowMultiplier(int thread, Matrix a, Matrix b, Matrix result) {
            this.thread = thread;
            this.a = a;
            this.b = b;
            this.result = result;
        }

        @Override
        public void run() {
            for (int i = thread; i < a.getRowCount(); i += threadCount) {
                for (int j = 0; j < b.getColumnCount(); ++j) {
                    result.setValue(i, j, SerialMatrixMultiplier.computeEntry(a, b, i, j));
                }
            }
        }
    }

    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        Matrix result = SerialMatrixMultiplier.emptyResult(a, b);

        Thread[] threads = new Thread[threadCount];
        for (int threadNr = 0; threadNr < threadCount; ++threadNr) {
            Thread thread = new Thread(new RowMultiplier(threadNr, a, b, result));
            threads[threadNr] = thread;
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) { }
        }

        return result;
    }
    
}
