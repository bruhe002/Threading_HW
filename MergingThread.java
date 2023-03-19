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

        for(int k = 0; k < result.length; k++) {
            if(i != arr1.length && j != arr2.length){
                if(arr1[i] <= arr2[j]) {
                    result[k] = arr1[i];
                    i++;
                } else {
                    result[k] = arr2[j];
                    j++;
                }
            } else if(i == arr1.length) {
                result[k] = arr2[j];
                j++;
            } else if(j == arr2.length) {
                result[k] = arr1[i];
                i++;
            }

        }
    }
}
