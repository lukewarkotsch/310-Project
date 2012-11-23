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
		if(size>0){
			int num = generator.nextInt(size);
			String[] data = HT.get(Integer.toString(num));
			reply = data[0];
			if(Integer.parseInt(data[1])!=11)
				HTC.changeResponses(Integer.parseInt(data[1]));
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

	public String findReply(String searchString, Hashtable<String, String[]> HT){
		String replyString;
		String[] SearchPieces = searchString.split(" ");
		String[] ReplyPieces = null;
		ReplyPieces = HT.get(searchString);
		if (ReplyPieces == null){
			String[] phrases = combine(SearchPieces);
			int inc = 0;
			while(ReplyPieces == null && inc < phrases.length && phrases[inc]!=null){
				ReplyPieces = HT.get(phrases[inc]);
				inc++;
			}
		}
		if(ReplyPieces == null)
			ReplyPieces = HT.get("null");
		replyString = ReplyPieces[generator.nextInt(ReplyPieces.length)];
		return replyString;
	}

	public String[] combine(String[] words){
		String[] phrases;
		int wLength = words.length;
		int amount = wLength*wLength;
		int pos = 0;
		phrases = new String[amount];
		for(int k=0;k<wLength;k++){
			for(int i=0;i<wLength-k;i++){
				if(i==0)
					phrases[pos] = words[k];
				else
					phrases[pos] = phrases[pos - 1] + " " + words[k+i];
				pos++;
			}
		}
		return phrases;
	}

	// -- Returns the given string in lowercase form with punctuation at the end
	public String cleanup(String input){
		boolean prevPunc = true;
		input = input.toLowerCase();
		String noPunc = "";
		String punc = "";
		char[] chars = input.toCharArray();
		// -- For each character of the input, check if it is a letter or a space
		for(int i=0;i<chars.length;i++){
			if(((chars[i] > 95) && (chars[i] < 123)) || (chars[i]==32)){
				// -- If it is, add it to the new phrase
				noPunc += chars[i];
				prevPunc = false;
			}
			// -- If it is not, add it to the punctuation string
			else {
				if(prevPunc)
					// Keep consecutive punctuations together
					punc += chars[i];
				else {
					punc += " " + chars[i];
					prevPunc = true;
				}
			}
		}
		return noPunc + punc;
	}

}
