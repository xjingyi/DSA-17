import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        int n = factorial(A.size());
        LinkedList<Integer> hey = new LinkedList<>();
        for (int i : A) {
            hey.addLast(i);
        }
        LinkedList<Integer> Re = new LinkedList<>();
        List<List<Integer>> BigRe = new ArrayList<List<Integer>>();
        BigRe = getPWT(hey, Re, BigRe, n);
        return BigRe;
    }

    public static List<List<Integer>> getPWT(LinkedList<Integer> A, LinkedList<Integer> Re, List<List<Integer>> BigRe, int n) {
        if (A.size() == 1) {
            int tempp = A.get(0);
            Re.addLast(tempp);
            LinkedList<Integer> put = new LinkedList<>();
            for (int i : Re) {
                put.addLast(i);
                System.out.print(i);
            }
            BigRe.add(put);
            Re.removeLast();
        } else {
            int nn = A.size();
            for (int i = 0; i < nn; i++) {
                Integer temp = A.get(i);
                Re.addLast(temp);
                A.remove(i);
                getPWT(A, Re, BigRe, n);
                A.add(i, temp);
                Re.removeLast();
            }
        }
        return BigRe;
    }


    public static int factorial(int number) {
        if (number <= 1) return 1;
        else return number * factorial(number - 1);
    }

}
