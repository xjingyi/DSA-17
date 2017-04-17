
import java.util.ArrayList;

public class Stocks {

    public int maxProfit(int[] prices) {

        int minPrice = prices[0];
        int maxProfit = 0;
        int currentProfit = 0;
        for (int i=0;i<prices.length;i++){
            if (prices[i]<=minPrice){
                minPrice = prices[i];
            }
            else {
                currentProfit = prices[i]-minPrice;
                if (currentProfit>maxProfit){
                    maxProfit = currentProfit;
                }
            }
        }
        return  maxProfit;


    }

    public int maxProfitWithK(int[] prices, int k) {
        // TODO: Optional
        return -1;
    }

    public int findMin(int[] arr) {
        int min = arr[0];
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
        }
        return min;

    }
}
