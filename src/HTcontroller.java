import java.util.Hashtable;

public class HTcontroller {

	Hashtable<String, String[]> responsesHT;
	Hashtable<String, int[]> symptomsHT;
	Hashtable<String, int[]> disordersHT;
	Hashtable<String, Integer> triggersHT;

	public HTcontroller(){
		responsesHT = new Hashtable<String, String[]>();
		initResp();
		symptomsHT = new Hashtable<String, int[]>();
		initSymp();
		disordersHT = new Hashtable<String, int[]>();
		initDis();
		triggersHT = new Hashtable<String, Integer>();
	}

	public Hashtable<String, String[]> getResp(){
		return responsesHT;
	}
	public Hashtable<String, int[]> getSymp(){
		return symptomsHT;
	}
	public Hashtable<String, int[]> getDis(){
		return disordersHT;
	}
	public Hashtable<String, Integer> getTrig(){
		return triggersHT;
	}

	private void initResp(){
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

	private void initSymp(){
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

	private void initDis(){
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
	}
}