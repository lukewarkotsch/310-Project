import java.util.Hashtable;
import java.util.Random;

public class CVcontroller {

	Random generator;
	
	public CVcontroller(){
		generator = new Random();
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
