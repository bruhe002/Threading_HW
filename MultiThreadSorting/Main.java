import java.io.*;

public class Main {
    // Global Arrays: Initial and Resulting
    public static int[] array = {7, 12, 19, 3, 18, 4, 2, 6, 15, 8};
    public static int[] new_array = new int[array.length];

    // Function used to print arrays
    public static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        // Set up two SortingThread Objects
        SortingThread s0 = new SortingThread(array, 0, 5, 1);
        SortingThread s1 = new SortingThread(array, 5, 10, 2);

        // SortingThread Class implements Runnable
        // Need to create Thread Objects
        Thread thread0 = new Thread(s0);
        Thread thread1 = new Thread(s1);

        // Start both threads to run simultaneously
        thread0.start();
        thread1.start();

        // Wait for both threads to finish in order to continue the code
        // WITHOUT THIS, DATA CAN BE UNPREDICTABLE
        // REST OF CODE WILL RUN WHILE SORTING IS STILL OCCURING
        try{
            thread0.join();
            thread1.join();
        } catch (InterruptedException e){}

        // Print Arrays to check if sorting occrued
        printArr(s0.getArray());
        printArr(s1.getArray());

        // Create a MergingThread Object
        Runnable m1 = new MergingThread(s0.getArray(), s1.getArray(), new_array);

        // Convert to thread
        Thread thread2 = new Thread(m1);

        // Start the thread
        thread2.start();
        try{
            thread2.join(); // Wait for it to finish before you print result
        } catch (InterruptedException e){}

        // Print Result
        printArr(new_array);
    }

}
