import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        Map<Integer, Integer> map = new MyLinearMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                int temp = map.get(arr[i]) + 1;
                map.put(arr[i], temp);
            } else {
                map.put(arr[i], 1);
            }
        }
        return map;
    }

    public static List<Integer> removeKDigits(int[] num, int k) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < num.length; i++) {
            list.add(num[i]);
        }
        System.out.println(list);
        int delete = k;
        int i = 0;
        while (i < list.size()-1 && delete > 0) {
            if (list.get(i) <= list.get(i + 1)) {
                i++;
            } else {
                System.out.println(i);
                list.remove(i);
                delete--;
                System.out.println(list);
                i = 0;
            }
        }
        while (delete > 0) {
            list.remove(list.size()- 1);
            delete--;
        }
        return list;
    }


    public static int sumLists(Node<Integer> h1, Node<Integer> h2) {
        Node n1 = h1;
        int sum1 = 0;
        while (n1 != null) {
            Integer a = (Integer) n1.data;
            sum1 = sum1 * 10 + a;
            n1 = n1.next;
        }
        Node n2 = h2;
        int sum2 = 0;
        while (n2 != null) {
            Integer a = (Integer) n2.data;
            sum2 = sum2 * 10 + a;
            n2 = n2.next;
        }
        int sum = sum1 + sum2;
        return sum;
    }
}
