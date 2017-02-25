import org.junit.Test;

public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     * <p>
     * Best-case runtime:n
     * Worst-case runtime:n^2
     * Average-case runtime:n^2
     * <p>
     * Space-complexity:1
     */
    @Override
    public int[] sort(int[] array) {
        int n = array.length;
        for (int i = 1; i < array.length; i++) {
            int number = array[i];
            int k = i;
            int j = i - 1;
            while (j >= 0 && array[j] >= number) {
                int temp = array[j];
                array[j] = array[k];
                array[k] = temp;
                j--;
                k--;
            }
        }
        return array;
    }

    @Test
    public void testmy() {
        int[] hey = new int[]{1, 3, 8, 7, 1, 9, 3, 3};
        hey = sort(hey);
        for (int i : hey) {
            System.out.println(i);
        }

    }
}
