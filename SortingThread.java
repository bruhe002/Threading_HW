import java.io.*;

public class SortingThread implements Runnable {
    private int[] sort_arr;

    public SortingThread(int[] arr, int begin, int end) {
        int length = end - begin;
        sort_arr = new int[length];
        int idx = begin;
        for(int i = 0; i < length; i++) {
            sort_arr[i] = arr[idx];
            idx++;
        }
    }

    public void run() {
        int length = sort_arr.length;
        for(int i = 0; i < length; i++) {
            for(int j = i; j > 0 && sort_arr[j - 1] > sort_arr[j]; j--) {
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

public class MergingThread implements Runnable {
    private int[] arr1;
    private int[] arr2;
    private int[] result;

    public MergingThread(int[] left, int[] right, int[] newArr) {
        arr1 = left;
        arr2 = right;
        result = newArr;
    }

    public void run() {
        int i = 0;
        int j = 0;

        for(int k = 0; k < result; k++) {
            if(arr1[i] <= arr2[j]) {
                result[k] = arr1[i];
                i++;
            } else {
                result[k] = arr2[j];
                j++;
            }
        }
    }
}

public class Main {
    public static int[] array = {7, 12, 19, 3, 18, 4, 2, 6, 15, 8};
    public static int[] new_array = new int[array.length];
    public static void main(String[] args) {
        Runnable s0 = new SortingThread(array, 0, 5);
        Runnable s1 = new SortingThread(array, 5, 10);

        new Thread(s0).start();
        new Thread(s1).start();

        Runnable m1 = new MergingThread(s0.getArray(), s1.getArray(), new_array);

        new Thread(m1).start();

        for(int i = 0; i < new_array.length; i++) {
            System.out.println(new_array[i]);
        }
    }
}

