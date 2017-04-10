/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */


import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private State cuurentState;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves;
        public int cost;
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            cost = moves + board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        /*
         * Return the cost difference between two states
         */
        @Override
        public int compareTo(State s) {
            return this.cost - s.cost;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        while(state.prev != null){
            state = state.prev;
        }
        return state;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        State initialState = new State(initial, 0, null);
        cuurentState = initialState;
        PriorityQueue<State> open = new PriorityQueue<>();
        HashSet<State> closed = new HashSet<>();
        int statesVisited = 0;
        open.add(initialState);
        if (!isSolvable()) {
            return;
        }
        while(!open.isEmpty()){
            State q = open.poll();
            statesVisited++;
            if (statesVisited % 1000 == 0)
                System.out.println(statesVisited);

                for(Board uu : q.board.neighbors()) {
                    if (uu.isGoal()) {
                        solutionState = new State(uu, q.moves + 1, q);
                        minMoves = solutionState.moves;
                        return;
                    }

                    State u = new State(uu, q.moves + 1, q);

                    boolean flag = true;
                    for (State n : open) {
                        if (n.board.equals(u.board) && n.cost < u.cost) {
                            flag = false;
                            break;
                        }
                    }
                    for (State n : closed) {
                        if (n.board.equals(u.board) && n.cost < u.cost || !flag) {
                            flag = false;
                            break;
                        }

                    }
                    if (flag) {
                        open.add(u);
                    }

            }
            closed.add(q);
        }
    }

    /*
     * Is the input board a solvable state
     */
    public boolean isSolvable() {
        return cuurentState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        State goal = solutionState;
        minMoves = goal.moves;
        ArrayList<Board> path = new ArrayList<>();
        while (goal.prev != null) {
            Board chicken = new Board(goal.board.tiles);
            path.add(0, chicken);
            goal = goal.prev;
        }
        return path;

    }

    /*
     * Debugging space: Your solution can have whatever output you find useful
     * Optional challenge: create a command line interaction for users to input game
     * states
     */
    public static void main(String[] args) {
        int[][] initState = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};
        Board initial = new Board(initState);

        // Solve the puzzle
        Solver solver = new Solver(initial);
        if (!solver.isSolvable())
            System.out.println("No solution");
        else {
            for (Board board : solver.solution()) {
                board.printBoard();
            }
            System.out.println(solver.minMoves);
        }

    }


}