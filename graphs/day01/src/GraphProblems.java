import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphProblems {

    public static boolean connected(Graph g, int v, int u) {
        ArrayList list = new ArrayList();
        list = depthFirstSearch(g,v,list);
        if (list.contains(u))
            return true;
        return false;
    }
    public static ArrayList depthFirstSearch(Graph g , int v, ArrayList list) {
        list.add(v);
        for (int i : g.getNeighbors(v)) {
            if (!list.contains(i))
                depthFirstSearch(g, i, list);
        }
        return list;
    }

    public static Stack depthFirstSearchL(Graph g , int v, ArrayList list, Stack stack) {

        list.add(v);
        for (int i : g.getNeighbors(v)) {
            if (!list.contains(i))
                depthFirstSearchL(g, i, list,stack);
        }
        stack.push(v);
        return stack;
    }

    public static Boolean isCyclicUtil(Graph g, int v, Boolean visited[],int parent)
    {
        visited[v] = true;

        for (int i : g.getNeighbors(v))
        {    // If an adjacent is not visited, then recur for that
            // adjacent
            if (!visited[i])
            {
                if (isCyclicUtil(g , i, visited,v))
                    return true;
            }
            // If an adjacent is visited and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }


    public static List<Integer> topologicalOrder(Digraph g) {
        List<Integer> vlist = g.vertices();
        Stack<Integer> flist = new Stack();
        ArrayList<Integer> list = new ArrayList();
        while (list.size()<vlist.size()){
            for (int i : vlist) {
                System.out.println("starting "+i);
                if (!list.contains(i)) {
                    depthFirstSearchL(g, i, list, flist);
                }
                for (int j : flist) {
                    System.out.print(j);

                }
                System.out.println();
            }
        }
        Stack<Integer> fflist = new Stack();
        while (!flist.isEmpty())
        fflist.push(flist.pop());
        return fflist;
    }

    public static boolean hasCycle(UndirectedGraph g) {
        Boolean visited[] = new Boolean[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++)
            visited[i] = false;
        for (int i : g.vertices()){
            if (!visited[i])
                if (isCyclicUtil(g,i,visited,-1) )
                    return true;
        }
        return false;
    }

}