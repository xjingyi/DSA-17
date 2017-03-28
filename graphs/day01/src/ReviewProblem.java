public class ReviewProblem {

    public static int minimumLengthSubArray(int[] A, int desiredSum) {
        int sum = 0;
        int min = A.length;
        int i = 0;
            for (int j = 0;j<A.length;j++){
                sum = sum+A[j];
                    while (sum>=desiredSum){
                        if (j-i+1<min){
                            min = j-i+1;
                        }
                        sum = sum-A[i];
                        i++;
                }
        }
        return min;
    }

}
