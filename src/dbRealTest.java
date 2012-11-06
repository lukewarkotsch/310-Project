import com.mongodb.*; 					// Import all MongoDB drivers

import java.net.UnknownHostException; 	// Ignoring this exception
import java.util.*;
public class dbRealTest {

	public static void main(String[] args) throws UnknownHostException {
		
		System.out.println("//---------------------------------CONNECTING TO MONGO INSTANCE----------------------------------------------------------------------------");
		Mongo mongoInstance = new Mongo("localhost", 27017); 				// Connects to local host Mongo Instance on port 27017
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");

        
		System.out.println("//----------------------------------DROPPING A DATABASE FROM A MONGO INSTANCE--------------------------------------------------------------");
        mongoInstance.dropDatabase("chatterbox"); 	// Drops DB from mongo instance m
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
		
        
		System.out.println("//---------------------------------ADDING A DATABASE AND A COLLECTION----------------------------------------------------------------------");
        DB chatterbox = mongoInstance.getDB("chatterbox"); 					// Attempts to get an existing DB instance from mongoInstance, creates one if it does not exist
		DBCollection convos = chatterbox.getCollection("conversations"); 	// Attempts to get an existing collection from chatterbox, creates one if it does not exist
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//---------------------------------ITERATING THROUGH DB PRINTING COLLECTIONS---------------------------------------------------------------");
		Set<String> colls = chatterbox.getCollectionNames(); 	// Gets a set of all collections in the db
		for (String s : colls) {
		    System.out.println(s); 								// Prints all collections
		}
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
		
		
		System.out.println("//---------------------------------CREATING A JSON OBJECT AND ADDING IT TO THE COLLECTION--------------------------------------------------");
		BasicDBObject convo = new BasicDBObject(); 	// Creates new JSON object named convo

        BasicDBObject emotions = new BasicDBObject(); 	// Creates new JSON object named emotions
        // Put in key/value pairs for emotions
        emotions.put("scared", 75);
        emotions.put("sad", 25);
		
        BasicDBObject diagnoses = new BasicDBObject(); 	// Creates new JSON object named diagnoses
        // Put in key/value pairs for diagnoses
        diagnoses.put("schizophrenia", 80);
        diagnoses.put("depression", 20);
        
        BasicDBObject triggers = new BasicDBObject(); 	// Creates new JSON object named triggers
        // Put in key/value pairs for triggers
        triggers.put("afraid", 4);
        triggers.put("not", 2);

        // Put documents into convo
        convo.put("emotions", emotions);
        convo.put("diagnoses", diagnoses);
        convo.put("triggers", triggers);


        // Insert convo JSON object into collection
        convos.insert(convo);
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
        
        
        System.out.println("//---------------------------------RETRIEVING THE FIRST DOCUMENT OF A COLLECTION-----------------------------------------------------------");
        // Create new JSON object and retrieve first doc object from collection
        DBObject doc = convos.findOne();
        System.out.println(doc); 	// Print out doc JSON object
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
        
        
        System.out.println("//---------------------------------RETRIEVING COUNT OF DOCUMENTS IN COLLECTION-----------------------------------------------------------------");
        // Print the number of documents in collection
        System.out.println(convos.getCount());
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
        
        
        System.out.println("//---------------------------------USING CURSOR TO QUERY COLLECTION RETRIEVING DOCUMENTS---------------------------------------------------");
        // DBCursor object allows iteration over the set of documents that matched the query
        DBCursor cursor = convos.find();			// Finds all documents (SELECT *)
        try { 										// Attempts to grab the next document
            while(cursor.hasNext()) {
                System.out.println(cursor.next()); 	// Prints the current document
            }
        } finally {
            cursor.close(); 						// Closes the cursor when there are no more documents
        }
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
        
        
        System.out.println("//---------------------------------CREATING AN OBJECT TO RUN A MORE SPECIFIC QUERY---------------------------------------------------------");
        // Create new object to query on
        BasicDBObject query = new BasicDBObject();
        BasicDBObject query1 = new BasicDBObject();

        // Put in key/value pairs for the query
        query1.put("schizophrenia", 80);
        query.put("diagnoses", query1);

        // Uses cursor to find all documents matching the query
        cursor = convos.find(query);

        // Prints out all documents that the cursor found
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
	
	
        System.out.println("//----------------------------------USING $OPERATOR IN QUERY OBJECT------------------------------------------------------------------------");
        query = new BasicDBObject();

        query.put("sad", new BasicDBObject("$gt", 20));  // $gt for greater than

        cursor = convos.find(query);

        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next()); 		// Prints all docs where sad > 20
            }
        } finally {
            cursor.close();
        }
        
        query = new BasicDBObject();

        query.put("sad", new BasicDBObject("$gt", 20).append("$lte", 30));  // $gt for greater than and $lte for less than or equal to

        cursor = convos.find(query);

        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next()); 		// Prints all docs where 20 < sad <= 30
            }
        } finally {
            cursor.close();
        }
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
	
        
        System.out.println("//----------------------------------CREATING AN INDEX---------------------------------------------------------------------------------------");
        convos.createIndex(new BasicDBObject("depression", -1));  // Create a descending index on depression
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
    	
        
        System.out.println("//----------------------------------RETRIEVING A LIST OF INDEXES IN DB---------------------------------------------------------------------");
        List<DBObject> list = convos.getIndexInfo(); 	// Create List of DB objects

        for (DBObject o : list) {
            System.out.println(o); 						// Print out each index
        }
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
	
        
        System.out.println("//----------------------------------RETRIEVING A LIST OF DATABASES IN MONGO INSTANCE-------------------------------------------------------");
        for (String s : mongoInstance.getDatabaseNames()) { 	// Gets DB names from mongo instance m
            System.out.println(s);
        }
        System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");

	}

}
