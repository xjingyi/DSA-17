import java.util.*;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {
        Queue<Integer> q = new LinkedList<Integer>();
        HashMap<Integer,Integer> map = new HashMap();
        map.put(v,0);
        q.offer(v);
        while (!q.isEmpty()){
            int u = q.poll();
            for (int i: g.getNeighbors(u)){
                if (!map.containsKey(i)){
                    int count = map.get(u)+1;
                    map.put(i,count);
                    q.offer(i);
                }
            }
        }
        if (map.get(w)==null)
            return null;
        else{ Stack list = new Stack();
            list.push(w);
            int current = w;
            for (int i = map.get(w)-1;i>=0;i--){
                for (int j:g.getNeighbors(current)){
                    if (map.get(j)==i){
                        list.push(j);
                        current = j;
                        break;
                    }
                }
            }
            ArrayList listFinal = new ArrayList<>();
            for (int i=0;i<=map.get(w);i++){
                listFinal.add(list.pop());
            }
            return listFinal;
        }

    }

    public static int distanceBetween(Graph g, int v, int w) {
        Queue<Integer> q = new LinkedList<Integer>();
        HashMap<Integer,Integer> map = new HashMap();
        map.put(v,0);
        q.offer(v);
        while (!q.isEmpty()){
            int u = q.poll();
            for (int i: g.getNeighbors(u)){
                if (!map.containsKey(i)){
                    int count = map.get(u)+1;
                    map.put(i,count);
                    q.offer(i);
                }
            }
        }
        if (map.get(w)==null)
            return -1;
        return map.get(w);
    }

}