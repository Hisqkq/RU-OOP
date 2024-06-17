package mergesort;

import java.util.Arrays;

public class ParallelMergeSort {
    private static final int THRESHOLD = 10000;

    public static void sort(int[] array) {
        if (array.length > 1) {
            if (array.length <= THRESHOLD) {
                MergeSort.sort(array);
            } else {
                int[] firstHalf = Arrays.copyOf(array, array.length / 2);
                int[] secondHalf = Arrays.copyOfRange(array, array.length / 2, array.length);

                Thread firstThread = new Thread(new ParallelRun(firstHalf));
                Thread secondThread = new Thread(new ParallelRun(secondHalf));

                firstThread.start();
                secondThread.start();

                try {
                    firstThread.join();
                    secondThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MergeSort.merge(firstHalf, secondHalf, array);
            }
        }
    }

    public static class ParallelRun implements Runnable {
        public int[] array;
        public ParallelRun(int [] array) {
            this.array = array;
        }
        @Override
        public void run(){
            sort(array);
        }
    }
}
