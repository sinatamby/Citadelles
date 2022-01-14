package extention_Reseau;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Serveur extends ServerSocket {
	private final static int port = 12000; /* Port d'écoute */

	public Serveur() throws IOException {
		super(port);
		System.out.println("[Serveur] : Serveur Jouet lancé sur " + (port));
	}
	public void execute() throws IOException {// execute le serveur
		Socket maConnection;
		List<Thread> myThreads = new ArrayList<Thread>();
		int numClient = 0;
		while (numClient < 5){
			System.out.println("[Serveur]: waiting for connexion number "+ numClient);
			maConnection = accept();
			numClient+=1;
			String c_ip = maConnection.getInetAddress().toString();
			int c_port = maConnection.getPort();
			System.out.format("[Serveur] : Arr. Client IP %s sur %d\n", c_ip, c_port);
			System.out.format("[Serveur]: Creation du thread T_%d\n", c_port);
			Thread cThread= new Thread(new ServiceClientI(maConnection, "T_" + c_port));
			myThreads.add(cThread);
			cThread.start(); 
		}
		System.out.format("Closing all Connections\n"); //ferme les connection
		for (Thread item: myThreads)
			item.interrupt();
		close();
	}

	public static void main(String[] args) throws IOException {
		try (Serveur connectionManager = new Serveur()) {
			connectionManager.execute();
		}
	}
}

	
	
	