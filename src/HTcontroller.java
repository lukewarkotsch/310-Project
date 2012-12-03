import java.util.Hashtable;

public class HTcontroller {

	// Instance data used by the controller
	Hashtable<String, String[]> responsesHT; // Contains the agents conversational tree
	Hashtable<String, int[]> symptomsHT; // Contains points towards symptoms the user has shown
	Hashtable<String, int[]> disordersHT; // Contains points towards disorders the user is displaying
	Hashtable<String, Integer> triggersHT; // Contains words the user has be typing

	// Constructor initializing the instance data
	public HTcontroller(){
		responsesHT = new Hashtable<String, String[]>();
		symptomsHT = new Hashtable<String, int[]>();
		initSymp();
		disordersHT = new Hashtable<String, int[]>();
		initDis();
		triggersHT = new Hashtable<String, Integer>();
	}

	// Getter methods for each instance variable
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

	// Initializes the hash table containing trigger words as keys, and an array of integers as values (points towards respective symptom)
	private void initSymp(){
		// - - - - - - - - - - Happy and Excited words - - - - - - - - - -
		// Order of symptom values in array ->  
		// [happiness, excitement, anger, aggression, sadness, fear, anxiety, confusion, memory loss, cognitive loss, delusion, hallucination]
		symptomsHT.put(":)", 					new int[]{4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put(":D", 					new int[]{4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("good", 					new int[]{4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
		symptomsHT.put("great", 				new int[]{5, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
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

	// Initializes the hash table containing trigger words as keys, and an array of integers as values (points towards respective disorder)
	private void initDis(){
		// - - - - - - - - Manic words - - - - - - - - 
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
		disordersHT.put("can do anything", 		new int[]{10, 0, 0, 0, 0, 0, 0, 0});
		disordersHT.put("irritable", 			new int[]{5, 4, 0, 0, 3, 0, 0, 0});
		disordersHT.put("horny", 				new int[]{2, 0, 0, 0, 0, 0, 0, 0});
		disordersHT.put("sexual", 				new int[]{1, 0, 0, 0, 0, 0, 0, 0});
		disordersHT.put("talk fast", 			new int[]{2, 0, 0, 0, 0, 0, 0, 0});
		disordersHT.put("energetic", 			new int[]{4, 0, 0, 0, 0, 0, 0, 0});
		disordersHT.put("cant sleep", 			new int[]{2, 0, 1, 0, 0, 0, 0, 0});
		disordersHT.put("amazing", 				new int[]{3, 0, 0, 0, 0, 0, 0, 0});
		disordersHT.put("annoying", 			new int[]{3, 2, 0, 0, 1, 0, 0, 0});
		disordersHT.put("really good", 			new int[]{3, 0, 0, 0, 0, 0, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - IED words - - - - - - - - 
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
		disordersHT.put("really mad", 			new int[]{0, 3, 0, 0, 0, 0, 0, 0});
		disordersHT.put("really angry", 		new int[]{0, 3, 0, 0, 0, 0, 0, 0});
		disordersHT.put("hurt", 				new int[]{0, 2, 0, 0, 0, 0, 0, 0});
		disordersHT.put("break things", 		new int[]{0, 3, 0, 0, 0, 0, 0, 0});
		disordersHT.put("punch", 				new int[]{0, 5, 0, 0, 0, 0, 0, 0});
		disordersHT.put("outburst", 			new int[]{0, 5, 0, 0, 0, 0, 0, 0});
		disordersHT.put("yell", 				new int[]{0, 4, 0, 0, 0, 0, 0, 0});
		disordersHT.put("violent", 				new int[]{0, 8, 0, 0, 0, 0, 0, 0});
		disordersHT.put("assault", 				new int[]{0, 10, 0, 0, 0, 0, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - Depression words - - - - - - - - 
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
		disordersHT.put("hurt myself", 			new int[]{0, 0, 8, 0, 0, 0, 0, 0});
		disordersHT.put("suicide", 				new int[]{0, 0, 10, 0, 0, 0, 0, 0});
		disordersHT.put("hang", 				new int[]{0, 0, 7, 0, 0, 0, 0, 0});
		disordersHT.put("depressed", 			new int[]{0, 0, 10, 0, 0, 0, 0, 0});
		disordersHT.put("cant concentrate", 	new int[]{0, 0, 3, 0, 0, 0, 0, 0});
		disordersHT.put("cant decide", 			new int[]{0, 0, 2, 0, 0, 0, 0, 0});
		disordersHT.put("tired", 				new int[]{0, 0, 3, 0, 0, 0, 0, 0});
		disordersHT.put("lazy", 				new int[]{0, 0, 1, 0, 0, 0, 0, 0});
		disordersHT.put("guilty", 				new int[]{0, 0, 3, 0, 0, 0, 0, 0});
		disordersHT.put("my fault", 			new int[]{0, 0, 3, 0, 0, 0, 0, 0});
		disordersHT.put("worthless", 			new int[]{0, 0, 5, 0, 0, 0, 0, 0});
		disordersHT.put("helpless", 			new int[]{0, 0, 5, 0, 0, 0, 0, 0});
		disordersHT.put("not interesting", 		new int[]{0, 0, 4, 0, 0, 0, 0, 0});
		disordersHT.put("lost interest", 		new int[]{0, 0, 4, 0, 0, 0, 0, 0});
		disordersHT.put("not anymore", 			new int[]{0, 0, 2, 0, 0, 0, 0, 0});
		disordersHT.put("ache", 				new int[]{0, 0, 1, 0, 1, 0, 0, 0});
		disordersHT.put("cramp", 				new int[]{0, 0, 1, 0, 1, 0, 0, 0});
		disordersHT.put("empty", 				new int[]{0, 0, 2, 0, 0, 0, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - Anxiety words - - - - - - - - 
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
		disordersHT.put("restless", 			new int[]{0, 0, 0, 0, 4, 0, 0, 0});
		disordersHT.put("on edge", 				new int[]{0, 0, 0, 0, 4, 0, 0, 0});
		disordersHT.put("edgy", 				new int[]{0, 0, 0, 0, 4, 0, 0, 0});
		disordersHT.put("cant concentrate", 	new int[]{0, 0, 2, 0, 3, 0, 0, 0});
		disordersHT.put("cant think", 			new int[]{0, 0, 2, 0, 3, 0, 0, 0});
		disordersHT.put("goes blank", 			new int[]{0, 0, 0, 0, 3, 0, 0, 0});
		disordersHT.put("tense", 				new int[]{0, 0, 0, 0, 5, 0, 0, 0});
		disordersHT.put("avoid", 				new int[]{0, 0, 0, 0, 5, 0, 0, 0});
		disordersHT.put("stay away from", 		new int[]{0, 0, 0, 0, 5, 0, 0, 0});
		disordersHT.put("try not to", 			new int[]{0, 0, 0, 0, 5, 0, 0, 0});
		disordersHT.put("nightmare", 			new int[]{0, 0, 0, 0, 6, 0, 0, 0});
		disordersHT.put("flashback", 			new int[]{0, 0, 0, 0, 6, 0, 0, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - Amnesia words - - - - - - - - 
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
		disordersHT.put("cant learn", 			new int[]{0, 0, 0, 0, 0, 10, 0, 0});
		disordersHT.put("cant remember", 		new int[]{0, 0, 0, 0, 0, 6, 4, 0});
		disordersHT.put("head trauma", 			new int[]{0, 0, 0, 0, 0, 10, 0, 0});
		disordersHT.put("head injury", 			new int[]{0, 0, 0, 0, 0, 10, 0, 0});
		disordersHT.put("brain trauma", 		new int[]{0, 0, 0, 0, 0, 10, 0, 0});
		disordersHT.put("tremors", 				new int[]{0, 0, 0, 0, 0, 3, 0, 0});
		disordersHT.put("seizures", 			new int[]{0, 0, 0, 0, 0, 3, 0, 0});
		disordersHT.put("brain injury", 		new int[]{0, 0, 0, 0, 0, 10, 0, 0});
		disordersHT.put("confused", 			new int[]{0, 0, 0, 0, 0, 5, 3, 0});
		disordersHT.put("disoriented", 			new int[]{0, 0, 0, 0, 0, 3, 2, 0});
		disordersHT.put("memory loss", 			new int[]{0, 0, 0, 0, 0, 8, 6, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - Altzheimers words - - - - - - - - 
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
		disordersHT.put("forget", 				new int[]{0, 0, 0, 0, 0, 0, 5, 0});
		disordersHT.put("forgot", 				new int[]{0, 0, 0, 0, 0, 3, 5, 0});
		disordersHT.put("notes", 				new int[]{0, 0, 0, 0, 0, 0, 3, 0});
		disordersHT.put("reminders", 			new int[]{0, 0, 0, 0, 0, 0, 3, 0});
		disordersHT.put("cant plan", 			new int[]{0, 0, 0, 0, 0, 0, 4, 0});
		disordersHT.put("takes me longer", 		new int[]{0, 0, 0, 0, 0, 0, 3, 0});
		disordersHT.put("cant anymore", 		new int[]{0, 0, 0, 0, 0, 0, 2, 0});
		disordersHT.put("what time is it", 		new int[]{0, 0, 0, 0, 0, 0, 7, 0});
		disordersHT.put("where am i", 			new int[]{0, 0, 0, 0, 0, 0, 8, 0});
		disordersHT.put("cant read", 			new int[]{0, 0, 0, 0, 0, 0, 3, 0});
		disordersHT.put("cant find", 			new int[]{0, 0, 0, 0, 0, 0, 4, 0});
		disordersHT.put("misplaced", 			new int[]{0, 0, 0, 0, 0, 0, 5, 0});
		disordersHT.put("lost", 				new int[]{0, 0, 0, 0, 0, 0, 5, 0});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x

		// - - - - - - - - Schizophrenia words - - - - - - - - 
		// Order of disorder values in array ->
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
		disordersHT.put("dont trust", 			new int[]{0, 0, 0, 0, 0, 0, 0, 5});
		disordersHT.put("dont go out", 			new int[]{0, 0, 0, 0, 1, 0, 0, 3});
		disordersHT.put("dont feel", 			new int[]{0, 0, 0, 0, 0, 0, 0, 5});
		disordersHT.put("cant feel", 			new int[]{0, 0, 0, 0, 0, 0, 0, 5});
		disordersHT.put("cant cry", 			new int[]{0, 0, 0, 0, 0, 0, 0, 6});
		disordersHT.put("not fun", 				new int[]{0, 0, 0, 0, 0, 0, 0, 2});
		disordersHT.put("sleep alot", 			new int[]{0, 0, 1, 0, 0, 0, 0, 2});
		disordersHT.put("dont sleep", 			new int[]{0, 0, 1, 0, 1, 0, 0, 2});
		disordersHT.put("delusions", 			new int[]{0, 0, 0, 0, 0, 0, 0, 10});
		disordersHT.put("hallucinations", 		new int[]{0, 0, 0, 0, 0, 0, 0, 10});
		disordersHT.put("for me", 				new int[]{0, 0, 0, 0, 0, 0, 0, 3});
		disordersHT.put("i am famous", 			new int[]{0, 0, 0, 0, 0, 0, 0, 8});
		disordersHT.put("planting thoughts", 	new int[]{0, 0, 0, 0, 0, 0, 0, 10});
		disordersHT.put("transmitting thoughts", new int[]{0, 0, 0, 0, 0, 0, 0, 10});
		disordersHT.put("not interested", 		new int[]{0, 0, 2, 0, 0, 0, 0, 3});
		// x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
	}

	// Uses the given parameter to determine what branch the conversation is on, and where to go next in the conversation tree
	// Each key in the hashtable is a choice of what could be said at the current point in conversation
	// Each value is a two element String array, the first being a response, and the second being which branch to follow next
	public void changeResponses(int num){
		responsesHT.clear();
		switch(num){
		case 0:
			responsesHT.put("0", new String[]{"Hello, I'm Dr.Feelgood. Why don't you begin by telling me about yourself, what is your name?", "1"});
			break;
		case 1:
			responsesHT.put("0", new String[]{"And what do you like to do with your spare time?", "2"});
			responsesHT.put("1", new String[]{"Do you have a job?", "2"});
			break;
		case 2:
			responsesHT.put("0", new String[]{"That's quite interesting, do you often have time for fun?", "3"});
			responsesHT.put("1", new String[]{"Where do you work, do you enjoy your work?", "3"});
			break;
		case 3:
			responsesHT.put("0", new String[]{"What about family, do you have any siblings?", "4"});
			responsesHT.put("1", new String[]{"Would you consider yourself someone with alot of friends?", "5"});
			break;
		case 4:
			responsesHT.put("0", new String[]{"Do you have any family members that you don't get along with?", "6"});
			break;
		case 5:
			responsesHT.put("0", new String[]{"How many friends can you trust and depend on?", "6"});
			break;
		case 6:
			responsesHT.put("0", new String[]{"Now that I know a little about you, can you tell me the reason you are here?", "7"});
			break;
		case 7:
			responsesHT.put("0", new String[]{"Are you experiencing any emotions more often than normal?", "8"});
			responsesHT.put("1", new String[]{"Is there anything about your thoughts that concerns you?", "8"});
			break;
		case 8:
			responsesHT.put("0", new String[]{"Is there anything in your life that frustrates you?", "9"});
			responsesHT.put("1", new String[]{"Is there anything in your life that makes you sad?", "9"});
			responsesHT.put("2", new String[]{"Is there anything in your life that excites you?", "9"});
			break;
		case 9:
			responsesHT.put("0", new String[]{"Is there anything in your life that frightens you?", "10"});
			responsesHT.put("1", new String[]{"Is there anything in your life that angers you?", "10"});
			responsesHT.put("2", new String[]{"Is there anything in your life that makes you happy?", "10"});
			break;
		case 10:
			responsesHT.put("0", new String[]{"Why is it that you have come to seek help now, what has changed?", "11"});
			break;
		case 12:
			responsesHT.put("0", new String[]{"Do you ever feel as if you could accomplish anything?", "13"});
			responsesHT.put("1", new String[]{"Do you ever experience short periods of innappropriate anger?", "14"});
			responsesHT.put("2", new String[]{"Do you ever experience long periods of sadness or lonliness?", "15"});
			break;
		case 13:
			responsesHT.put("0", new String[]{"Why do you think you feel this way?", "16"});
			break;
		case 14:
			responsesHT.put("0", new String[]{"What do you think is the cause?", "16"});
			break;
		case 15:
			responsesHT.put("0", new String[]{"Do these feelings often confuse you?", "16"});
			break;
		case 16:
			responsesHT.put("0", new String[]{"Between happiness, excitement, sadness and anger, what emotion do you experience most often?", "100"});
			break;
		case 20:
			responsesHT.put("0", new String[]{"Tell me about something that often frustrates you.", "21"});
			responsesHT.put("1", new String[]{"Do you ever take out your anger in a physical way?", "22"});
			responsesHT.put("2", new String[]{"Have you hurt anyone before?", "23"});
			break;
		case 21:
			responsesHT.put("0", new String[]{"Does it seem appropriate to be so bothered by it?", "24"});
			responsesHT.put("1", new String[]{"How could you avoid it in the future?", "24"});
			break;
		case 22:
			responsesHT.put("0", new String[]{"Has this form of expression ever got you in trouble?", "25"});
			break;
		case 23:
			responsesHT.put("0", new String[]{"Has this form of expression ever got you in trouble?", "25"});
			responsesHT.put("1", new String[]{"Do you believe they deserved it?", "26"});
			break;
		case 24:
			responsesHT.put("0", new String[]{"Why do you think you feel this way?", "27"});
			break;
		case 25:
			responsesHT.put("0", new String[]{"Why do you think you feel this way?", "27"});
			responsesHT.put("1", new String[]{"What do you think is the cause of these feelings?", "27"});
			responsesHT.put("2", new String[]{"Do these feelings often confuse you?", "27"});
			break;
		case 26:
			responsesHT.put("0", new String[]{"What do you think is the cause of these feelings?", "27"});
			responsesHT.put("1", new String[]{"Do these feelings often confuse you?", "27"});
			break;
		case 27:
			responsesHT.put("0", new String[]{"Along with anger, do you ever experience periods of unexplained happiness or excitement?", "28"});
			responsesHT.put("1", new String[]{"Along with anger, do you ever experience periods of unexplained sadness or loneliness?", "28"});
			break;
		case 28:
			responsesHT.put("0", new String[]{"Between anger, aggression, happiness, and excitement, which do you experience most often?", "100"});
			break;
		case 30:
			responsesHT.put("0", new String[]{"How often do you feel sad?", "31"});
			responsesHT.put("1", new String[]{"Do you often feel alone?", "32"});
			responsesHT.put("2", new String[]{"Tell me about something that makes you sad?", "33"});
			responsesHT.put("3", new String[]{"Why do you feel this way?", "34"});
			break;
		case 31:
			responsesHT.put("0", new String[]{"Does it stop you from doing things you used to enjoy?", "35"});
			break;
		case 32:
			responsesHT.put("0", new String[]{"Why do you feel alone?", "35"});
			responsesHT.put("1", new String[]{"Does it stop you from doing things you used to enjoy?", "35"});
			break;
		case 33:
			responsesHT.put("0", new String[]{"Why does it make you sad?", "35"});
			break;
		case 34:
			responsesHT.put("0", new String[]{"How do you think this could change?", "35"});
			break;
		case 35:
			responsesHT.put("0", new String[]{"Do you ever feel angry or frustrated for no reason?", "36"});
			responsesHT.put("0", new String[]{"Do you ever experience periods of unexplained joy or confidence?", "36"});
			break;
		case 36:
			responsesHT.put("0", new String[]{"Between sadness, anger, and happiness, which do you feel most often?", "100"});
			break;
		case 40:
			responsesHT.put("0", new String[]{"Tell me about something that worries you.", "41"});
			responsesHT.put("1", new String[]{"Do you have any reasons to feel afraid?", "42"});
			responsesHT.put("2", new String[]{"Are you often stressed or anxious?", "43"});
			break;
		case 41:
			responsesHT.put("0", new String[]{"Do you think it's reasonable for this to worry you?", "44"});
			responsesHT.put("1", new String[]{"Have you ever seaked help before?", "44"});
			responsesHT.put("2", new String[]{"What harm could it cause you?", "44"});
			break;
		case 42:
			responsesHT.put("0", new String[]{"Have you ever seeked help before?", "44"});
			responsesHT.put("1", new String[]{"What harm could it cause you?", "44"});
			responsesHT.put("2", new String[]{"Does this fear affect your daily life?", "44"});
			break;
		case 43:
			responsesHT.put("0", new String[]{"Do you think it's reasonable for this to worry you?", "44"});
			responsesHT.put("1", new String[]{"Have you ever seeked help before?", "44"});
			responsesHT.put("2", new String[]{"Do you know why?", "44"});
			responsesHT.put("3", new String[]{"What makes you feel this way?", "44"});
			break;
		case 44:
			responsesHT.put("0", new String[]{"Are you often confused with these feelings?", "45"});
			responsesHT.put("1", new String[]{"Have you ever felt that you are not yourself?", "45"});
			break;
		case 45:
			responsesHT.put("0", new String[]{"Between fear, anxiety, and confusion, which do you experience most often?", "100"});
			break;
		case 50:
			responsesHT.put("0", new String[]{"Do you often lose your train of thought?", "51"});
			responsesHT.put("1", new String[]{"Have you ever had troubles speaking?", "52"});
			responsesHT.put("2", new String[]{"Do you ever get lost or forget where you are going?", "53"});
			responsesHT.put("3", new String[]{"Have you ever noticed any change in you ability to solve problems or puzzles?", "54"});
			responsesHT.put("4", new String[]{"Do you find it hard to learn new skills?", "55"});
			
			break;
		case 51:
			responsesHT.put("0", new String[]{"When you do, is it easy to remember?", "56"});
			responsesHT.put("1", new String[]{"Have you ever had troubles speaking?", "52"});
			responsesHT.put("2", new String[]{"Do you ever get lost or forget where you are going?", "53"});
			break;
		case 52:
			responsesHT.put("0", new String[]{"Explain what was happening.", "56"});
			break;
		case 53:
			responsesHT.put("0", new String[]{"Have you ever had troubles speaking?", "52"});
			responsesHT.put("1", new String[]{"How often do you fail to recognize someone who knows you?", "56"});
			responsesHT.put("2", new String[]{"Have you ever noticed any change in you ability to solve problems or puzzles?", "54"});
			break;
		case 54:
			responsesHT.put("0", new String[]{"Has this caused you to change your habits at all?", "56"});
			break;
		case 55:
			responsesHT.put("0", new String[]{"Have you ever noticed any change in you ability to solve problems or puzzles?", "54"});
			responsesHT.put("1", new String[]{"What problem did you have?", "56"});
			responsesHT.put("0", new String[]{"Has this caused you to change your habits at all?", "56"});
			break;
		case 56:
			responsesHT.put("0", new String[]{"Is it hard for you to understand ideas that are not your own?", "57"});
			responsesHT.put("1", new String[]{"Do you need help from other people to complete daily tasks?", "57"});
			responsesHT.put("2", new String[]{"Describe to me your day so far.", "57"});
			break;
		case 57:
			responsesHT.put("0", new String[]{"Between confused, fogetfull, and unable to think, which describes you most often?", "100"});
			break;
		case 60:
			responsesHT.put("0", new String[]{"Do you ever feel out of touch with reality?", "61"});
			responsesHT.put("1", new String[]{"Is it difficult for you to convince others of your beliefs?", "61"});
			responsesHT.put("2", new String[]{"Are your thoughts ever out of your control?", "61"});
			break;
		case 61:
			responsesHT.put("0", new String[]{"Is it difficult for you to find interest in anything?", "62"});
			responsesHT.put("1", new String[]{"How often do you go out or have fun?", "62"});
			break;
		case 62:
			responsesHT.put("0", new String[]{"Do you ever hear things that no one else does?", "63"});
			responsesHT.put("1", new String[]{"Do you ever see things that no one else does?", "63"});
			responsesHT.put("2", new String[]{"Is there anything you regret or feel guilty about?", "63"});
			break;
		case 63:
			responsesHT.put("0", new String[]{"Have you been chosen for any greater purpose?", "64"});
			responsesHT.put("1", new String[]{"Do you believe the end of the world to be an immediate threat?", "64"});
			break;
		case 64:
			responsesHT.put("0", new String[]{"Pleae tell me which, if any, of these words relate to you: visions, whispering, important, aliens, followed, or conspiracy.", "100"});
			break;
		case 100:
			responsesHT.put("0", new String[]{"Thank you for your time, I think that I have learned enough about you for an accurate diagnosis.", "101"});
		}
	}
	
	public String getPath(String currentNode){
		String path = currentNode + "\n";
		for(int i=0; i<responsesHT.size();i++)
			path+="\t - " + responsesHT.get(Integer.toString(i))[0] + "\n";
		return path;
	}
}