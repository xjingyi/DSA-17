import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        PriorityQueue<Integer> min = minPQ();
        PriorityQueue<Integer> max = maxPQ();

        double[] runningMedian = new double[inputStream.length];
        for (int i = 0; i < inputStream.length; i++) {
            if (i == 0) {
                max.offer(inputStream[i]);
                runningMedian[i] = inputStream[i];
            } else if (i == 1) {
                if (max.peek() > inputStream[i]) {
                    min.offer(max.poll());
                    max.offer(inputStream[i]);
                } else {
                    min.offer(inputStream[i]);
                }
                runningMedian[i] = (max.peek() + min.peek()) / 2.0;
            } else if (inputStream[i] <= max.peek()) {
                max.offer(inputStream[i]);
            } else {
                min.offer(inputStream[i]);

            }
            if ((min.size() - max.size()) > 1) {
                max.offer(min.poll());
            }
            if ((max.size() - min.size()) > 1) {
                min.offer(max.poll());
            }
            if (min.size() == max.size()) {
                runningMedian[i] = (min.peek() + max.peek()) / 2.0;
            } else if (min.size() > max.size()) {
                runningMedian[i] = min.peek();
            } else {
                runningMedian[i] = max.peek();
            }
        }
        return runningMedian;
    }
}
