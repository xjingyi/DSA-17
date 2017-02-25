import org.junit.Test;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * <p>
     * Best-case runtime:nlogn
     * Worst-case runtime:nlogn
     * Average-case runtime:nlogn
     * <p>
     * Space-complexity:n
     */
    @Override
    public int[] sort(int[] array) {
        // Find the middle point
        int re[] = new int[array.length];
        System.arraycopy(array, 0, re, 0, array.length);
        if (array.length > 1) {
            int m = (array.length) / 2;
            int L[] = new int[m];
            int R[] = new int[array.length - m];
            System.arraycopy(array, 0, L, 0, m);
            System.arraycopy(array, m, R, 0, array.length - m);
            // Sort first and second halves
            L = sort(L);
            R = sort(R);
            re = merge(L, R);
        }
        return re;
    }


    /* A utility function to print array of size n */


    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     */
    public int[] merge(int[] a, int[] b) {

        int re[] = new int[a.length + b.length];
        int i = 0, j = 0;

        int k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                re[k] = a[i];
                i++;
            } else {
                re[k] = b[j];
                j++;
            }
            k++;

        }
        while (i < a.length) {
            re[k] = a[i];
            i++;
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (j < b.length) {
            re[k] = b[j];
            j++;
            k++;
        }
        return re;

    }

    @Test
    public void testmy() {
        //int[] hey = new int[] {1,3,8,7,1,9,3,3,};
        //hey = sort(hey);
        //for (int i:hey){
        //  System.out.println(i);
        //}
        int[] a = new int[]{1, 3, 7, 8};
        int[] b = new int[]{1, 2, 9, 10};
        int[] c = merge(a, b);
        for (int i : c) {
            System.out.println(i);
        }
    }

}
