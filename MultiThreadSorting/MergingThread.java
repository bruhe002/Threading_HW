public class MergingThread implements Runnable {
    private int[] arr1;
    private int[] arr2;
    private int[] result;

    // Merging Thread Constructor
    /*
    * PARAMETERS
    *   int[] left: Left array to be combined.
    *   int[] right: Right array to be combined.
    *   int[] newArr: New Array to combine, and sort, left and right
    */
    public MergingThread(int[] left, int[] right, int[] newArr) {
        arr1 = left;
        arr2 = right;
        result = newArr;
    }

    // Run Function
    public void run() {
        // Initialize arr1 and arr2 indexes (i and j respectively)
        int i = 0;
        int j = 0;

        // Traverse through each array
        // For each iteration place the smallest current element into new array
        for(int k = 0; k < result.length; k++) { // result array should equal arr1.length + arr2.length
            // If we have not reached the end of either arrays
            if(i != arr1.length && j != arr2.length){
                //Check if which current element is stronger
                // Add to the result array
                // Increment the respective index variable
                if(arr1[i] <= arr2[j]) {
                    result[k] = arr1[i];
                    i++;
                } else {
                    result[k] = arr2[j];
                    j++;
                }
            }
            // if i has reached the end of its array
            else if(i == arr1.length) {
                // Only add arr2
                result[k] = arr2[j];
                j++;
            }
            // if j has reached the end of its array
            else if(j == arr2.length) {
                // Only add arr1
                result[k] = arr1[i];
                i++;
            }

        }
    }
}
