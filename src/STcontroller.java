import java.util.Hashtable;

public class STcontroller {

	public STcontroller(){
		
	}

	public void symptomStats(String input, int[] stats, Hashtable<String, int[]> HT){
		// Split phrase into array of words
		String[] words = input.split(" ");
		int[] vals;
		// -- For each word check symptom stats and update
		for(int i=0;i<words.length;i++){
			vals = HT.get(words[i]);
			if(vals!=null) {
				// -- For each value, add to current stat
				for(int j=0;j<stats.length;j++)
					stats[j]+=vals[j];
			}
		}
	}

	// -- Updates all disorder stats from given sentence
	public void disorderStats(String input, int[] stats, Hashtable<String, int[]> HT){
		// Split phrase into array of words
		String[] words = input.split(" ");
		int[] vals;
		// -- For each word check disorder stats and update
		for(int i=0;i<words.length;i++){
			vals = HT.get(words[i]);
			if(vals!=null) {
				// -- For each value, add to current stat
				for(int j=0;j<stats.length;j++)
					stats[j]+=vals[j];
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
}
