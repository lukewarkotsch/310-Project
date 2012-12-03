import java.net.UnknownHostException; 	// Ignoring this exception
import java.util.*;

public class MainController {

	HTcontroller HTcontroller; // Controls HashTable related functions
	CVcontroller CVcontroller; // Controls Conversation related functions
	STcontroller STcontroller; // Controls Statistic related functions
	DBcontroller DBcontroller; // Controls Database related functions
	Scanner scan;

	// Instance data initialization
	String UserInput; 	// Holds input from user
	String MyReply; 	// Holds the agents reply
	String PrevReply; 	// Holds the agents previous reply
	int turn;
	String name;
	boolean end; 		// Used to determine if the conversation has ended

	public MainController() throws UnknownHostException{
		HTcontroller = new HTcontroller(); // Controls HashTable related functions
		CVcontroller = new CVcontroller(); // Controls Conversation related functions
		STcontroller = new STcontroller(); // Controls Statistic related functions
		DBcontroller = new DBcontroller(); // Controls Database related functions
		scan = new Scanner(System.in);

		// Instance data initialization
		UserInput = ""; 	// Holds input from user
		MyReply = ""; 		// Holds the agents reply
		PrevReply = ""; 	// Holds the agents previous reply
		end = false; 		// Used to determine if the conversation has ended
		turn = 0;
		name = "User";
		HTcontroller.changeResponses(0); // Initialize conversation tree
	}

	// While the conversation has not ended, determine how to reply, and analyze the user input
	public String addToConvo(String s){
		turn ++;
		if(turn==3) name = s;
		UserInput = s;
		PrevReply = MyReply; // Store previous reply for future use
		MyReply = "";
		UserInput = CVcontroller.cleanup(UserInput); // Clean the string for analysis
		// If the user is confirming a question asked by the agent, add that reply to the user input string
		if(UserInput.contains("yes") || UserInput.contains("ya") || UserInput.contains("mhm") || UserInput.contains("sure")){
			UserInput+=" " + PrevReply.replace("?", ".");
			UserInput = CVcontroller.cleanup(UserInput); // Clean the string for analysis
			MyReply += CVcontroller.positiveReply();
		}
		MyReply += CVcontroller.reply(UserInput, HTcontroller, STcontroller); // Determine the agents response
		STcontroller.updateStats(UserInput, HTcontroller.getSymp(), STcontroller.symptomStats); // Update symptoms shown by the user
		STcontroller.updateStats(UserInput, HTcontroller.getDis(), STcontroller.disorderStats); // Update disorders shown by the user
		STcontroller.triggerStats(UserInput, HTcontroller.getTrig()); // Store words the user has typed
		if (MyReply.contains("Thank you for your time")){
			MyReply += "\n" + STcontroller.getStats();
			end = true;
		}
		turn ++;
		if(end) saveToDB();
		return MyReply;
	}

	public void saveToDB(){
		DBcontroller.addToSymptoms(STcontroller.symptoms, STcontroller.symptomStats); // Create a document for symptoms shown
		DBcontroller.addToDisorders(STcontroller.disorders, STcontroller.diagnoses); // Create a document for diagnosis given
		DBcontroller.addToConvo(); // Create a document for the conversation
		DBcontroller.addToConvos(name); // Add the document to the Database
		DBcontroller.printAll(); // Print all documents in the database
	}
}