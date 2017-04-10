import java.util.HashSet;
import java.util.Set;
import java.util.*;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;
    private int[][] goal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        n = b.length;
        tiles = b;
    }

    /*
     * Size of the board (equal to 3 for 8 puzzle, but in theory the Board
     * class should  work for any puzzle size)
     */
    private int size() {
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     * Estimated cost from the current node to the goal for A* (h(n))
     */
    public int manhattan(){
        int sumDist = 0;
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n ; j++) {
                if (tiles[i][j]!=0) {
                    int current = tiles[i][j];
                    sumDist += Math.abs((current-1)/n-i)+Math.abs((current+2)%n-j);
                }
            }
        }
        return sumDist;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        return (manhattan()==0);
    }

    private int[] matrixToArray(int[][] A) {
        int[] arr = new int[A.length * A.length - 1];
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i][j] != 0) {
                    arr[count] = A[i][j];
                    count++;
                }
            }

        }
        return arr;
    }


    public boolean solvable() {
        int[] tilesArr = matrixToArray(tiles);
        int count = 0;
        for (int i = 0; i < tilesArr.length-1; i++) {
            for (int j = i + 1; j < tilesArr.length; j++) {
                if (tilesArr[j]<tilesArr[i]) {
                    count ++;
                }
            }
        }
        if (count % 2 == 1)
            return false;
        return true;

    }

    /*
     * Return the neighboring boards in the state tree
     * One possible method: Create a duplicate board state, try moving the
     * blank space up, down, left, and right, and if the resulting board state
     * is valid, add it to an accumulator.
     */

    private static int[][] copyOf(int[][] A) {
        int[][] B = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public Iterable<Board> neighbors() {
        int[] zeroPos = new int[2];
        ArrayList<Board> neig = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tiles[i][j] == 0) {
                    zeroPos[0] = i;
                    zeroPos[1] = j;
                }
            }
        }
        int[][] cord = {{1,0},{-1,0},{0,1},{0,-1}};
        int[] newZeroPos = new int[2];
        for (int i = 0; i < 4; i++) {
            newZeroPos[0] = cord[i][0] + zeroPos[0];
            newZeroPos[1] = cord[i][1] + zeroPos[1];
            if ((newZeroPos[0] < size()) && (newZeroPos[0] >= 0) && (newZeroPos[1] < size()) && (newZeroPos[1] >= 0)) {
                int[][] newArr = copyOf(tiles);
                int temp = newArr[zeroPos[0]][zeroPos[1]];
                newArr[zeroPos[0]][zeroPos[1]] = newArr[newZeroPos[0]][newZeroPos[1]];
                newArr[newZeroPos[0]][newZeroPos[1]] = temp;
                Board neighbor = new Board(newArr);
                neig.add(neighbor);
            }
        }

        return neig;
    }


    @Override
    public String toString() {
        String s = "";
        for (int[] tile : tiles) {
            for (int aTile : tile) s+= aTile + " ";
            s += "\n";
        }
        return s;
    }

    /*
     * Prints out the board state nicely for debugging purposes
     */
    public void printBoard() {
        for (int[] tile : tiles) {
            for (int aTile : tile) System.out.print(aTile + " ");
            System.out.println();
        }
        System.out.println();
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
//        int[][] initState = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
        int[][] initState = {{8, 6, 7}, {3, 4, 2}, {0, 1, 5}};
        Board board = new Board(initState);


        board.printBoard();
        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
        for (Board b : it)
            b.printBoard();


    }


}