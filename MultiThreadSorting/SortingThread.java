import java.io.*;

public class SortingThread implements Runnable {
    private int[] sort_arr;
    private int id;

    // Define Constructor
    /*
    * PARAMETERS:
    *   int[] arr: used for data
    *   int begin: the starting index to read data
    *   int end: the upper bound index to read data
    *   int num: numerical identifier
    */
    public SortingThread(int[] arr, int begin, int end, int num) {
        int length = end - begin; // Establish new length
        sort_arr = new int[length]; //Create new array with new length

        // Begin to add elements into the array
        for(int i = 0; i < length; i++) {
            sort_arr[i] = arr[begin];
//            System.out.println("The current number is: " + arr[begin]);
            begin++; // Make begin and i increment together
        }
        id = num; // Set id
    }

    // Run Method
    public void run() {
        int length = sort_arr.length; // Initialize Length variable to save typing

        // Sort array using Insertion Sorting algorithm: O(n^2)
        for(int i = 1; i < length; i++) {
            System.out.println("Sorting Array for thread: " + id); // Display that thread is currently working
            try{
                Thread.sleep(1000); // Let the process work so user can see what is happening
            } catch (InterruptedException e){}
            for(int j = i; j > 0 && sort_arr[j - 1] > sort_arr[j]; j--) {
//                System.out.println(sort_arr[j]);
                // SWAP The two elements if greater than
                int temp = sort_arr[j];
                sort_arr[j] = sort_arr[j - 1];
                sort_arr[j - 1] = temp;
            }
        }
    }

    // Returns the Objects private array variable
    public int[] getArray() {
        return sort_arr;
    }
}


