class MinionInfo:
	def __init__(self, minion, position):
		self.time = minion[0];

		self.position = position;
		self.prb = float(minion[1]) / minion[2];

	def __str__(self):
		return "MINION: " + str(self.position) + " | Time: " + str(self.time) + " Probability: " + str(self.prb);

	def __repr__(self):
		return self.__str__();

class MinionStats:
	bestOrder = [];
	bestTime = 1024*1024;

def answer(minions):
	minionList = []
	position = 0;
	for i in minions:
		minionList.append(MinionInfo(i, position));
		position += 1;
		MinionStats.bestOrder.append(position);

	permute(minionList, 0);

	return MinionStats.bestOrder;

def permute(minionList, k):
	i = k;
	while (i < len(minionList)):
		minionList[i], minionList[k] = minionList[k], minionList[i];
		permute(minionList, k+1);
		minionList[i], minionList[k] = minionList[k], minionList[i];
		i += 1;
	if (k == len(minionList) - 1):
		thisTime = getTime(minionList);

		if (thisTime < MinionStats.bestTime):
			MinionStats.bestTime = thisTime;
			MinionStats.bestOrder = minionList[:];



def getTime(minionPermutation):
	#base case: the minion is guaranteed to fess up
	if len(minionPermutation) == 1:
		return minionPermutation[0].time;
	current = minionPermutation[0];

	output = current.prb * current.time + (1.0 - current.prb) * (current.time + getTime(minionPermutation[1:]));
	
	return output;