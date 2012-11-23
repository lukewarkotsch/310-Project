import java.net.UnknownHostException; 	// Ignoring this exception
import java.util.*;

public class userInterface {

	public static void main(String[] args) throws UnknownHostException {

		HTcontroller HTcontroller = new HTcontroller();
		CVcontroller CVcontroller = new CVcontroller();
		STcontroller STcontroller = new STcontroller();
		DBcontroller DBcontroller = new DBcontroller();

		Scanner scan = new Scanner(System.in);

		// Variables for use in conversation analysis
		String UserInput = "";
		String PrevUserInput = "";
		String MyReply = "";
		String PrevReply = "";
		boolean end = false;

		System.out.println("Welcome, you are now speaking with a mental health professional, " +
				"for best results please participate fully and answer any questions in detail. \n" +
				"Feel free to speak openly and discuss any sensitive topics as this conversation is completely confidential.\n");

		HTcontroller.changeResponses(0);

		while (!end) {
			PrevUserInput = UserInput;
			PrevReply = MyReply;
			System.out.print("You: ");
			UserInput = scan.nextLine();
			if(UserInput.contains("yes") || UserInput.contains("ya") || UserInput.contains("mhm") || UserInput.contains("sure"))
				UserInput+=" " + PrevReply.replace("?", ".");
			UserInput = CVcontroller.cleanup(UserInput);
			//MyReply = CVcontroller.findReply(UserInput, HTcontroller.getResp());
			MyReply = CVcontroller.reply(UserInput, HTcontroller, STcontroller);
			STcontroller.updateStats(UserInput, HTcontroller.getSymp(), STcontroller.symptomStats);
			STcontroller.updateStats(UserInput, HTcontroller.getDis(), STcontroller.disorderStats);
			STcontroller.triggerStats(UserInput, HTcontroller.getTrig());
			/*if(MyReply.equals("NULL"))
				MyReply = CVcontroller.findReply(CVcontroller.cleanup(PrevReply), HTcontroller.getResp());*/
			System.out.println("Doc: " + MyReply);
			if(MyReply.contains("diagnosis."))
				end=true;
		}

		System.out.print(STcontroller.getStats());

		DBcontroller.addToSymptoms(STcontroller.symptoms, STcontroller.symptomStats);
		DBcontroller.addToDisorders(STcontroller.disorders, STcontroller.diagnoses);
		DBcontroller.addToConvo();
		DBcontroller.addToConvos();

		//DBcontroller.printAll();
	}
}