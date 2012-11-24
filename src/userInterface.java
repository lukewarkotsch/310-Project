import java.net.UnknownHostException; 	// Ignoring this exception
import java.util.*;

public class userInterface {

	public static void main(String[] args) throws UnknownHostException {

		HTcontroller HTcontroller = new HTcontroller(); // Controls HashTable related functions
		CVcontroller CVcontroller = new CVcontroller(); // Controls Conversation related functions
		STcontroller STcontroller = new STcontroller(); // Controls Statistic related functions
		DBcontroller DBcontroller = new DBcontroller(); // Controls Database related functions

		Scanner scan = new Scanner(System.in);

		// Instance data initialization
		String UserInput = ""; // Holds input from user
		String MyReply = ""; // Holds the agents reply
		String PrevReply = ""; // Holds the agents previous reply
		boolean end = false; // Used to determine if the conversation has ended

		// Output introduction to the application
		System.out.println("Welcome, you are now speaking with a mental health professional, " +
				"for best results please participate fully and answer any questions in detail. \n" +
				"Feel free to speak openly and discuss any sensitive topics as this conversation is completely confidential.\n");

		HTcontroller.changeResponses(0); // Initialize conversation tree

		// While the conversation has not ended, determine how to reply, and analyze the user input
		while (!end) {
			PrevReply = MyReply; // Store previous reply for future use
			System.out.print("You: ");
			UserInput = scan.nextLine();
			// If the user is confirming a question asked by the agent, add that reply to the user input string
			if(UserInput.contains("yes") || UserInput.contains("ya") || UserInput.contains("mhm") || UserInput.contains("sure"))
				UserInput+=" " + PrevReply.replace("?", ".");
			UserInput = CVcontroller.cleanup(UserInput); // Clean the string for analysis
			MyReply = CVcontroller.reply(UserInput, HTcontroller, STcontroller); // Determine the agents response
			STcontroller.updateStats(UserInput, HTcontroller.getSymp(), STcontroller.symptomStats); // Update symptoms shown by the user
			STcontroller.updateStats(UserInput, HTcontroller.getDis(), STcontroller.disorderStats); // Update disorders shown by the user
			STcontroller.triggerStats(UserInput, HTcontroller.getTrig()); // Store words the user has typed
			System.out.println("Doc: " + MyReply);
			if(MyReply.contains("diagnosis.")) // End the conversation when the agent outputs its final response in the conversation tree
				end=true;
		}

		System.out.print(STcontroller.getStats()); // Print symptoms show, and possible diagnosis of the user

		DBcontroller.addToSymptoms(STcontroller.symptoms, STcontroller.symptomStats); // Create a document for symptoms shown
		DBcontroller.addToDisorders(STcontroller.disorders, STcontroller.diagnoses); // Create a document for diagnosis given
		DBcontroller.addToConvo(); // Create a document for the conversation
		DBcontroller.addToConvos(); // Add the document to the Database

		//DBcontroller.printAll(); // Print all documents in the database
	}
}