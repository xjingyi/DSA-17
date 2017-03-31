import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        char[] clock = new char[hoursInDay];
        for (int i = 0; i < clock.length; i++) {
            clock[i] = '.';
        }
        HashMap<Character, Integer> val = new HashMap();
        HashMap<Character, Integer> stock = new HashMap();
        val.put('p', 1);
        val.put('n', 5);
        val.put('d', 10);
        stock.put('p', pennies);
        stock.put('n', nickels);
        stock.put('d', dimes);
        LinkedList<char[]> fin = new LinkedList<char[]>();
        return clockWT(clock, stock, 0, val, fin);
    }

    public static List<char[]> clockWT(char[] clock, HashMap<Character, Integer> stock, int pos, HashMap<Character, Integer> val, List<char[]> fin) {
        if (isfull(clock)) {
            char[] Re = new char[clock.length];
            for (int i = 0; i < clock.length; i++) {
                Re[i] = clock[i];
            }
            fin.add(Re);
        } else {
            for (char coin : stock.keySet()) {
                if (stock.get(coin) >= 1 && canPut(clock, pos, coin, val)) {
                    clock[pos] = coin;

                    stock.put(coin, stock.get(coin) - 1);
                    int newPos = pos + val.get(coin);
                    while (newPos >= clock.length) {
                        newPos = newPos - clock.length;
                    }
                    clockWT(clock, stock, newPos, val, fin);
                    clock[pos] = '.';
                    stock.put(coin, stock.get(coin) + 1);
                }
            }
        }
        return fin;
    }

    public static boolean isfull(char[] A) {
        if (emptySeats(A) == 0)
            return true;
        return false;
    }

    public static int emptySeats(char[] A) {
        int count = A.length;
        for (char c : A) {
            if (c != '.')
                count--;
        }
        return count;
    }

    public static boolean canPut(char[] clock, int pos, char coin, HashMap<Character, Integer> val) {
        if (emptySeats(clock) == 1) {
            return true;
        }
        int adding = val.get(coin);
        int newPos = pos + adding;
        while (newPos >= clock.length) {
            newPos = newPos - clock.length;
        }
        if (clock[newPos] == '.' && newPos != pos)
            return true;
        return false;
    }

}
