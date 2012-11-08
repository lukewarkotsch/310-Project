import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
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
		mongoDB = mongoInstance.getDB("doc-talk"); 			// Attempts to get an existing DB instance from mongoInstance, creates one if it does not exist
		convos = mongoDB.getCollection("conversations"); 	// Attempts to get an existing collection from chatterbox, creates one if it does not exist
		symptoms = new BasicDBObject();
		disorders = new BasicDBObject();
		triggers = new BasicDBObject();
		convo = new BasicDBObject();
	}
	
	public void addToSymptoms(String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			symptoms.put(keys[i], values[i]);
	}

	public void addToDisorders(String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			disorders.put(keys[i], values[i]);
	}

	public void addToTriggers(String[] keys, int[] values){
		for(int i=0; i<keys.length; i++)
			triggers.put(keys[i], values[i]);
	}

	public void addToConvo(){
		convo.put("symptoms", symptoms);
		convo.put("diagnoses", disorders);
		convo.put("triggers", triggers);
	}

	public void addToConvos(){
		Date date = new Date();
		convo.put("Date", date.toString());
		convos.insert(convo);
	}
}
