import com.mongodb.*; 					// Import all MongoDB drivers

import java.net.UnknownHostException; 	// Ignoring this exception
import java.util.*;

public class userInterface {

	// Hash tables for static data
	static Hashtable<String, String[]> responsesHT = new Hashtable<String, String[]>();
	static Hashtable<String, int[]> symptomsHT = new Hashtable<String, int[]>();
	static Hashtable<String, int[]> disordersHT = new Hashtable<String, int[]>();
	static Hashtable<String, Integer> triggersHT = new Hashtable<String, Integer>();

	// Random object for flexible decision making
	static Random generator = new Random();

	// Database JSON objects
	static BasicDBObject symptoms = new BasicDBObject();
	static BasicDBObject disorders = new BasicDBObject();
	static BasicDBObject triggers = new BasicDBObject();
	static BasicDBObject convo = new BasicDBObject();

	// Variables for use in conversation analysis
	static String UserInput = "";
	static String PrevUserInput = "";
	static String MyReply = "";
	static String PrevReply = "";
	static String space = " ";
	static String dash = "-";
	static String[] InputPieces;
	static String[] ReplyPieces;
	static int[] symptomStats = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
	static int[] disorderStats = new int[]{0,0,0,0,0,0,0,0};

	public static void main(String[] args) throws UnknownHostException {
		Mongo mongoInstance = new Mongo("localhost", 27017);				// Connects to local host Mongo Instance on port 27017
		DB chatterbox = mongoInstance.getDB("chatterbox"); 					// Attempts to get an existing DB instance from mongoInstance, creates one if it does not exist
		DBCollection convos = chatterbox.getCollection("conversations"); 	// Attempts to get an existing collection from chatterbox, creates one if it does not exist

		responsesHTinit();
		symptomsHTinit();
		disordersHTinit();

		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome, you are now speaking with a mental health professional, " +
				"for best results please participate fully and answer any questions in detail. \n" +
				"Feel free to speak openly and discuss any sensitive topics as this conversation is completely confidential.\n");

