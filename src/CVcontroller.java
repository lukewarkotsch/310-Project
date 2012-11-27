import java.util.Hashtable;
import java.util.Random;

public class CVcontroller {

	private Random generator;

	public CVcontroller(){
		generator = new Random();
	}

	public String reply(String searchString, HTcontroller HTC, STcontroller STC){
		String reply = "";
		Hashtable<String, String[]> HT = HTC.getResp();
		int size = HT.size();
		int num = generator.nextInt(size);
		String[] data = HT.get(Integer.toString(num));
		reply = data[0];
		int branch = Integer.parseInt(data[1]);
		if(searchString.contains("?"))
			return "I'm sorry, I'm not supposed to answer questions, only ask them.";
		String[] pieces = searchString.split(" ");
		for(String i : pieces){
			if(i.equals("no")){
				if(Integer.parseInt(data[1])!=11){
					HTC.changeResponses(branch);
					return reply("", HTC, STC);
				} else {
					HTC.changeResponses(focusConvo(STC.symptomStats));
					return reply("", HTC, STC);
				}
			}
		}
		if(size>0){
			if(Integer.parseInt(data[1])!=11)
				HTC.changeResponses(branch);
			else
				HTC.changeResponses(focusConvo(STC.symptomStats));
		}
		return reply;
	}

	public int focusConvo(int[] symptomStats) {
		int group1 = (symptomStats[0] + symptomStats[1])/2;
		int group2 = (symptomStats[2] + symptomStats[3])/2;
		int group3 = symptomStats[4];
		int group4 = (symptomStats[5] + symptomStats[6])/2;
		int group5 = (symptomStats[7] + symptomStats[8] + symptomStats[9])/3;
		int group6 = (symptomStats[10] + symptomStats[11])/2;

		int max = group1;
		if(max < group2) max = group2;
		if(max < group3) max = group3;
		if(max < group4) max = group4;
		if(max < group5) max = group5;
		if(max < group6) max = group6;

		if(max == group1) return 12;
		if(max == group2) return 20;
		if(max == group3) return 30;
		if(max == group4) return 40;
		if(max == group5) return 50;
		if(max == group6) return 60;
		else return 0;
	}

	// -- Returns the given string in lowercase form with punctuation at the end
	public String cleanup(String input){
		input = input.toLowerCase();
		String noPunc = "";
		String punc = "";
		char[] chars = input.toCharArray();
		// -- For each character of the input, check if it is a letter or a space
		for(int i=0;i<chars.length;i++){
			if(((chars[i] > 95) && (chars[i] < 123)) || (chars[i]==32)){
				// -- If it is, add it to the new phrase
				noPunc += chars[i];
			}
			// -- If it is not, add it to the punctuation string
			else 
				punc += " " + chars[i];
		}
		return noPunc + punc;
	}
}
