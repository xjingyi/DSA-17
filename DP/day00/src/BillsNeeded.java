public class BillsNeeded {

    int[] memo;
    public int billsNeeded(int N, int[] billDenominations) {
        memo = new int[N+1];
        for (int i = 0;i<memo.length;i++){
            memo[i] = -1;
        }
        return billsUntil(N,billDenominations);
    }
    public int billsUntil(int N, int[] billDenominations) {
        if (N == 0){
            return 0;
        }
        else if (N<0) {
            return 100;
        }
        if (memo[N]!=-1){
            return memo[N];
        }
        int[] left = new int[billDenominations.length];
        for (int i = 0; i<left.length;i++){
            left[i] =billsUntil(N - billDenominations[i], billDenominations);
        }
        memo[N] = findMin(left)+1;
        return memo[N];
    }

    public int findMin(int[] arr) {
        int min = arr[0];
        for (int i:arr){
            if (i<min){
                min = i;
            }
        }
        return min;
    }

/*    public static void main(String args[]) {
        int N = 49;
        int[] hey = {1,15,25};
        BillsNeeded you = new BillsNeeded();
        int a =you.billsNeeded(N,hey);
        System.out.println(a);
    }*/


}
