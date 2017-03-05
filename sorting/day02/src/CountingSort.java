public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
<<<<<<< HEAD
     * Runtime: n+max
=======
     * Runtime: TODO
     *
     * k: maximum element in array A
>>>>>>> 3426e3cd9fc692dfc73d933f57a34f667a2def97
     */
    static void countingSort(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        int[] count = new int[max+1];
        for (int n:A){
            count[n] ++ ;
        }
        int j =0;
        for (int i = 0; i<count.length;i++){
            while (count[i]>0){
            A[j] = i;
            j++;
                count[i]--;
            }
        }
    }
}
