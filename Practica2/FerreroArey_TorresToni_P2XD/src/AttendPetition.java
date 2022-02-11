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
		String[] serverSentences = { "Buenos d�as.", "Buenas tardes.", "�C�mo est�n ustedes?",
				"�Ustedes... refiri�ndose a m�?", "A usted.", "Pues somos unos ustedes muy solitarios.",
				"�Est�n ustedes solos?",
				"Ustedes no s� como estar�n, yo, que soy usted, estoy m�s solo que un chorizo de Cantimpalo.",
				"Un momento, est� usted equivocado.", "�Est�n acompa�ados los chorizos de Cantimpalo?",
				"No lo s�. Usted ha dicho textualmente: <<Yo, que soy usted.>> Y sin �nimo de interferir en su ego, que yo sepa usted es usted, pero jam�s ser� yo.",
				"�C�mo que yo jam�s ser� yo!",
				"Yo, refiri�ndome a usted, ser� yo, siempre que usted sea yo; pero yo, refiri�ndome a usted, que soy m�, jam�s ser� yo.",
				"O sea que yo debo de ser m� si no soy usted a pesar de ser yo. Pues yo no entiendo esto de usted ni de m�.",
				"Uno es uno siempre." };
		String reciveSentence;
		
		try {
			try {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				reciveSentence = inFromClient.readLine();
				while ((!reciveSentence.equals("Buenos d�as")) && (!reciveSentence.equals("Buenas tardes"))) {
					reciveSentence = inFromClient.readLine();
				}
				System.out.println("RECIVED: " + reciveSentence);
				if (reciveSentence.equals("Buenos d�as")) {
					outToClient.writeBytes("Buenas tardes\n");
				}
				else {
					outToClient.writeBytes("Buenos d�as\n");
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
