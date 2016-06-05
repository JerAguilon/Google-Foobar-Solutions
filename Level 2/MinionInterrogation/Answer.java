import java.util.Collections;
import java.util.Arrays;
import java.util.List;

//O(N!) solution

class MinionInfo implements Comparable<MinionInfo> {
    int position;
    int time;
    double prb;

    double points;

    public MinionInfo(int time, int numerator, int denominator, int position) {
        this.position = position;
        this.time = time;
        this.prb = (double) numerator / denominator;
        this.points = time / ((double)numerator/denominator);

    }

    @Override
    public int compareTo(MinionInfo other) {
        if (this.points < other.points) return -1;
        else if (this.points > other.points) return 1;
        return 0;
    }

    public String toString() {
        return "Minion #" + position;
    }
}

public class Answer {   


    public static int[] answer(int[][] minions) { 
        MinionInfo[] minionList = new MinionInfo[minions.length];
        //initialize array to generate to store minions
        for (int i = 0; i < minions.length; i++) {
            int[] curr = minions[i];
            minionList[i] = new MinionInfo(curr[0], curr[1], curr[2], i);
        }


        List<MinionInfo> result = Arrays.asList(minionList);

        Collections.sort(result);


        MinionInfo[] optimizedOrder = (MinionInfo[]) result.toArray();

        int[] output = new int[minions.length];

        for (int i = 0; i < output.length; i++) {
            output[i] = optimizedOrder[i].position;
        }

        return output;


    } 

    public static void main(String[] args) {
        int[][] arg = new int[][] {{390, 185, 624}, {686, 351, 947},
                                    {276, 1023, 1024}, {199, 148, 250}};

        System.out.println(Arrays.toString(answer(arg)));

    }

    
    
}