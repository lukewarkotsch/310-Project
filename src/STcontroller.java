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
		output += "-------------------------------------";
		output += "\nSymptom Statistics";
		output += "\n-------------------------------------";
		output += "\nHappines:\t\t" + symptomStats[0];
		output += "\nExcitement:\t\t" + symptomStats[1];
		output += "\nAnger:\t\t\t" + symptomStats[2];
		output += "\nAgression:\t\t" + symptomStats[3];
		output += "\nSadness:\t\t" + symptomStats[4];
		output += "\nFear:\t\t\t" + symptomStats[5];
		output += "\nAnxiety:\t\t" + symptomStats[6];
		output += "\nConfusion:\t\t" + symptomStats[7];
		output += "\nMemory Loss:\t\t" + symptomStats[8];
		output += "\nCognitive Loss:\t\t" + symptomStats[9];
		output += "\nDelusion:\t\t" + symptomStats[10];
		output += "\nHallucination:\t\t" + symptomStats[11];
		output += "\n\n-------------------------------------";
		output += "\nDisorder Statistics";
		output += "\n-------------------------------------";
		output += "\nManic Disorder:\t\t" + disorderStats[0];
		output += "\nIE Disorder:\t\t" + disorderStats[1];
		output += "\nDepression:\t\t" + disorderStats[2];
		output += "\nBipolar Disorder:\t" + disorderStats[3];
		output += "\nAnxiety Disorder:\t" + disorderStats[4];
		output += "\nAmnesia:\t\t" + disorderStats[5];
		output += "\nAlzheimers:\t\t" + disorderStats[6];
		output += "\nSchizophrenia:\t\t" + disorderStats[7];
		return output;
	}
}
