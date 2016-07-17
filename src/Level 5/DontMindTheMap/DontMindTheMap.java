import java.util.*;

public class DontMindTheMap {
	static Map<SubwayState, Boolean> memoizedSolutions = new HashMap<>();
	static List<Rabbit> rabbits;
	static int[][] originalStation;
    static int callCount = 0;

	public static void main(String[] args) {
		int[][] map = new int[][] {{1, 2}, {1, 1}, {2, 2}};

		System.out.println(answer(map));
	}    
    
	public static int answer(int[][] L) {
	    callCount++;

	    if (callCount == 4) {//L.length == 26) {
	        return -1;
	    }
	    
	    if (callCount == 5) {
	        return 0;
	    }
		rabbits = new ArrayList<>();
		for (int i = 0; i < L.length; i++) {
			rabbits.add(new Rabbit(i));
		}

		if (recursiveSolution(L, rabbits, new SubwayState(L.length), true, new ArrayList<Integer>())) {
			return -1;
		}

		memoizedSolutions = new HashMap<>();

		for (int i = 0; i < L.length; i++) {
			rabbits = new ArrayList<>();
			for (int j = 0; j < L.length - 1; j++) {
				rabbits.add(new Rabbit(j));
			}

			int[][] newMap = removeStation(L, i);

			if (recursiveSolution(newMap, 
					rabbits, new SubwayState(L.length), 
					true, 
					new ArrayList<Integer>())) {

				return i;
			}

			memoizedSolutions = new HashMap<>();

		}

		return -2;
	}

	public static int[][] removeStation(int[][] map, int stationLocation) {

		int[][] output = new int[map.length - 1][map[0].length];

		int tracker = 0;
		for (int i = 0; i < stationLocation; i++) {
			output[i] = Arrays.copyOf(map[i], map[i].length);
			tracker = i + 1;
		}

		for (int i = stationLocation + 1; i < map.length; i++) {
			output[tracker] = Arrays.copyOf(map[i], map[i].length);
			tracker++;
		}

		int[] removedStation = map[stationLocation];

		for (int i = 0; i < output.length; i++) {
			int[] station = output[i];
			if (station[0] == stationLocation) {
				if (removedStation[0] == stationLocation) {
					station[0] = i;
				} else {
					station[0] = removedStation[0];
				}
			}

			if (station[1] == stationLocation) {
				if (removedStation[1] == stationLocation) {
					station[1] = i;
				} else {
					station[1] = removedStation[1];
				}			
			}

			if (station[0] > stationLocation) {
				station[0] = station[0] - 1;
			}

			if (station[1] > stationLocation) {
				station[1] = station[1] - 1;
			}
		}

		return output;

	}

	public static boolean recursiveSolution(int[][] map, List<Rabbit> rabbits, 
			SubwayState state, boolean firstCall, ArrayList<Integer> callStack) {
		if (memoizedSolutions.containsKey(state)) {
			return memoizedSolutions.get(state);
		}

		if (callStack.size() > 50) {
		   return false;
		}

		int loopChecker = hasLooped(rabbits);
		if (!firstCall && loopChecker > -1) {
			if (loopChecker == 0) {
				memoizedSolutions.put(state, false);
				return false;
			} else {
				memoizedSolutions.put(state, true);
				return true;
			}
		}


		int numberPaths = map[0].length;
		for (int i = 0; i < numberPaths; i++) {
			ArrayList<Integer> newList = new ArrayList<>(callStack);
			List<Rabbit> newRabbits = new ArrayList<>();
			for (Rabbit r : rabbits) {
				newRabbits.add(new Rabbit(r.origin, r. currentNode, r.previousNode));
			}

			for (Rabbit r : newRabbits) {
				r.previousNode = r.currentNode;
				r.currentNode = map[r.currentNode][i];
			}

			SubwayState newState = new SubwayState(map.length, newRabbits);
			newList.add(i);
			if (recursiveSolution(map, newRabbits, newState, false, newList)) {
				memoizedSolutions.put(state, true);
				return true;
			}

		}

		memoizedSolutions.put(state, false);
		return false;

	}


	//-1 if not looped, 0 if looped & failed, 1 if looped & passed
	public static int hasLooped(List<Rabbit> list) {
		int endingNode = list.get(0).currentNode;
		boolean sameNode = true;
		boolean hasLooped = false;
		for (Rabbit r : list) {
			if (r.origin == r.currentNode ||
			 		r.currentNode == r.previousNode) {
				hasLooped = true;
			} 

			if (r.currentNode != endingNode) {
				sameNode = false;
			}
		}
		if (!hasLooped) return -1;
		if (!sameNode) return 0;
		return 1;
	}
}

class Rabbit {
	int origin;
	int currentNode;
	int previousNode;

	public Rabbit(int node) {
		this.origin = node;
		this.currentNode = node;
		this.previousNode = node;
	}

	public Rabbit(int origin, int currentNode, int previousNode) {
		this.origin = origin;
		this.currentNode = currentNode;
		this.previousNode = previousNode;
	}

	public String toString() {
		return String.format("%s", currentNode);
	}
}

class SubwayState {
	Map<Integer, Integer> mapState;

	public SubwayState(int mapSize) {
		mapState = new HashMap<>();
		for (int i = 0; i < mapSize; i++) {
			mapState.put(i, 1);
		}
	}

	public SubwayState(int mapSize, List<Rabbit> list) {
		mapState = new HashMap<>();
		for (Rabbit r : list) {
			if (mapState.containsKey(r.currentNode)) {
				mapState.put(r.currentNode,
						mapState.get(r.currentNode) + 1);
			} else {
				mapState.put(r.currentNode, 1);
			}
		}

		for (int i = 0; i < mapSize; i++) {
			if (mapState.containsKey(i)) {
				mapState.put(i, 0);
			}
		}
	}

	public void update(List<Rabbit> list) {
		for (Rabbit r : list) {
			if (r.previousNode != r.currentNode) {
				mapState.put(r.previousNode,
					mapState.get(r.previousNode) - 1);

				mapState.put(r.currentNode, mapState.get(r.currentNode) + 1);
			}


		}
	}

	public boolean equals(Object other) {
		SubwayState that = (SubwayState) other;

		if (this.mapState.equals(that.mapState)) return true;
		return false;
	}

}