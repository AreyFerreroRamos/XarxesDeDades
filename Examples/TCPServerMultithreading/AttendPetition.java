package urv.sockets;

import java.io.*;
import java.net.*;

public class AttendPetition extends Thread{
	Socket connectionSocket;
	
	public AttendPetition(Socket s){
		this.connectionSocket=s;
	}
	
	public void run(){
		String clientSentence;
		String capitalizedSentence;
		BufferedReader inFromClient;
		
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			clientSentence = inFromClient.readLine();
			
			System.out.println("Received: " + clientSentence);
			
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			
			outToClient.writeBytes(capitalizedSentence);
			
			this.connectionSocket.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
