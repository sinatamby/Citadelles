package extentionReseau;

import java.net.*;
import java.io.*;

public class Thread_Client implements Runnable {
	private int serveurPort = 12000;
	private String serveurIp = "172.16.80.255";
	private final String FINISH = "" + (char) 4;
	private String id;
	private BufferedReader Tampon_Lecture = null;
	private PrintWriter ma_sortie = null;

	public Thread_Client( String monId) {id=monId; } 
	public Thread_Client(String hote, int port, String mon_id) {
		this.serveurIp = hote;	
		this.serveurPort = port;
		this.id = mon_id;
	}
	public void run() {
		Socket la_connection = null;
		try {
			la_connection = new Socket(serveurIp, serveurPort);
			Tampon_Lecture = new BufferedReader(new InputStreamReader(
					la_connection.getInputStream()));
			ma_sortie = new PrintWriter(la_connection.getOutputStream(), true);
		} catch (IOException e) {
				System.out.println(e);System.exit(-1);
		}
		System.out.format("%s: Contact Reussi avec %s:%d\n", id, serveurIp,
				serveurPort);
		for (int i = 0; i < 10; i++) {
			ma_sortie.format("%s: message %d\n", id, i);	
			try {
				Thread.sleep( (int)( 3000*Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		
		ma_sortie.format("%s\n",FINISH);
		
		if (la_connection !=null)
			try {
				la_connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}