import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.io.IOException;

public class TCPClient {

	public static void main(String[] args) throws Exception {
			//L'adreça IP i el port els introdueix l'usuari per paràmetre al configurar el programa.
		Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		Scanner keyboard = new Scanner(System.in);
		String sendSentence="";
		String reciveSentence;

		clientSocket.setSoTimeout(10000);
		
		try {
			while (!sendSentence.equals("Buenas noches")) {
				try {
					sendSentence = keyboard.nextLine();
					outToServer.writeBytes(sendSentence + "\n");
					reciveSentence = inFromServer.readLine();
					System.out.println("FROM SERVER: " + reciveSentence);
				}
				catch (SocketTimeoutException exception) {
					System.out.println("El servidor no respon. Introdueix un altra frase.");
				}
			}
			keyboard.close();
			clientSocket.close();
		}
		catch (SocketException SocketException) {
			System.out.println("El servidor s'ha desconectat i s'ha acabat la conversa.");
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
