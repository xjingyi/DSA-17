/*
import java.util.Arrays;

public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int[][] memo = new int[map.length][map[0].length];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -233;
            }
        }
        minimizingHealth(map, 0,0,0,memo);
        System.out.println(Arrays.deepToString(memo));
        System.out.println("done");
        int row = 0;
        int col = 0;
        int goal = memo[row][col];
        System.out.println(goal);
        int min  = 0;
        int current = 0;
        while (row<map.length-1&&col<map[0].length){
            current = current+map[row][col];
            if (current<min){
                min = current;
            }

            if (row == map.length-1 && col == map[0].length-1){
                break;
            }
            if (row == map.length-1){
                col++;
            }
            else if (col == map[0].length-1){
                row++;
            }
            else if (memo[row+1][col] == goal){
                row = row+1;
            }
            else if (memo[row][col+1] == goal){
                col= col+1;
            }
        }
        current =- current -1;
        min = -min;
        System.out.println(" current "+ current+ " min "+ min);
        if (current>min)
            return current;
        else return min;

    }
    public static int minimizingHealth(int[][] map, int row, int col, int health, int[][] memo) {
        if (row == map.length-1 && col == map[0].length-1){
        if (memo[row][col]==-1||health+map[row][col]>memo[row][col]){
        memo[row][col] = health+map[row][col];}
        return health+map[row][col];
        }
        int h;
        */
/*        if (memo[row][col]!=-233){
            return memo[row][col];
        }*//*

        if (row == map.length-1) {
            h = minimizingHealth(map, row, col + 1, health + map[row][col], memo);
        }
        else  if (col == map[0].length-1){
            h = minimizingHealth(map, row+1, col, health + map[row][col], memo);
        }
        else {
            h = Math.max(minimizingHealth(map, row+1, col, health + map[row][col], memo),minimizingHealth(map, row, col + 1, health + map[row][col], memo));
        }
        if (memo[row][col]==-233){
            return memo[row][col] = h;
        }
        else if (h>memo[row][col]){
            memo[row][col] = h;
        }
        System.out.println(" row "+row+" col "+col+" memo "+ memo[row][col]);
        System.out.println(Arrays.deepToString(memo));
        return h;
    }
}
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int[][] memo = new int[map.length][map[0].length];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -233;
            }
        }
        LinkedList<Integer> glow = new LinkedList<>();
        glow.addFirst(-233);
        calculatingHealth(map, 0, 0, 0, memo,Integer.MAX_VALUE,glow);

        System.out.println(Arrays.deepToString(memo));

        return -glow.get(0)+1;
    }
    public static void calculatingHealth(int[][] map, int row, int col, int health, int[][] memo , int low,LinkedList<Integer> glow) {
        int current = health + map[row][col];
        if (memo[row][col] == -233){
            memo[row][col] = current;
        }
        else if (current>memo[row][col]){
            memo[row][col] = current;
        }
        if (current<low){
/*            System.out.println("current "+current+ " low "+low);*/
            low = current;
        }
        if (row == memo.length-1 && col == memo[0].length-1) {
/*            System.out.println(low);*/
            if (glow.get(0) == -233 || low>glow.get(0)){
/*                System.out.println("hitting "+low);*/
                glow.removeFirst();
                glow.addFirst(low);
            }
        }
        if (row == map.length-1 && col == map[0].length-1) {
        }
        else if (row == map.length-1){
            calculatingHealth(map, row,col+1, current,memo, low, glow);}
        else if (col == map[0].length-1) {
            calculatingHealth(map, row+1, col, current, memo, low,glow);
        }
        else {
            calculatingHealth(map, row,col+1, current,memo,low, glow);
            calculatingHealth(map, row+1, col, current, memo,low, glow);}
        if (current<low){
            System.out.println("current "+current+ " low "+low);
            low = current;
        }
        if (row == memo.length-1 && col == memo[0].length-1) {
/*            System.out.println(low);*/
            if (glow.get(0) == -233 || low>glow.get(0)){
/*                System.out.println("hitting "+low);*/
                glow.removeFirst();
                glow.addFirst(low);
            }
        }
    }
    public static void minimizingHealth(int row, int col, int[][] memo, int low, LinkedList<Integer> glow) {
        if (memo[row][col]<low){
            low = memo[row][col];
        }
        if (row == memo.length-1 && col == memo[0].length-1) {
            System.out.println(low);
            if (glow.get(0) == -233 || low>glow.get(0)){
                System.out.println("hitting "+low);
                glow.removeFirst();
                glow.addFirst(low);
            }
        }
        else if (row == memo.length-1){
            minimizingHealth(row,col+1,memo,low,glow);}
        else if (col == memo[0].length-1) {
            minimizingHealth( row+1, col, memo,low,glow);
        }
        else {
            minimizingHealth(row,col+1,memo,low,glow);
        minimizingHealth(row,col+1,memo,low,glow);}
        }



}
