import com.sun.scenario.effect.impl.state.AccessHelper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int S, int C, int[] occupied) {
        int groups = 1;
        PriorityQueue<Integer> toSort = new PriorityQueue<>();
        for(int i:occupied){
            toSort.offer(i);
        }
        ArrayList<Integer> sorted = new ArrayList<>();
        for (int i = 0;i<occupied.length;i++){
            sorted.add(toSort.poll());
        }
        PriorityQueue<Integer> dist = new PriorityQueue<>();
        for (int i = 1;i<sorted.size();i++){
            if (sorted.get(i)!=sorted.get(i-1)+1) {
                groups++;
                int dis = sorted.get(i) - sorted.get(i-1)- 1;
                dist.offer(dis);
                /*System.out.println("Offering" + dist.size());*/
            }
        }
        /*System.out.println("Groups"+ groups);*/
        int re = occupied.length;
        if (groups == M){
            return re;
        }
        while (groups>M){
            /*System.out.println("Polling" + dist.peek());*/
            re =re+ dist.poll();
            groups--;
        }
        return re;
    }
}
