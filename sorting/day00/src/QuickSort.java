import org.junit.Test;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * Best-case runtime:nlogn
     * Worst-case runtime:nlogn
     * Average-case runtime:n^2
     * <p>
     * Space-complexity:logn
     */
    @Override
    public int[] sort(int[] array) {
        int count = 0;
        int order = 1;
        for (int i = 0; i< array.length-1;i++){
            if (array[i]>array[i+1]){
                count++;
                order = 1;
            }
            else if (array[i]<array[i+1]){
                count --;
                order = -1;
            }
            else {
                count = count + order;
            }
        }
        if (Math.abs(count)==array.length-1){
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param low  The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int low, int high) {
        if ((high - low + 1) < INSERTION_THRESHOLD) {
            InsertionSort is = new InsertionSort();
            int[] tempp = new int[high - low + 1];
            System.arraycopy(a, low, tempp, 0, high - low + 1);
            is.sort(tempp);
            System.arraycopy(tempp, 0, a, low, high - low + 1);
        } else if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(a, low, high);
            // Recursively sort elements before
            // partition and after partition
            quickSort(a, low, pi - 1);
            quickSort(a, pi + 1, high);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param low  The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int n = low;
        for (int i = low; i <= high; i++) {
            if (array[i] < pivot) {
                n = n + 1;
                int temp = array[n];
                array[n] = array[i];
                array[i] = temp;

            }
        }
        int temp = array[n];
        array[n] = array[low];
        array[low] = temp;

        return n;
    }


    @Test
    public void testmy() {
        //int[] hey = new int[] {1,3,8,7,1,9,3,3,};
        //hey = sort(hey);
        //for (int i:hey){
        //  System.out.println(i);
        //}
        int[] hey = new int[]{4, 1, 2, 7, 1, 9, 3, 3};
        sort(hey);
        for (int i : hey) {
            System.out.println(i);
        }

    }
}
