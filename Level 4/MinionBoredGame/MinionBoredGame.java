import java.util.HashMap;

class GameState {
    private int movesLeft;
    private int position;

    public GameState(int movesLeft, int position) {
        this.movesLeft = movesLeft;
        this.position = position; 
    }

    public int movesLeft() {
        return movesLeft;
    } 
    
    public int position() {
        return position;
    }
    
    @Override
    public boolean equals(Object other) {
        GameState that = (GameState) other;

        return this.movesLeft == that.movesLeft
                && this.position == that.position;
    }

    @Override
    public int hashCode() {
        return movesLeft * 39 + position * 23 % 13;
    }
}

public class MinionBoredGame {
    public static final int MOD = 123454321;
    static int t;
    static int n;
   
    public static void main(String[] args) {
        System.out.println(answer(15,10)); 
    } 
    public static int answer(int t, int n) {
        MinionBoredGame.t = t;
        MinionBoredGame.n = n;
        HashMap<GameState, Integer> set = new HashMap<>();

        return answerRecursive(t, 1, set) % MOD;
    }

    public static int answerRecursive(int movesLeft, int position,
            HashMap<GameState, Integer> map) {
        GameState current = new GameState(movesLeft, position);

        if (map.containsKey(current)) return map.get(current);       

        if (position == n) {
            return 1;
        }

        if (position + movesLeft < n) {
            return 0;
        }

        if (movesLeft == 0) {
            return 0;
        }

        int possibleSolutions = 0;

        if (canMoveRight(position)) {
            possibleSolutions += answerRecursive(movesLeft - 1, position + 1, map);
        }

        if (canMoveLeft(position)) {
            possibleSolutions += answerRecursive(movesLeft - 1, position - 1, map);
        }

        possibleSolutions += answerRecursive(movesLeft -1, position, map);
        
        possibleSolutions = possibleSolutions % MOD;
        
        map.put(current, possibleSolutions);

        return possibleSolutions;
    }

    private static boolean canMoveLeft(int position) {
        if (position == t) return false;
        
        return position > 1; 
    }

    private static boolean canMoveRight(int position) {
        return position < n; 
    }
}
