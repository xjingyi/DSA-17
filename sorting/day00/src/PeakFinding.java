
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

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peak(int i, int[] nums) {
        if (i>0 && nums[i] < nums[i-1])
            return -1;
        if (i<nums.length-1 && nums[i] < nums[i+1])
            return 1;
        return 0;
    }

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x>0 && nums[y][x] < nums[y][x-1])
            return -1;
        if (x<nums[0].length-1 && nums[y][x] < nums[y][x+1])
            return 1;
        return 0;
    }

    // Return -1 is up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y>0 && nums[y][x] < nums[y-1][x])
            return -1;
        if (y<nums.length-1 && nums[y][x] < nums[y+1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }
    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }
}
