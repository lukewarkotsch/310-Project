import java.net.UnknownHostException;
import java.util.Date;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class DBcontroller {
	private Mongo mongoInstance;
	private DB mongoDB;
	private DBCollection convos;

	private BasicDBObject symptoms;
	private BasicDBObject disorders;
	private BasicDBObject triggers;
	private BasicDBObject convo;

	public DBcontroller() throws UnknownHostException{
		mongoInstance = new Mongo("localhost", 27017);		// Connects to local host Mongo Instance on port 27017
		mongoDB = mongoInstance.getDB("easy-diagnosis"); 	// Attempts to get an existing DB instance from mongoInstance, creates one if it does not exist
		convos = mongoDB.getCollection("conversations"); 	// Attempts to get an existing collection from easy-diagnosis, creates one if it does not exist
		symptoms = new BasicDBObject();
		disorders = new BasicDBObject();
		triggers = new BasicDBObject();
		convo = new BasicDBObject();
	}

	// Adds users symptom points to the symptoms document
	public void addToSymptoms(String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			symptoms.put(keys[i], values[i]);
	}

	// Adds users disorder points to the disorders document
	public void addToDisorders(String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			disorders.put(keys[i], values[i]);
	}

	// Adds all words entered by the user to the triggers document
	public void addToTriggers(String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			triggers.put(keys[i], values[i]);
	}

	// Adds each sub-document to the conversation document
	public void addToConvo(){
		convo.put("symptoms", symptoms);
		convo.put("diagnoses", disorders);
		convo.put("triggers", triggers);
	}

	// Adds the conversation document to the collection with a date stamp
	public void addToConvos(String name){
		Date date = new Date();
		convo.put("Date", date.toString());
		convo.put("Name", name);
		convos.insert(convo);
	}

	// Prints out all conversation documents in the collection
	public void printAll(){
		DBCursor cursor = convos.find();
		System.out.print(cursor.next());
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		cursor.close();
	}

	// Empties collection from the database
	public void clear(){
		mongoInstance.dropDatabase("easy-diagnosis");
		System.out.print("Dropped easy-diagnosis database from localhost\n");
	}

	public String getStats(){
		// [happiness, excitement, anger, aggression, sadness, fear, anxiety, confusion, memory loss, cognitive loss, delusion, hallucination]
		// [manic disorder, IED, depression, bipolar, anxiety disorder, amnesia, altzheimers, schizophrenia]
		int[] sympCounts = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
		int[] diagCounts = new int[]{0,0,0,0,0,0,0,0};
		DBCursor cursor = convos.find();
		while(cursor.hasNext()) {
			DBObject thing = cursor.next();
			BSONObject innerthing = (BSONObject) thing.get("symptoms");
			BSONObject innerthing2 = (BSONObject) thing.get("diagnoses");
			sympCounts[0] += Integer.parseInt(innerthing.get("happiness").toString());
			sympCounts[1] += Integer.parseInt(innerthing.get("excitement").toString());
			sympCounts[2] += Integer.parseInt(innerthing.get("anger").toString());
			sympCounts[3] += Integer.parseInt(innerthing.get("aggression").toString());
			sympCounts[4] += Integer.parseInt(innerthing.get("sadness").toString());
			sympCounts[5] += Integer.parseInt(innerthing.get("fear").toString());
			sympCounts[6] += Integer.parseInt(innerthing.get("anxiety").toString());
			sympCounts[7] += Integer.parseInt(innerthing.get("confusion").toString());
			sympCounts[8] += Integer.parseInt(innerthing.get("memory loss").toString());
			sympCounts[9] += Integer.parseInt(innerthing.get("cognitive loss").toString());
			sympCounts[10] += Integer.parseInt(innerthing.get("delusion").toString());
			sympCounts[11] += Integer.parseInt(innerthing.get("hallucination").toString());
			
			diagCounts[0] += Integer.parseInt(innerthing2.get("manic disorder").toString());
			diagCounts[1] += Integer.parseInt(innerthing2.get("IED").toString());
			diagCounts[2] += Integer.parseInt(innerthing2.get("depression").toString());
			diagCounts[3] += Integer.parseInt(innerthing2.get("bipolar").toString());
			diagCounts[4] += Integer.parseInt(innerthing2.get("anxiety disorder").toString());
			diagCounts[5] += Integer.parseInt(innerthing2.get("amnesia").toString());
			diagCounts[6] += Integer.parseInt(innerthing2.get("altzheimers").toString());
			diagCounts[7] += Integer.parseInt(innerthing2.get("schizophrenia").toString());
		}
		return 
				"---------------------------------------------------------\n" +
				"Total symptom scores across all patients: \n" +
				"---------------------------------------------------------\n" +
				"Happiness: " + sympCounts[0] + "\n" +
				"Excitement: " + sympCounts[1] + "\n" +
				"Anger: " + sympCounts[2] + "\n" +
				"Aggression: " + sympCounts[3] + "\n" +
				"Sadness: " + sympCounts[4] + "\n" +
				"Fear: " + sympCounts[5] + "\n" +
				"Anxiety: " + sympCounts[6] + "\n" +
				"Confusion: " + sympCounts[7] + "\n" +
				"Memory Loss: " + sympCounts[8] + "\n" +
				"Cognitive Loss: " + sympCounts[9] + "\n" +
				"Delusion: " + sympCounts[10] + "\n" +
				"Hallucination: " + sympCounts[11] + "\n" + 
				"---------------------------------------------------------\n" +
				"Total diagnosis scores across all patients: \n" +
				"---------------------------------------------------------\n" +
				"Manic Disorder: " + diagCounts[0] + "\n" +
				"IED: " + diagCounts[1] + "\n" +
				"Depression: " + diagCounts[2] + "\n" +
				"Bipolar: " + diagCounts[3] + "\n" +
				"Anxiety Disorder: " + diagCounts[4] + "\n" +
				"Amnesia: " + diagCounts[5] + "\n" +
				"Altzheimers: " + diagCounts[6] + "\n" +
				"Schizophrenia: " + diagCounts[7] + "\n";
	}
}
