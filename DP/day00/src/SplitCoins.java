
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SplitCoins {

    public int splitCoins(int[] coins) {
        LinkedList<Integer> myCoins = new LinkedList<>();
        for (int i: coins){
            myCoins.addLast(i);
        }
        int re = splitting(0,myCoins);

        return re;

    }
    public int splitting(int dif,LinkedList<Integer> coins) {
        if (coins.size()== 0){
            System.out.println(dif);
            return dif;

        }
/*
        int[] key = {dif,coins.size()};
        if (memo.containsKey(key)){
            return memo.get(key);
        }
*/
        int[] left = new int[2];
        int coin = coins.removeFirst();
        LinkedList<Integer> passing = new LinkedList<>();
        for (int i : coins){
            passing.addLast(i);
        }
        left[0] = splitting(dif+coin,passing);
        LinkedList<Integer> passing1 = new LinkedList<>();
        for (int i : coins){
            passing1.addLast(i);
        }
        left[1] = splitting(dif-coin,passing1);

        return findMin(left);
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
        int[] hey = {4,1,1,1,1,5};
        SplitCoins a = new SplitCoins();
        int b =a.splitCoins(hey);
        System.out.println(b);

    }


}
