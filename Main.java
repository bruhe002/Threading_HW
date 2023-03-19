import java.io.*;

public class Main {
    public static int[] array = {7, 12, 19, 3, 18, 4, 2, 6, 15, 8};
    public static int[] new_array = new int[array.length];


    public static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        SortingThread s0 = new SortingThread(array, 0, 5, 1);
        SortingThread s1 = new SortingThread(array, 5, 10, 2);

        Thread thread0 = new Thread(s0);
        Thread thread1 = new Thread(s1);

        thread0.start();
        thread1.start();

        try{
            thread0.join();
            thread1.join();
        } catch (InterruptedException e){}


        printArr(s0.getArray());
        printArr(s1.getArray());

        Runnable m1 = new MergingThread(s0.getArray(), s1.getArray(), new_array);

        Thread thread2 = new Thread(m1);
        thread2.start();
        try{
            thread2.join();
        } catch (InterruptedException e){}

        printArr(new_array);
    }

}
