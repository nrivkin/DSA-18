import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// this is our implementation of a rubiks cube. It is your job to use A* or some other search algorithm to write a
// solve() function
public class RubiksCube {

    private BitSet cube;
    private HashMap<BitSet, Integer> map;


    // initialize a solved rubiks cube
    public RubiksCube() {
        // 24 colors to store, each takes 3 bits
        cube = new BitSet(24 * 3);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                setColor(side * 4 + i, side);
            }
        }
    }

    // initialize a rubiks cube with the input bitset
    private RubiksCube(BitSet s) {
        cube = (BitSet) s.clone();
    }

    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = (BitSet) r.cube.clone();
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return other.cube.equals(cube);
    }

    private class State {
        // Each state needs to keep track of its cost and the previous state
        private RubiksCube rCube;
        private int moves; // equal to g-cost in A*
        private double cost; // equal to f-cost in A*
        private State prev;
        private Character rotation;

        public State(RubiksCube cube, int moves, State prev, Character rotation) {
            this.rCube = cube;
            this.moves = moves;
            this.prev = prev;
            this.rotation = rotation;
        }

        public void getDist(){//maxmanhattan(){ //should be renamed if new method used
            cost = moves;// + threeDManhattanDist(rCube.cube);//maxManhattan(rCube.cube);
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).rCube.equals(this.rCube);
        }

        @Override
        public int hashCode(){
            return rCube.hashCode();
        }
    }

    class CostSort implements Comparator<State>
    {
        // Used for sorting in descending order of cost
        public int compare(State a, State b)
        {
            return (int) (a.cost - b.cost);
        }
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        return this.equals(new RubiksCube());
    }


    // takes in 3 bits where bitset.get(0) is the MSB, returns the corresponding int
    private static int bitsetToInt(BitSet s) {
        int i = 0;
        if (s.get(0)) i |= 4;
        if (s.get(1)) i |= 2;
        if (s.get(2)) i |= 1;
        return i;
    }

    // takes in a number 0-5, returns a length-3 bitset, where bitset.get(0) is the MSB
    private static BitSet intToBitset(int i) {
        BitSet s = new BitSet(3);
        if (i % 2 == 1) s.set(2, true);
        i /= 2;
        if (i % 2 == 1) s.set(1, true);
        i /= 2;
        if (i % 2 == 1) s.set(0, true);
        return s;
    }

    // index from 0-23, color from 0-5
    private void setColor(int index, int color) {
        BitSet colorBitset = intToBitset(color);
        for (int i = 0; i < 3; i++)
            cube.set(index * 3 + i, colorBitset.get(i));
    }


    // index from 0-23, returns a number from 0-5
    private int getColor(int index, BitSet cube) {
        return bitsetToInt(cube.get(index * 3, (index + 1) * 3));
    }

    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        int[] faceFrom = null;
        int[] faceTo = null;
        int[] sidesFrom = null;
        int[] sidesTo = null;
        // colors move from the 'from' variable to the 'to' variable
        switch (c) {
            case 'u': // clockwise
            case 'U': // counterclockwise
                faceFrom = new int[]{0, 1, 2, 3};
                faceTo = new int[]{1, 2, 3, 0};
                sidesFrom = new int[]{4, 5, 8, 9, 17, 16, 21, 20};
                sidesTo = new int[]{21, 20, 4, 5, 8, 9, 17, 16};
                break;
            case 'r':
            case 'R':
                faceFrom = new int[]{8, 9, 10, 11};
                faceTo = new int[]{9, 10, 11, 8};
                sidesFrom = new int[]{6, 5, 2, 1, 17, 18, 13, 14};
                sidesTo = new int[]{2, 1, 17, 18, 13, 14, 6, 5};
                break;
            case 'f':
            case 'F':
                faceFrom = new int[]{4, 5, 6, 7};
                faceTo = new int[]{5, 6, 7, 4};
                sidesFrom = new int[]{3, 2, 8, 11, 14, 15, 23, 20};
                sidesTo = new int[]{8, 11, 14, 15, 23, 20, 3, 2};
                break;
            default:
                System.out.println(c);
                assert false;
        }
        // if performing a counter-clockwise rotation, swap from and to
        if (Character.isUpperCase(c)) {
            int[] temp;
            temp = faceFrom;
            faceFrom = faceTo;
            faceTo = temp;
            temp = sidesFrom;
            sidesFrom = sidesTo;
            sidesTo = temp;
        }
        RubiksCube res = new RubiksCube(cube);
        for (int i = 0; i < faceFrom.length; i++) res.setColor(faceTo[i], this.getColor(faceFrom[i], cube));
        for (int i = 0; i < sidesFrom.length; i++) res.setColor(sidesTo[i], this.getColor(sidesFrom[i], cube));
        return res;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r= r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }

    public double threeDManhattanDist(BitSet cube) {
        Queue<RubiksCube> open = new LinkedList<>();
        int[][] corners = {{0, 21, 16}, {1, 9, 17}, {2, 5, 8},  {3, 4, 20}, {10, 13, 18}, {6, 11, 14}, {7, 15, 23}, {12, 22, 19}};
        RubiksCube solvedCube = new RubiksCube();
        int[][] solved_corners = new int[8][3];

//        System.out.println("here");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                solved_corners[i][j] = getColor(corners[i][j], solvedCube.cube);
                //System.out.println(solved_corners[i][j]);
//                System.out.println(getColor(corners[i][j], cube));
            }
        }
//        //System.out.println(solved_corners);
//
//        //System.out.println("made it here");
        int maxsum = 0;
        for (int i = 0; i < 8; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += Math.abs(getColor(corners[i][j], cube) - solved_corners[i][j]);
            }
            if (sum > maxsum) {
                maxsum = sum;
            }
        }
        return maxsum/15.0;
    }

    public Iterable<Character> neighbors(){
        List<Character> rotations = new ArrayList<>();
        rotations.add('r');
        rotations.add('u');
        rotations.add('f');
        rotations.add('R');
        rotations.add('U');
        rotations.add('F');
        return rotations;
    }

    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        //map = generateMap();
        //System.out.println(map);
        PriorityQueue<State> open = new PriorityQueue<>(new CostSort());
        Set<RubiksCube> openSet = new HashSet<>();
        HashMap<RubiksCube, Integer> visited = new HashMap<>();
        State initialState = new State(new RubiksCube(cube), 0, null, null);
        open.add(initialState);
        Set<RubiksCube> closed = new HashSet<>();
        while (open.size() > 0) {
            State currState = open.poll(); // Get state with lowest cost
            if(currState.rCube.isSolved()){
                List<Character> solution = new ArrayList<>();
                State temp = currState;
                while (temp.prev != null){
                    solution.add(0, temp.rotation);
                    temp = temp.prev;
                }
                return solution;
            }
            for(Character c : neighbors()){
                RubiksCube nr = currState.rCube.rotate(c);
                State ns = new State(nr, currState.moves + 1, currState, c);
                visited.put(nr, ns.moves);
                boolean ignore = false;
                if(openSet.contains(nr)){
                    if(visited.get(nr) < ns.moves) {
                        ignore = true;
                    }
                }
                if(closed.contains(nr)){
                    if(visited.get(nr) < ns.moves) {
                        ignore = true;
                    }
                }
                if(!ignore){
                    ns.getDist();
                    openSet.add(nr);
                    open.add(ns);
                }
            }
            closed.add(currState.rCube);
        }
        return null;
    }

    public void main(String[] args) {
        System.out.println("ran");
//        map = generateMap();
    }

}