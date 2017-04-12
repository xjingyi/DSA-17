import java.util.ArrayList;

public class Stocks {

    public int maxProfit(int[] prices) {
        int max = 0;

        for (int i = 0;i<prices.length-1;i++){
            if ((prices[i]-prices[0])>max){
                max = prices[i]-prices[0];
            }
        }
        int minPrice = findMin(prices);
        if (minPrice<prices[0]){
            return (prices[0]-minPrice+max);
        }
        else {
            return max;
        }
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

    public static int maxProfitWithK(int[] prices, int k) {
        // TODO: Optional
        return -1;
    }
    public static void main(String args[]) {
        int[] hey = {5,8,1,2,6,8,7};
        Stocks a = new Stocks();
        int b =a.maxProfit(hey);
        System.out.println(b);
    }


}
