import java.util.Hashtable;

public class STcontroller {

	int[] symptomStats;
	int[] disorderStats;
	int[] diagnoses;

	String[] symptoms;
	String[] disorders;

	public STcontroller(){
		symptomStats = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
		disorderStats = new int[]{0,0,0,0,0,0,0,0};
		diagnoses = new int[]{0,0,0,0,0,0,0,0};
		symptoms = new String[] {"happiness", "excitement", "anger", "aggression", "sadness", "fear", "anxiety", "confusion", "memory loss", "cognitive loss", "delusion", "hallucination"};
		disorders = new String[] {"manic disorder", "IED", "depression", "bipolar", "anxiety disorder", "amnesia", "altzheimers", "schizophrenia"};
	}

	public void updateStats(String input, Hashtable<String, int[]> HT, int[] currStats){
		// Split phrase into array of words
		if(input.length()>0){
			String[] words = input.split(" ");
			String check = "";
			int[] vals;
			// -- For each word check symptom stats and update
			for(int i=0;i<words.length;i++){
				check += words[i];
				vals = HT.get(check);
				if(vals!=null) {
					// -- For each value, add to current stat
					for(int j=0;j<currStats.length;j++)
						currStats[j]+=vals[j];
				}
				check += " ";
			}
			if(input.length()<=words[0].length())
				input = "";
			else
				input = input.substring(words[0].length()+1);
			updateStats(input, HT, currStats);
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

	public void diagnose(){
		double points;
		points = (double)(symptomStats[0]*0.2 + symptomStats[1]*0.6 + symptomStats[6]*0.1 + symptomStats[9]*0.1);
		diagnoses[0] = (int) ((disorderStats[0] + points) * 100);

		points = (double)(symptomStats[1]*0.1 + symptomStats[2]*0.4 + symptomStats[3]*0.5);
		diagnoses[1] = (int) ((disorderStats[1] + points) * 100);

		points = (double)(symptomStats[4]*0.75 + symptomStats[7]*0.1 + symptomStats[9]*0.15);
		diagnoses[2] = (int) ((disorderStats[2] + points) * 100);

		if( Math.max(disorderStats[0], disorderStats[2])/2 < Math.min(disorderStats[0], disorderStats[2]) )
			points = (disorderStats[0] + disorderStats[2])/2;
		else points = 0;
		diagnoses[3] = (int) ((disorderStats[3] + points) * 100);

		points = (double)(symptomStats[5]*0.2 + symptomStats[6]*0.7 + symptomStats[9]*0.1);
		diagnoses[4] = (int) ((disorderStats[4] + points) * 100);

		points = (double)(symptomStats[7]*0.15 + symptomStats[8]*0.8 + symptomStats[9]*0.05);
		diagnoses[5] = (int) ((disorderStats[5] + points) * 100);

		points = (double)(symptomStats[7]*0.2 + symptomStats[8]*0.5 + symptomStats[9]*0.3);
		diagnoses[6] = (int) ((disorderStats[6] + points) * 100);

		points = (double)(symptomStats[9]*0.2 + symptomStats[10]*0.4 + symptomStats[11]*0.4);
		diagnoses[7] = (int) ((disorderStats[7] + points) * 100);
	}

	public String getStats(){
		diagnose();
		String output = "";
		output += "-------------------------------------";
		output += "\nSymptoms displayed:";
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
		output += "\nCalculated possible diagnoses:";
		output += "\n-------------------------------------";
		output += "\nManic Disorder:\t\t" + diagnoses[0];
		output += "\nIE Disorder:\t\t" + diagnoses[1];
		output += "\nDepression:\t\t" + diagnoses[2];
		output += "\nBipolar Disorder:\t" + diagnoses[3];
		output += "\nAnxiety Disorder:\t" + diagnoses[4];
		output += "\nAmnesia:\t\t" + diagnoses[5];
		output += "\nAlzheimers:\t\t" + diagnoses[6];
		output += "\nSchizophrenia:\t\t" + diagnoses[7];
		return output;
	}

}
