import java.net.UnknownHostException; 	// Ignoring this exception
import java.util.*;

public class userInterface {

	public static void main(String[] args) throws UnknownHostException {

		HTcontroller HTcontroller = new HTcontroller();

		CVcontroller CVcontroller = new CVcontroller();

		STcontroller STcontroller = new STcontroller();

		Scanner scan = new Scanner(System.in);

		// Variables for use in conversation analysis
		String UserInput = "";
		String PrevUserInput = "";
		String MyReply = "";
		String PrevReply = "";
		String[] InputPieces;
		String[] ReplyPieces;
		int turn = 0;
		boolean end = false;

		System.out.println("Welcome, you are now speaking with a mental health professional, " +
				"for best results please participate fully and answer any questions in detail. \n" +
				"Feel free to speak openly and discuss any sensitive topics as this conversation is completely confidential.\n");

		while (!end) {
			PrevUserInput = UserInput;
			PrevReply = MyReply;
			System.out.print("You: ");
			UserInput = CVcontroller.cleanup(scan.nextLine());
			turn ++;
			MyReply = CVcontroller.findReply(UserInput, HTcontroller.getResp());
			STcontroller.symptomStats(UserInput, HTcontroller.getSymp());
			STcontroller.disorderStats(UserInput, HTcontroller.getDis());
			STcontroller.triggerStats(UserInput, HTcontroller.getTrig());
			if(MyReply.equals("NULL"))
				MyReply = CVcontroller.findReply(CVcontroller.cleanup(PrevReply), HTcontroller.getResp());
			System.out.println("Doc: " + MyReply);
			turn ++;
			if(turn>10) end=true;
		}

		System.out.print(STcontroller.getStats());
	}
}