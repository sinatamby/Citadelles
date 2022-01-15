package extention_Reseau;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Thread_Client implements Runnable {
	private int serveurPort = 12000;
	private String serveurIp = "127.0.0.1";
	private final String FINISH = "" + (char) 4;
	private String id;
	private BufferedReader input = null;
	private PrintWriter output = null;
	private Socket la_connection= null;
	Scanner scanner = new Scanner(System.in);

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
			//try {
				//System.out.println(this.read());
				this.parse();
			//} catch (IOException e) {
				//e.printStackTrace();
			//}

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

	public void send(String message) {
		System.out.println("SEND2SERVER : " +message);
		this.output.format(message); 
	}
	public void parse() {
		String[] cmd = null;
		String message = null;
		try {
			message=this.read();
			cmd = message.split(":",0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("COMMANDE  ==> -"+cmd[0]+"-");
		switch (cmd[0]) {
		case "SLEEP ":
			System.out.println("okok");
			this.sleep();
			break;
		case "SAY":
			this.say(cmd[1]);
			break;
			
		case "INPUT":
			this.input(cmd[1]);
			break;
		default:
			break;
		}

	}



public void sleep() {
	System.out.println("-dors-");
	boolean dors=true;
	while (dors) {
		try {
			String cmd = this.read();
			if (cmd.equals("WAKEUP ")){
				dors=false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	System.out.println("-reveille-");
}
private void input(String message) {
	System.out.print("[server] :"+message);
	String line = scanner.nextLine();
	this.send(line);
}

private void say(String message) {
	System.out.println("[server] :"+message);
}



}