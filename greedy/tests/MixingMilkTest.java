import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MixingMilkTest {
    private int numTests = 8;
    private int M, N;
    private int[] units, price;
    private int[] answers = {630, 0, 20, 0, 2000000, 2000000000, 993159, 86776774};

    @Test
    public void run() throws IOException {
        BufferedReader f = BarnRepairTest.readTestFile("mixingmilk.test");
        StringTokenizer st;
        int solution;
        boolean passed = true;

        System.out.println();
        for (int i = 0;i<answers.length;i++){
            System.out.print(answers[i]+" ");
        }
        System.out.println();

        for (int t=0; t<numTests; t++) {
            f.readLine();
            st = new StringTokenizer(f.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            units = new int[N]; price = new int[N];


            for (int i=0; i<N; i++) {
                st = new StringTokenizer(f.readLine());
                units[i] = Integer.parseInt(st.nextToken());
                price[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println("Test case " + t);

            System.out.println("M"+M+" N"+N);

            System.out.println("Price");
            for (int i = 0;i<units.length;i++){
                System.out.print(units[i]+" ");
            }
            System.out.println();

            System.out.println("Units");
            for (int i = 0;i<price.length;i++){
                System.out.print(price[i]+" ");
            }
            System.out.println();
            solution = MixingMilk.solve(M, N, price, units);
            System.out.println("Pay" + solution);
            System.out.print("Test case " + t);


            if (solution != answers[t]) {
                System.out.println(" fails");
                passed = false;
            } else {
                System.out.println(" passes");
            }
        }

        assert passed;
    }
/*    @Test
    public void tesyMy()  {

        int solution;

        M = 100; N = 5;
        int[] unitss = {20, 40, 10, 80, 30};
        int[] pricee = {5, 9, 3, 8, 6};

        solution = MixingMilk.solve(M, N, unitss, pricee);
        System.out.print("Test case " + solution);

    }*/
}
