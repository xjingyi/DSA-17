/*
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {


    public static List<Integer> justifyText(String[] w, int m) {
        LinkedList<Integer> solution = new LinkedList<>();
        solution.addFirst(0);
        justifying(w,0,m,solution);
        return solution;
    }
    public static Integer justifying(String[] w,int a, int m, LinkedList<Integer> solution) {
        if (a == w.length-1){
            return 0;
        }
        int[] arr = new int[w.length-a-1];
        for (int i  = a+1;i<w.length;i++){
            arr[i-a-1] = ((justifying(w,i,m,solution))+badness(w,a,i-1,m));
        }
        int[] min = findMin(arr);
        solution.addLast(min[0]+a);
        return min[1];
    }

    public static int badness(String[] w,int a,int b, int m) {
        int sum = -1;
        for (int i = a;i<=b;i++){
            sum = w[i].length()+1;
        }
        if (sum>m){
            return Integer.MAX_VALUE;
        }
        else {return ((m-sum)^3);}
    }
    public static int[] findMin(int[] arr) {
        int min = arr[0];
        int index = 0;
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }
        int[] re = {index,min};
        return re;
    }
*/
/*import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.Pack200;

public class TextJustification {


    public static List<Integer> justifyText(String[] w, int m) {
        int n = w.length;
        int[] lth = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            lth[i] = w[i].length();
        }
        int[][] extras = new int[n][n];
        int i,j;
        for (i = 0; i < n; i++)
        {
            for (j = i; j < n; j++)
                if (j == i){
                    extras[i][j] = m - lth[i];
                    System.out.println("i "+ i +" j " + j + " extras " + extras[i][j] );
                }
                else {
                    extras[i][j] = extras[i][j - 1] - lth[j] - 1;
                    System.out.println("i " + i + " j " + j + " extras " + extras[i][j] + " l " + lth[j]);
                }
        }
        System.out.println(Arrays.deepToString(extras));

        int[][] cost = new int[n][n];
        for (i = 0; i <n ; i++) {
            {
                for (j = i; j < n; j++) {
                    if (extras[i][j]<0){
                        cost[i][j] = Integer.MAX_VALUE;
                    }
                    else {
                        cost[i][j] =(int) Math.pow(extras[i][j],3);
                        System.out.println("i " + i + " j " + j +" cost "+ cost[i][j] + " extras " + extras[i][j] );
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(cost));
        int[] c = new int[lth.length];
        for (i = 0;i<n;i++){
            c[i] = 0;
        }
        int[] b = new int[lth.length];
        int[] p = new int[lth.length+1];
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);

        for (i = n-1; i>=0;i --)
        {
            int mint = Integer.MAX_VALUE;
            int index = i;
            for (j = i; j < n; j++){
                System.out.println("didnt "+ " mint " +mint + " i "+i + " index " + index + " cost " + cost[i][j] + " c "+ c[j]);
             if (cost[i][j]!=Integer.MAX_VALUE&&(cost[i][j]+c[j])<mint){
                mint = (cost[i][j]+c[j]);
                index = j;
                System.out.println(" mint " +mint + " i "+i + " index " + index + " cost " + cost[i][j] + " c "+ c[j]);
            }
            }
            c[i] = mint;
            System.out.println(" c of "+ i + " is "+c[i]);
            b[i] = index;
        }

        for (int a :b){
            System.out.print(a+" ");
        }
        System.out.println();

        return list;

    }
    public static int[] findMin(int[] arr, int a) {
        int min = arr[a];
        int index = a;
        for (int i = a; i <arr.length ; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
                 }
        }
        int[] re = {index,min};
        return re;
    }
    public static int printSolution (int p[], int n)
    {
        int k;
        if (p[n] == 0)
            k = 0;
        else
            k = printSolution (p, p[n]-1) + 1;
        System.out.println("Line number"+ k +": From word no. "+ p[n]+ " to " +n);
        return k;
    }
}*/

// A Dynamic programming solution for Word Wrap Problem

import java.util.LinkedList;
import java.util.List;


public class TextJustification {

    public static List<Integer> justifyText(String[] w, int M) {
        int n = w.length;
        int[] l = new int[w.length];
        for (int i = 0; i <n; i++) {
            l[i] = w[i].length();
        }

        int[][] extras  = new int[n + 1][n + 1];
        int[][] lc = new int[ n + 1][n + 1];
        int[] c = new int[ n + 1];
        int[] p = new int[ n + 1];
        int i, j;
        for (i = 1; i <= n; i++)
        {
            extras[i][i] = M - l[i-1];
            for (j = i+1; j <= n; j++)
                extras[i][j] = extras[i][j-1] - l[j-1] - 1;
        }
        int INF = Integer.MAX_VALUE;
        for (i = 1; i <= n; i++)
        {
            for (j = i; j <= n; j++)
            {
                if (extras[i][j] < 0)
                    lc[i][j] = INF;
                else
                    lc[i][j] = (extras[i][j]*extras[i][j]*extras[i][j]);
            }
        }

        c[0] = 0;
        for (j = 1; j <= n; j++)
        {
            c[j] = INF;
            for (i = 1; i <= j; i++)
            {
                if (c[i-1] != INF && lc[i][j] != INF && (c[i-1] + lc[i][j] < c[j]))
                {
                    c[j] = c[i-1] + lc[i][j];
                    p[j] = i;
                }
            }
        }
        for (int a:p){
            System.out.print(" "+ a);
        }
        System.out.println();
        LinkedList<Integer> list = new LinkedList<>();
/*        printSolution(p, n,list);*/
        i = n;
        while (i>1){
            list.addFirst(p[i]-1);
            i = p[i] - 1;
        }

        return list;
    }

    static int printSolution(int p[], int n,LinkedList<Integer> list) {
        int k;
        if (p[n] == 1)
            k = 1;
        else
            k = printSolution(p, p[n] - 1, list) + 1;
        System.out.println("Line number"+ k +": From word no. "+ p[n]+ " to " +n);
        list.add(n);
        return k;
    }
    public static void main(String args[])
    {
        String[] w = {"he"};
        int n = 4;
        int M = 6;
        justifyText(w, M);
    }
    // Driver program to test above functions
}
