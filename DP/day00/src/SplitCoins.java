
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;

public class SplitCoins {
        int[][] memo;

    public int splitCoins(int[] coins) {
        int sum  = sum(coins);
        memo = new int[coins.length+1][sum+1];
        for (int i  = 0;i<coins.length+1;i++){
            for (int j = 0;j<sum+1;j++){
                memo[i][j] = -1;
            }
        }
        int re = splitting(0,0,coins);
        return re;
    }

    public int sum(int[] coins) {
        int sum = 0;
        for (int i : coins){
            sum = sum+i;
        }
        return sum;
    }

    public int splitting(int dif,int coinIndex,int[] coins) {
        if (coinIndex==coins.length){
/*            System.out.println(dif);*/
            return dif;
        }

/*        System.out.println("key1 "+key.get(0));
        System.out.println("key2 "+key.get(1));*/
        if (memo[coinIndex][Math.abs(dif)]!=-1){
/*            System.out.println("Memo!");*/
            return memo[coinIndex][Math.abs(dif)];
        }

        int[] left = new int[2];
        int coin = coins[coinIndex];
        left[0] = splitting(Math.abs(dif+coin),coinIndex+1,coins);
        left[1] = splitting(Math.abs(dif-coin),coinIndex+1,coins);
        if (left[0] < left[1])
            memo[coinIndex][Math.abs(dif)] = left[0];
        else
            memo[coinIndex][Math.abs(dif)] = left[1];
        return memo[coinIndex][Math.abs(dif)];
    }
    public static ArrayList<Integer> copying(ArrayList<Integer> arr) {
        ArrayList<Integer> copy = new ArrayList<>(arr.size());
        for (int i :arr){
            copy.add(i);
        }
        return copy;
    }
    public int findMin(int[] coins) {
        int min = Math.abs(coins[0]);
        for (int i:coins){
            if (Math.abs(i)<min){
                min = Math.abs(i);
            }
        }
        return min;
    }
    public static void main(String args[]) {
        int[] hey = {4,1,1,1};
        SplitCoins a = new SplitCoins();
        int b =a.splitCoins(hey);
        System.out.println(b);
    }


}
