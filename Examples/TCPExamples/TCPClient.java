import java.io.*;
import java.net.*;

class TCPClient {
	public static void main(String argv[]) throws Exception {	
			//Variables auxiliars.
		String sentence;
		String modifiedSentence;
		
			//Variable que permet la lectura de teclat.
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			
			//Variable per establir coneixions amb server.
										//127.0.0.1
										//10.21.0.1
										//My IP: 10.110.173.116
		Socket clientSocket = new Socket("10.21.0.1", 6789);
			
			//Obtenir el canal de sortida.
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			//Obtenir el canal d'entrada.
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
			//Lectura de teclat. Ha d'acabar en \n.
		sentence = inFromUser.readLine();
			
			//Eniar al server la frase que ha teclejat.
		outToServer.writeBytes(sentence + '\n');
			
			//Llegir del server el que s'envia.
		modifiedSentence = inFromServer.readLine();
			
			//Mostrar el que ha contestat el server.
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
}