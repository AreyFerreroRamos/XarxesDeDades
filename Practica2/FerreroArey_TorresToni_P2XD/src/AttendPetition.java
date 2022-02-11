import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class AttendPetition extends Thread {

	Socket connectionSocket;

	public AttendPetition(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
		try {
			connectionSocket.setSoTimeout(20000);
		} catch (SocketException exception) {
			exception.printStackTrace();
		}
	}

	public void run() {
		String[] serverSentences = { "Buenos días.", "Buenas tardes.", "¿Cómo están ustedes?",
				"¿Ustedes... refiriéndose a mí?", "A usted.", "Pues somos unos ustedes muy solitarios.",
				"¿Están ustedes solos?",
				"Ustedes no sé como estarán, yo, que soy usted, estoy más solo que un chorizo de Cantimpalo.",
				"Un momento, está usted equivocado.", "¿Están acompañados los chorizos de Cantimpalo?",
				"No lo sé. Usted ha dicho textualmente: <<Yo, que soy usted.>> Y sin ánimo de interferir en su ego, que yo sepa usted es usted, pero jamás será yo.",
				"¡Cómo que yo jamás seré yo!",
				"Yo, refiriéndome a usted, será yo, siempre que usted sea yo; pero yo, refiriéndome a usted, que soy mí, jamás será yo.",
				"O sea que yo debo de ser mí si no soy usted a pesar de ser yo. Pues yo no entiendo esto de usted ni de mí.",
				"Uno es uno siempre." };
		String reciveSentence;
		
		try {
			try {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				reciveSentence = inFromClient.readLine();
				while ((!reciveSentence.equals("Buenos días")) && (!reciveSentence.equals("Buenas tardes"))) {
					reciveSentence = inFromClient.readLine();
				}
				System.out.println("RECIVED: " + reciveSentence);
				if (reciveSentence.equals("Buenos días")) {
					outToClient.writeBytes("Buenas tardes\n");
				}
				else {
					outToClient.writeBytes("Buenos días\n");
				}
				reciveSentence = inFromClient.readLine();
				while (!reciveSentence.equals("Buenas noches")) {
					System.out.println("RECIVED: " + reciveSentence);
					outToClient.writeBytes(serverSentences[(int) (Math.random() * (serverSentences.length - 1))] + "\n");
					reciveSentence = inFromClient.readLine();
				}
				outToClient.writeBytes("Buenas noches");
				connectionSocket.close();
			}
			catch (SocketTimeoutException exception) {
				System.out.println("El servidor no ha obtingut resposta i s'ha desconectat.");
				connectionSocket.close();
			}
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
