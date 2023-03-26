import java.io.*;
import java.util.*;

public class Main {
    // Global Arrays: Initial and Resulting
    public static int[] array;
    public static int[] new_array;

    static boolean errorFlag;

    // Function used to print arrays
    public static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        // Greeting User
        System.out.println("\nWelcome to Mutlithread Sorting!!");

        // Set up variables for input
        Scanner arrayScan = new Scanner(System.in); // Scanner for reading input
        String input; // The input given by a user
        ; // Grabs the numbers entered from input and separates them via space into this array

        do {
            errorFlag = false; // Reset error
            System.out.println("Please enter your array (separate with spaces)");

            // Obtain the array from input
            input = arrayScan.nextLine();
            String[] inputArray = input.split(" ", 0);

            array = new int[inputArray.length];
            new_array = new int[inputArray.length];

            try {
                for(int i = 0; i < inputArray.length; i++) {
                    array[i] = Integer.parseInt(inputArray[i]);
                }
            } catch (Exception e) {
                System.out.println("Element is not a valid number.\nPlease try again.");
                errorFlag = true;
            }
        } while(errorFlag);

        // Set up two SortingThread Objects
        // See SortingThread class for information on parameters
        int mid = array.length / 2;

        SortingThread s0 = new SortingThread(array, 0, mid, 1);
        SortingThread s1 = new SortingThread(array, mid, array.length, 2);

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
