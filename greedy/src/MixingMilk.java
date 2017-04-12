import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MixingMilk {
    public static int solve(int M, int N, int[] units, int[] price) {
        PriorityQueue<Integer> sortedPrice = new PriorityQueue<>();
        for (int i : price) {
            sortedPrice.offer(i);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < price.length; i++) {

            if (map.get(price[i]) == null) {
                map.put(price[i], units[i]);
            } else {
                map.put(price[i], units[i]+map.get(price[i]));
                }
            }

        int pay = 0;
        int numV = 0;
        while (M > 0) {
/*            System.out.println("M " + M);
            System.out.println(sortedPrice.size());*/
            int vendor = sortedPrice.poll();
            if (map.get(vendor)!=null){
            int amount = map.get(vendor);
            map.remove(vendor);
                if (M >= amount) {
                    M = M - amount;
                    pay = pay + amount * vendor;
                    numV++;
                } else {
                    pay = pay + vendor * M;
                    M = 0;
                    numV++;
                }
            }
        }

        System.out.println("S = "+numV);
        return pay;
    }
}