		while (!MyReply.equals("Doc: Goodbye, it was nice talking with you.")) {
			PrevUserInput = UserInput;
			PrevReply = MyReply;
			System.out.print("You: ");
			UserInput = cleanup(scan.nextLine());
			MyReply = findReply(UserInput);
			symptomStats(UserInput, symptomStats);
			disorderStats(UserInput, disorderStats);
			triggerStats(UserInput);
			if(MyReply.equals("NULL"))
				MyReply = findReply(cleanup(PrevReply));
			System.out.println("Doc: " + MyReply);
		}
	}


	//----------------------------------------CONVERSATION FUNCTIONS-----------------------------------------------
	public static String findReply(String searchString){
		String replyString;
		String[] SearchPieces = searchString.split(" ");
		String[] ReplyPieces = null;
		ReplyPieces = responsesHT.get(searchString);
		if (ReplyPieces == null){
			String[] phrases = combine(SearchPieces);
			int inc = 0;
			while(ReplyPieces == null && inc < phrases.length && phrases[inc]!=null){
				ReplyPieces = responsesHT.get(phrases[inc]);
				inc++;
			}
		}
		if(ReplyPieces == null)
			ReplyPieces = responsesHT.get("null");
		replyString = ReplyPieces[generator.nextInt(ReplyPieces.length)];
		return replyString;
	}

	public static String[] combine(String[] words){
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

	// -- Returns the given string in lowercase form with no punctuation
	public static String cleanup(String input){
		input = input.toLowerCase();
		String noPunc = "";
		char[] chars = input.toCharArray();
		// -- For each character of the input, check if it is a letter or a space
		for(int i=0;i<chars.length;i++){
			if(((chars[i] > 95) && (chars[i] < 123)) || (chars[i]==32))
				// -- If it is, add it to the new phrase
				noPunc += chars[i];
		}
		return noPunc;
	}

	// -- Updates all symptom stats from given sentence
	public static void symptomStats(String input, int[] stats){
		// Split phrase into array of words
		String[] words = input.split(" ");
		int[] vals;
		// -- For each word check symptom stats and update
		for(int i=0;i<words.length;i++){
			vals = symptomsHT.get(words[i]);
			if(vals!=null) {
				// -- For each value, add to current stat
				for(int j=0;j<stats.length;j++)
					stats[j]+=vals[j];
			}
		}
	}

	// -- Updates all disorder stats from given sentence
	public static void disorderStats(String input, int[] stats){
		// Split phrase into array of words
		String[] words = input.split(" ");
		int[] vals;
		// -- For each word check disorder stats and update
		for(int i=0;i<words.length;i++){
			vals = disordersHT.get(words[i]);
			if(vals!=null) {
				// -- For each value, add to current stat
				for(int j=0;j<stats.length;j++)
					stats[j]+=vals[j];
			}
		}
	}

	// -- Updates all trigger stats from given sentence
	public static void triggerStats(String input){
		// Split phrase into array of words
		String[] words = input.split(" ");
		int count;
		// -- For each word check trigger stats and update
		for(int i=0;i<words.length;i++){
			// If the word exists already, add 1 to count
			if (triggersHT.containsKey(words[i])){
				count = triggersHT.get(words[i]);
				triggersHT.put(words[i], (count+1));
			}
			// Otherwise initialize with count of 18
			else triggersHT.put(words[i], 1);
		}
	}
	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxCONVERSATION FUNCTIONSxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx


	//----------------------------------------DB INTERACTION FUNCTIONS-----------------------------------------------
	public static void addToSymptoms(BasicDBObject E, String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			E.put(keys[i], values[i]);
	}

	public static void addToDisorders(BasicDBObject D, String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			D.put(keys[i], values[i]);
	}

	public static void addToTriggers(BasicDBObject T, String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			T.put(keys[i], values[i]);
	}

	public static void addToConvo(BasicDBObject C, BasicDBObject E, BasicDBObject D , BasicDBObject T){
		C.put("symptoms", E);
		C.put("diagnoses", D);
		C.put("triggers", T);
	}

	public static void addToConvos(DBCollection Cs, BasicDBObject C){
		Date date = new Date();
		C.put("Date", date.toString());
		Cs.insert(C);
	}
	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxDB INTERACTION FUNCTIONSxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx


	//---------------------------------------HASH TABLE INITIALIZATION------------------------------------------------
	public static void responsesHTinit(){
		responsesHT.put("hello", new String[]{"Hello", "Hi there", "Hello, how are you today?", "Hi there, how have you been?"});
		responsesHT.put("hi", new String[]{"Hello", "Hi there", "Hello, how are you today?", "Hi there, how have you been?"});
		responsesHT.put("hey", new String[]{"Hello", "Hi there", "Hello, how are you today?", "Hi there, how have you been?"});
		responsesHT.put("yo", new String[]{"Hello", "Hi there", "Hello, how are you today?", "Hi there, how have you been?"});
		responsesHT.put("sup", new String[]{"Hello", "Hi there", "Hello, how are you today?", "Hi there, how have you been?"});
		responsesHT.put("how are you", new String[]{"Im doing fine, yourself?", "Im quite well thanks, and what about you?", "Im good, how are you?"});
		responsesHT.put("hows it going", new String[]{"Im doing fine, yourself?", "Im quite well thanks, and what about you?", "Im good, how are you?"});
		responsesHT.put("goodbye", new String[]{"Goodbye, it was nice talking with you."});
		responsesHT.put("bye", new String[]{"Goodbye, it was nice talking with you."});
		responsesHT.put("why", new String[]{"Thats not important, why dont you tell me something that you dont often talk about."});
		responsesHT.put("null", new String[]{"Tell me something that I dont know about you"});
		responsesHT.put("you", new String[]{"NULL"});
		responsesHT.put("new", new String[]{"Im not very interesting, why dont you tell me about yourself?"});
		responsesHT.put("your name", new String[]{"Dont worry about my name, you can just call me Doc"});
		responsesHT.put("Doc", new String[]{"Yes?"});
	}

	public static void symptomsHTinit(){		
		// - - - - - - - - - - Happy and Excited words - - - - - - - - - -
		// Order of symptom values in array ->  
		// [happiness, excitement, anger, aggression, sadness, fear, anxiety, confusion, memory loss, cognitive loss, delusion, hallucination]
		symptomsHT.put(":)", 					new int[]{4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put(":D", 					new int[]{4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("good", 					new int[]{4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("great", 				new int[]{5, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("!", 					new int[]{0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("happy", 				new int[]{7, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("excited", 				new int[]{2, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("fine", 					new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("thankyou", 				new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("thanks", 				new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("well", 					new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("comedy", 				new int[]{5, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("funny", 				new int[]{6, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("kiss", 					new int[]{8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("bliss", 				new int[]{10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("perfect", 				new int[]{10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - - - Angry and Aggressive words - - - - - - - - - -
		// Order of symptom values in array ->  
		// [happiness, excitement, anger, aggression, sadness, fear, anxiety, confusion, memory loss, cognitive loss, delusion, hallucination]
		symptomsHT.put("mad", 					new int[]{0, 0, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("angry", 				new int[]{0, 0, 7, 2, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("fuck you", 				new int[]{0, 0, 5, 10, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("stupid", 				new int[]{0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("hurt", 					new int[]{0, 0, 2, 7, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("hit", 					new int[]{0, 0, 4, 10, 0, 0, 0, 0, 0, 0, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - - - - - - Sad words - - - - - - - - - - - - - - -
		// Order of symptom values in array ->  
		// [happiness, excitement, anger, aggression, sadness, fear, anxiety, confusion, memory loss, cognitive loss, delusion, hallucination]
		symptomsHT.put("sad", 					new int[]{0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("depressed", 			new int[]{0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("cry", 					new int[]{0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("no", 					new int[]{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("cant", 					new int[]{0, 0, 0, 0, 3, 1, 1, 0, 0, 0, 0, 0});
		symptomsHT.put(":(", 					new int[]{0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put(":'(", 					new int[]{0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - - - Fearful and anxious words - - - - - - - - - -
		// Order of symptom values in array ->  
		// [happiness, excitement, anger, aggression, sadness, fear, anxiety, confusion, memory loss, cognitive loss, delusion, hallucination]
		symptomsHT.put("scared", 				new int[]{0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("hide", 					new int[]{0, 0, 0, 0, 0, 5, 3, 0, 0, 0, 0, 0});
		symptomsHT.put("jittery", 				new int[]{0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0});
		symptomsHT.put("nervous", 				new int[]{0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0});
		symptomsHT.put("frozen", 				new int[]{0, 0, 0, 0, 0, 7, 7, 0, 0, 4, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - Confused, memory loss, cognitive loss words - - - - -
		// Order of symptom values in array ->  
		// [happiness, excitement, anger, aggression, sadness, fear, anxiety, confusion, memory loss, cognitive loss, delusion, hallucination]
		symptomsHT.put("dont know", 			new int[]{0, 0, 0, 0, 0, 0, 0, 2, 4, 1, 0, 0});
		symptomsHT.put("not sure", 				new int[]{0, 0, 0, 0, 0, 0, 0, 3, 3, 2, 0, 0});
		symptomsHT.put("?", 					new int[]{0, 0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0});
		symptomsHT.put("cant remember", 		new int[]{0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0});
		symptomsHT.put("dont understand", 		new int[]{0, 0, 0, 0, 0, 0, 0, 8, 0, 5, 0, 0});
		symptomsHT.put("who are you", 			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0});
		symptomsHT.put("dont remember", 		new int[]{0, 0, 0, 0, 0, 0, 0, 0, 8, 2, 0, 0});
		symptomsHT.put("who am i", 				new int[]{0, 0, 0, 0, 0, 0, 0, 4, 10, 0, 0, 0});
		symptomsHT.put("why", 					new int[]{0, 0, 1, 0, 0, 0, 0, 6, 2, 2, 0, 0});
		symptomsHT.put("doesnt make sense", 	new int[]{0, 0, 2, 0, 0, 0, 0, 8, 0, 1, 0, 0});
		symptomsHT.put("confused", 				new int[]{0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0});
		symptomsHT.put("confusing", 			new int[]{0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - - - Delusive and hallucinatory words - - - - - -
		// Order of symptom values in array ->  
		// [happiness, excitement, anger, aggression, sadness, fear, anxiety, confusion, memory loss, cognitive loss, delusion, hallucination]
		symptomsHT.put("hearing things", 		new int[]{0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 10});
		symptomsHT.put("seeing things", 		new int[]{0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 8});
		symptomsHT.put("voices", 				new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 6});
		symptomsHT.put("whispers", 				new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 7});
		symptomsHT.put("whispering", 			new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 7});
		symptomsHT.put("visions", 				new int[]{0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 5});
		symptomsHT.put("out to get me", 		new int[]{0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 10, 0});
		symptomsHT.put("they", 					new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0});
		symptomsHT.put("colors", 				new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 4});
		symptomsHT.put("sounds", 				new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 4});
		symptomsHT.put("important", 			new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0});
		symptomsHT.put("famous", 				new int[]{2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0});
		symptomsHT.put("control my thoughts", 	new int[]{0, 0, 0, 0, 0, 4, 3, 0, 0, 0, 10, 0});
		symptomsHT.put("control me", 			new int[]{0, 0, 0, 0, 0, 4, 3, 0, 0, 0, 10, 0});
		symptomsHT.put("aliens", 				new int[]{0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 8, 0});
		symptomsHT.put("hear my thoughts", 		new int[]{0, 0, 0, 0, 0, 3, 4, 0, 0, 0, 8, 0});
		symptomsHT.put("world is ending", 		new int[]{0, 4, 0, 0, 2, 6, 0, 0, 0, 0, 5, 0});
		symptomsHT.put("end of the world", 		new int[]{0, 4, 0, 0, 2, 6, 0, 0, 0, 0, 5, 0});
		symptomsHT.put("affair", 				new int[]{0, 0, 5, 0, 5, 4, 4, 3, 0, 0, 3, 0});
		symptomsHT.put("my fault", 				new int[]{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 4, 0});
		symptomsHT.put("guilty", 				new int[]{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 4, 0});
		symptomsHT.put("messages", 				new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0});
		symptomsHT.put("special", 				new int[]{3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0});
		symptomsHT.put("deserve", 				new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0});
		symptomsHT.put("following me", 			new int[]{0, 0, 0, 0, 0, 5, 2, 0, 0, 0, 6, 0});
		symptomsHT.put("chosen", 				new int[]{0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0});
		symptomsHT.put("god", 					new int[]{0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// symptomsHT.put("", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
	}

	public static void disordersHTinit(){
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
	}
	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxHASH TABLE INITIALIZATIONxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

}