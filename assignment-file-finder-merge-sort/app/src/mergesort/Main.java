/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mergesort;

import java.util.Arrays;

public class Main {

    private static final int L = 10000000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      long time1, time2;
      int [] a1 = new int[L];
      int [] a2 = new int[L];
      for (int i = 0; i < L; i += 1) {
        a1[i] = a2[i] = (int) (Math.random() * L);
      }
      System.out.print("sequential sort: ");
      time1 = System.currentTimeMillis();
      MergeSort.sort(a1);
      time2 = System.currentTimeMillis();
      System.out.println((time2 - time1) + " millis. Array is sorted = " + MergeSort.isSorted(a1));
      
      System.out.print("parallel sort: ");
      time1 = System.currentTimeMillis();
      ParallelMergeSort.sort(a2);
      time2 = System.currentTimeMillis();
      System.out.println((time2 - time1) + " millis. Array is sorted = " + MergeSort.isSorted(a2));
      System.out.println("sorter arrays equals = " + Arrays.equals(a1, a2));
      System.out.println("The number of processors is " + Runtime.getRuntime().availableProcessors());
    }
}


//sequential sort: 3130 millis. Array is sorted = true
//parallel sort: 1212 millis. Array is sorted = true
//sorter arrays equals = true
//The number of processors is 8