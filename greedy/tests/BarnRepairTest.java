import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BarnRepairTest {
    private int numTests = 10;
    private int M, S, C;
    private int[] occupied;
    private int[] answers = {21, 6, 20, 9, 10, 6, 118, 180, 100, 200};

    public static BufferedReader readTestFile(String name) throws IOException {
        String slash = File.separator;
        String filename = System.getProperty("user.dir") + slash +
                "tests" + slash + "files" + slash + name;
        return new BufferedReader(new FileReader(filename));
    }

    @Test
    public void run() throws IOException {
        BufferedReader f = readTestFile("barnrepair.test");
        StringTokenizer st;
        int solution;
        boolean passed = true;

        for (int t=0; t<numTests; t++) {
            f.readLine();
            st = new StringTokenizer(f.readLine());
            M = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            occupied = new int[C];

            for (int i=0; i<C; i++)
                occupied[i] = Integer.parseInt(f.readLine());

            solution = BarnRepair.solve(M, S, C, occupied);
            System.out.println("Test case " + t);
            System.out.println("M"+M+" S"+S+" C"+C);
            for (int i = 0;i<occupied.length;i++){
                System.out.print(occupied[i]+" ");
            }
            System.out.println();
            if (solution != answers[t]) {
                System.out.println(" fails");
                passed = false;
            } else {
                System.out.println(" passes");
            }
        }

        assert passed;
    }
    @Test
    public void tesyMy()  {

        int solution;

        M = 2;
        S = 12;
        C = 6;
        int[] occupi = {1, 2, 4, 5, 6, 12};

            solution = BarnRepair.solve(M, S, C, occupi);
            System.out.print("Test case " + solution);

    }
}
