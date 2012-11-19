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
			responsesHT.put("1", new String[]{"Oh i see, where do you work, do you enjoy your work?", "3"});
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
		}
	}

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
}