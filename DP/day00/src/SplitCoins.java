import java.util.ArrayList;

public class SplitCoins {

    public int splitCoins(int[] coins) {
        ArrayList<Integer> myCoins = new ArrayList<>();
        for (int i: coins){
            myCoins.add(i);
        }
        return splitting(0,myCoins);
    }
    public int splitting(int dif,ArrayList<Integer> coins) {
        if (coins.size()== 0){
            return dif;
        }
/*        if (memo[N]!=-1){
            return memo[N];
        }*/
        int[] left = new int[coins.size()];
        for (int i = 0; i<coins.size();i++){
/*            System.out.println("Trying "+ coins.get(i));*/
            ArrayList<Integer> coinsLeft = copying(coins);
            coinsLeft.remove(coins.get(i));
            left[i] = splitting(Math.abs(dif-coins.get(i)),coinsLeft);
        }

        return findMin(left,dif);
    }
    public static ArrayList<Integer> copying(ArrayList<Integer> arr) {
        ArrayList<Integer> copy = new ArrayList<>(arr.size());
        for (int i :arr){
            copy.add(i);
        }
        return copy;
    }
    public int findMin(int[] coins, int dif) {
        int min = Math.abs(coins[0]);
        for (int i:coins){
            if (Math.abs(i)<min){
                min = Math.abs(i);
            }
        }
        return min;
    }
    public static void main(String args[]) {
        int[] hey = {1,1,7,100,200,300};
        SplitCoins a = new SplitCoins();
        int b =a.splitCoins(hey);
        System.out.println(b);
    }

}
