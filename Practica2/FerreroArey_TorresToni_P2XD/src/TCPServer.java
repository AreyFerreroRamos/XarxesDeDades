import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	private static ServerSocket welcomeSocket;

	public static void main(String[] args) throws Exception {
			//El port l'introdueix l'usuari per paràmetre al configurar el programa.
		welcomeSocket = new ServerSocket(Integer.parseInt(args[0]));
		
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			new AttendPetition(connectionSocket).start();
		}
	}
}
