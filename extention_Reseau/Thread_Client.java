package extention_Reseau;

import java.net.*;
import java.io.*;

public class Thread_Client implements Runnable {
	private int serveurPort = 12000;
	private String serveurIp = "127.0.0.1";
	private final String FINISH = "" + (char) 4;
	private String id;
	private BufferedReader input = null;
	private PrintWriter output = null;
	private Socket la_connection= null;

	public Thread_Client(String monId) {
		this.id = monId;

		Socket la_connection = null;
		try {
			la_connection = new Socket(serveurIp, serveurPort);
			input = new BufferedReader(new InputStreamReader(la_connection.getInputStream()));
			output = new PrintWriter(la_connection.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println(e);System.exit(-1);
		}
		System.out.format("%s: Contact Reussi avec %s:%d\n", id, serveurIp,
				serveurPort);
	}

	public void run() {
		boolean connecte=true;
		while (connecte) {
			try {
				System.out.println(this.read());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}


		output.format("%s\n",FINISH);

		if (la_connection !=null)
			try {
				la_connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}


	public String read() throws IOException {
		String message_lu = input.readLine();
		return(message_lu);
	}


	public void sleep() {
		boolean wake=false;

		while (wake) {
			try {
				Thread.sleep( (int)( 3000*Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void send(String message) {
		System.out.println("Envoye message sur Serveur"+ id+ ": "+message);
		this.output.format("[Client] : %s \n",message); 
	}
}