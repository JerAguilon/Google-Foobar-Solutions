import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

//O(N!) solution

class MinionInfo {
	int position;
	int time;
	double prb;

	public MinionInfo(int time, int numerator, int denominator, int position) {
		this.position = position;
		this.time = time;
		this.prb = (double) numerator / denominator;

	}

	public String toString() {
		return "Minion #" + position;
	}
}

class MinionStats {
	static MinionInfo[] bestOrder;
	static double bestTime = Double.MAX_VALUE;
}

public class Answer {   


    public static int[] answer(int[][] minions) { 
    	MinionInfo[] minionList = new MinionInfo[minions.length];
        //initialize array to generate to store minions
        for (int i = 0; i < minions.length; i++) {
        	int[] curr = minions[i];
        	minionList[i] = new MinionInfo(curr[0], curr[1], curr[2], i);
        }

        MinionStats ms = new MinionStats();

        permute(minionList, 0, ms);

        MinionInfo[] bestOrder = ms.bestOrder;

        int[] result = new int[bestOrder.length];

        for (int i = 0; i < result.length; i++) {
        	result[i] = bestOrder[i].position;
        }

        return result;

    } 
    
    static void permute(MinionInfo[] arr, int k, MinionStats bestStats){


        for(int i = k; i < arr.length; i++){
            swap(arr, i, k);
            permute(arr, k+1, bestStats);
			swap(arr, k, i);
        }
        if (k == arr.length -1){
        	double thisTime = generateExpected(arr);

        	if (thisTime < MinionStats.bestTime) {
        		MinionStats.bestTime = thisTime;
        		MinionStats.bestOrder = Arrays.copyOf(arr, arr.length);

        	}
        	
        }
    }

    private static void swap(MinionInfo[] arr, int p1, int p2) {
        MinionInfo temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
    
    private static double generateExpected(MinionInfo[] arr) {
        return generateExpected(arr, 0);
    }
    
    private static double generateExpected(MinionInfo[] arr, int position) {
        MinionInfo curr = arr[position];
        
        if (position == arr.length - 1) {
            //return total time, probablility of right answer is 100%
            return arr[position].time;
        }
        
        
        return curr.time * curr.prb + (1.0 - curr.prb) 
        		* (curr.time + generateExpected(arr, position + 1));
    }
    
    
}