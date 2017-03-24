import java.util.*;

public class UndirectedGraph implements Graph {

    private HashMap<Integer,ArrayList> g;
    private int countV;
    private int countE;
    public UndirectedGraph(int n) {
        g = new HashMap(n);
        for(int i=0;i<n;i++){
            ArrayList list  = new ArrayList();
            g.put(i,list);
        }
        countV = n;
    }

    @Override
    public void addEdge(int v, int w) {
        ArrayList listv;
        ArrayList listw;
        listv = g.get(v);
        listw = g.get(w);
        listv.add(w);
        listw.add(v);
        g.put(v,listv);
        g.put(w,listw);
        countE++;
    }

    @Override
    public List<Integer> vertices() {
        ArrayList list = new ArrayList(countV);
        for (int i : g.keySet()){
            list.add(i);
        }
        return list;
    }

    @Override
    public int numVertices() {
        return countV;
    }

    @Override
    public int numEdges() {
    	return countE;
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
    	ArrayList list = g.get(v);
        return list;
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
    	ArrayList list = g.get(v);
        if (list.contains(w))
            return true;
        return false;
    }

}
