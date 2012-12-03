import java.util.Hashtable;
import java.util.Random;

public class CVcontroller {

	private Random generator;

	public CVcontroller(){
		generator = new Random();
	}

	// Evaluates users response, analyzes for symptoms and disorders, and responds accordingly
	public String reply(String searchString, HTcontroller HTC, STcontroller STC){
		String reply = "";
		Hashtable<String, String[]> HT = HTC.getResp();
		int size = HT.size();
		int num = generator.nextInt(size); // number used to pick a response
		String[] data = HT.get(Integer.toString(num));
		reply = data[0]; // Agents response
		int branch = Integer.parseInt(data[1]); // Next branch to jump to
		if(searchString.contains("?")) // Avoid answering questions
			return "I'm sorry, I'm not supposed to answer questions, only ask them.";
		String[] pieces = searchString.split(" ");
		// For each word entered by user, look for "no" to skip next question
		// and look for branch 11 to focus conversation
		for(String i : pieces){
			if(i.equals("no") || i.equals("nope") || i.equals("not") || i.equals("nah")){
				if(Integer.parseInt(data[1])!=11){
					HTC.changeResponses(branch);
					return positiveReply() + reply("", HTC, STC);
				} else {
					// If at branch 11, focus the conversation
					HTC.changeResponses(focusConvo(STC.symptomStats));
					return positiveReply() + reply("", HTC, STC);
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

	// Checks which symptoms are being displayed most and jumps to conversation on that topic
	public int focusConvo(int[] symptomStats) {
		// Each group is a set of related symptoms
		int group1 = (symptomStats[0] + symptomStats[1])/2;
		int group2 = (symptomStats[2] + symptomStats[3])/2;
		int group3 = symptomStats[4];
		int group4 = (symptomStats[5] + symptomStats[6])/2;
		int group5 = (symptomStats[7] + symptomStats[8] + symptomStats[9])/3;
		int group6 = (symptomStats[10] + symptomStats[11])/2;

		// Find the max group value
		int max = group1;
		if(max < group2) max = group2;
		if(max < group3) max = group3;
		if(max < group4) max = group4;
		if(max < group5) max = group5;
		if(max < group6) max = group6;

		// Use max group to jump to new topic
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
	
	public String positiveReply(){
		String[] responses = new String[]{"Okay, I see", "Hmm that is peculiar", "Right I think I may see where you are coming from", "Lets try to dig deeper into that"};
		int num = generator.nextInt(responses.length);
		return responses[num] + "\nDoc: ";
	}
}
