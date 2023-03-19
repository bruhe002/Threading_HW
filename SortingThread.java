import java.io.*;

public class SortingThread implements Runnable {
    private int[] sort_arr;
    private int id;
    public SortingThread(int[] arr, int begin, int end, int num) {
        int length = end - begin;
        sort_arr = new int[length];
        for(int i = 0; i < length; i++) {
            sort_arr[i] = arr[begin];
//            System.out.println("The current number is: " + arr[begin]);
            begin++;
        }
        id = num;
    }

    public void run() {
        int length = sort_arr.length;
        for(int i = 1; i < length; i++) {
            System.out.println("Sorting Array for thread: " + id);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){}
            for(int j = i; j > 0 && sort_arr[j - 1] > sort_arr[j]; j--) {
//                System.out.println(sort_arr[j]);
                int temp = sort_arr[j];
                sort_arr[j] = sort_arr[j - 1];
                sort_arr[j - 1] = temp;
            }
        }
    }

    public int[] getArray() {
        return sort_arr;
    }
}


