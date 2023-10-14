import java.io.*;
import java.net.*;

class TCPServer {
	private static ServerSocket welcomeSocket;

	public static void main(String argv[]) throws Exception {
			//Variable auxiliars.
		String clientSentence;
		String capitalizedSentence;
		
		welcomeSocket = new ServerSocket(6789);

		while (true) {
				//Espera a que algun client es conecti.
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
				//Rebre la frase del client.
			clientSentence = inFromClient.readLine();
			System.out.println("Received: " + clientSentence);
				
				//Envia la frase al client.
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
		}
	}
}