import java.util.Hashtable;

public class STcontroller {

	int[] symptomStats;
	int[] disorderStats;

	public STcontroller(){
		symptomStats = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
		disorderStats = new int[]{0,0,0,0,0,0,0,0};
	}

	public void symptomStats(String input, Hashtable<String, int[]> HT){
		// Split phrase into array of words
		String[] words = input.split(" ");
		int[] vals;
		// -- For each word check symptom stats and update
		for(int i=0;i<words.length;i++){
			vals = HT.get(words[i]);
			if(vals!=null) {
				// -- For each value, add to current stat
				for(int j=0;j<symptomStats.length;j++)
					symptomStats[j]+=vals[j];
			}
		}
	}

	// -- Updates all disorder stats from given sentence
	public void disorderStats(String input, Hashtable<String, int[]> HT){
		// Split phrase into array of words
		String[] words = input.split(" ");
		int[] vals;
		// -- For each word check disorder stats and update
		for(int i=0;i<words.length;i++){
			vals = HT.get(words[i]);
			if(vals!=null) {
				// -- For each value, add to current stat
				for(int j=0;j<disorderStats.length;j++)
					disorderStats[j]+=vals[j];
			}
		}
	}

	// -- Updates all trigger stats from given sentence
	public void triggerStats(String input, Hashtable<String, Integer> HT){
		// Split phrase into array of words
		String[] words = input.split(" ");
		int count;
		// -- For each word check trigger stats and update
		for(int i=0;i<words.length;i++){
			// If the word exists already, add 1 to count
			if (HT.containsKey(words[i])){
				count = HT.get(words[i]);
				HT.put(words[i], (count+1));
			}
			// Otherwise initialize with count of 18
			else HT.put(words[i], 1);
		}
	}

	public String getStats(){
		String output = "";
		output += "Symptom Statistics";
		output += "\n-------------------------------------";
		output += "\nHappines:\t" + symptomStats[0];
		output += "\nExcitement:\t" + symptomStats[1];
		output += "\nAnger:\t" + symptomStats[2];
		output += "\nAgression:\t" + symptomStats[3];
		output += "\nSadness:\t" + symptomStats[4];
		output += "\nFear:\t" + symptomStats[5];
		output += "\nAnxiety:\t" + symptomStats[6];
		output += "\nConfusion:\t" + symptomStats[7];
		output += "\nMemory Loss:\t" + symptomStats[8];
		output += "\nCognitive Loss:\t" + symptomStats[9];
		output += "\nDelusion:\t" + symptomStats[10];
		output += "\nHallucination:\t" + symptomStats[11];
		output += "\n-------------------------------------";
		output += "\nDisorder Statistics";
		output += "\n-------------------------------------";
		return output;
	}
}
