import com.mongodb.*; 					// Import all MongoDB drivers

import java.net.UnknownHostException; 	// Ignoring this exception
import java.util.*;
public class dbTest {

	public static void main(String[] args) throws UnknownHostException {

		System.out.println("//---------------------------------CONNECTING TO DB AND CREATING COLLECTION----------------------------------------------------------------");
		Mongo m = new Mongo("localhost", 27017); 					// Connects to local host Mongo Instance on port 27017
		DB db = m.getDB("testDB"); 									// Attempts to get an existing DB instance from m, creates one if it does not exist
		DBCollection coll = db.getCollection("testCollection"); 	// Attempts to get an existing collection from db, creates one if it does not exist
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//---------------------------------ITERATING THROUGH DB PRINTING COLLECTIONS---------------------------------------------------------------");
		Set<String> colls = db.getCollectionNames(); 	// Gets a set of all collections in the db
		for (String s : colls) {
			System.out.println(s); 						// Prints all collections
		}
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//---------------------------------CREATING A JSON OBJECT AND ADDING IT TO THE COLLECTION--------------------------------------------------");
		BasicDBObject doc = new BasicDBObject(); 	// Creates new JSON object named doc
		// Put in key/value pairs for doc
		doc.put("name", "MongoDB");
		doc.put("type", "database");
		doc.put("count", 1);

		BasicDBObject info = new BasicDBObject(); 	// Creates new JSON object named info
		// Put in key/value pairs for info
		info.put("x", 203);
		info.put("y", 102);

		// Put info JSON object into doc JSON object
		doc.put("info", info);

		// Insert doc JSON object into collection
		coll.insert(doc);
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//---------------------------------RETRIEVING THE FIRST DOCUMENT OF A COLLECTION-----------------------------------------------------------");
		// Create new JSON object and retrieve first doc object from collection
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc); 	// Print out doc JSON object
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//---------------------------------ADDING MULTIPLE DOCUMENTS AND RETRIEVE COUNT OF DOCUMENTS IN COLLECTION---------------------------------");
		// Loop creating and inserting simple JSON objects into same collection
		for (int i=0; i < 100; i++) {
			coll.insert(new BasicDBObject().append("i", i));
		}

		// Print the number of documents in collection
		System.out.println(coll.getCount());
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//---------------------------------USING CURSOR TO QUERY COLLECTION RETRIEVING DOCUMENTS---------------------------------------------------");
		// DBCursor object allows iteration over the set of documents that matched the query
		DBCursor cursor = coll.find();			 	// Finds all documents (SELECT *)
		try { 										// Attempts to grab the next document
			while(cursor.hasNext()) {
				System.out.println(cursor.next()); 	// Prints the current document
			}
		} finally {
			cursor.close(); 						// Closes the cursor when there are no more documents
		}
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//---------------------------------CREATING AN OBJECT TO RUN A MORE SPECIFIC QUERY---------------------------------------------------------");
		// Create new object query on
		BasicDBObject query = new BasicDBObject();

		// Put in key/value pairs for the query
		query.put("i", 71);

		// Uses cursor to find all documents matching the query
		cursor = coll.find(query);

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

		query.put("i", new BasicDBObject("$gt", 50));  	// $gt for greater than

		cursor = coll.find(query);

		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next()); 		// Prints all docs where i > 50
			}
		} finally {
			cursor.close();
		}

		query = new BasicDBObject();

		query.put("i", new BasicDBObject("$gt", 20).append("$lte", 30));  // $gt for greater than and $lte for less than or equal to

		cursor = coll.find(query);

		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next()); 		// Prints all docs where 20 < i <= 30
			}
		} finally {
			cursor.close();
		}
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//----------------------------------CREATING AN INDEX---------------------------------------------------------------------------------------");
		coll.createIndex(new BasicDBObject("i", 1));  // Create an ascending index on i
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//----------------------------------RETRIEVING A LIST OF INDEXES IN DB---------------------------------------------------------------------");
		List<DBObject> list = coll.getIndexInfo(); 	// Create List of DB objects

		for (DBObject o : list) {
			System.out.println(o); 					// Print out each index
		}
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//----------------------------------RETRIEVING A LIST OF DATABASES IN MONGO INSTANCE-------------------------------------------------------");
		for (String s : m.getDatabaseNames()) { 	// Gets DB names from mongo instance m
			System.out.println(s);
		}
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");


		System.out.println("//----------------------------------DROPPING A DATABASE FROM A MONGO INSTANCE--------------------------------------------------------------");
		m.dropDatabase("mydb"); 	// Drops DB from mongo instance m
		System.out.println("//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");

	}

}
