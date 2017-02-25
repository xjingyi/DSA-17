import java.util.Arrays;

/**
 * Created by sidd on 2/20/17.
 */
public class PeakFinding {

    public static int findOneDPeak(int[] nums) {
        int n = nums.length;
        return findOnePeak(nums, 0, n - 1, n);
    }

    public static int findOnePeak(int[] nums, int low, int high, int n) {
        int mid = low + (high - low) / 2;

        if (mid == 0) {
            if (nums[mid] >= nums[mid + 1]) {
                return mid;
            } else return findOnePeak(nums, mid + 1, high, n);
        } else if (mid == n - 1) {
            if (nums[mid] >= nums[mid - 1]) {
                return mid;
            } else {
                return findOnePeak(nums, low, mid - 1, n);
            }
        } else if (nums[mid - 1] > nums[mid]) {
            return findOnePeak(nums, low, mid - 1, n);
        } else if (nums[mid + 1] > nums[mid]) {
            return findOnePeak(nums, mid + 1, high, n);
        } else {
            return mid;
        }
    }

    public static int[] findTwoDPeak(int[][] nums) {

        int sizerow = nums.length;
        int sizecol = nums[0].length;
        int row = sizerow / 2;
        int col = sizecol / 2;
        return findTwoPeak(nums, 0, sizerow - 1, 0, sizecol - 1, sizerow, sizecol);
    }

    public static int[] findTwoPeak(int[][] nums, int rowstart, int rowend, int colstart, int colend, int sizerow, int sizecol) {
        int maxnumrow = 0;
        int maxrow = 0;
        int maxnumcol = 0;
        int maxcol = 0;
        int midrow = rowstart + (rowend - rowstart) / 2;
        int midcol = colstart + (colend - colstart) / 2;
        int sizerowtemp = rowend - rowstart + 1;
        int sizecoltemp = colend - colstart + 1;

        int[] re = new int[2];
        for (int i = rowstart; i <= rowend; i++) {
            if (nums[i][midcol] > maxnumrow) {
                maxnumrow = nums[i][midcol];
                maxrow = i;
            }
        }
        for (int i = colstart; i <= colend; i++) {
            if (nums[midrow][i] > maxnumcol) {
                maxnumcol = nums[midrow][i];
                maxcol = i;
            }
        }
        if (maxcol == maxrow) {
            re[0] = maxrow;
            re[1] = maxcol;
            return re;
        } else if (maxcol > midcol) {
            if (nums[midrow - 1][maxcol] > nums[midrow][maxcol]) {
                return findTwoPeak(nums, rowstart, midrow - 1, midcol + 1, colend, sizerow, sizecol);
            } else if (nums[midrow + 1][maxcol] > nums[midrow][maxcol]) {
                return findTwoPeak(nums, midrow + 1, rowend, midcol + 1, colend, sizerow, sizecol);
            } else {
                re[0] = midrow;
                re[1] = maxcol;
                return re;
            }
        } else {
            if (nums[midrow - 1][maxcol] > nums[midrow][maxcol]) {
                return findTwoPeak(nums, rowstart, midrow - 1, colstart, midcol - 1, sizerow, sizecol);
            } else if (nums[midrow + 1][maxcol] > nums[midrow][maxcol]) {
                return findTwoPeak(nums, midrow + 1, rowend, colstart, midcol - 1, sizerow, sizecol);
            } else {
                re[0] = midrow;
                re[1] = maxcol;
                return re;
            }
        }
    }

}
