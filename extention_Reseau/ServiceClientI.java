package extention_Reseau;

import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.io.*;

public class ServiceClientI  implements Runnable{ 
	// FINISH string 
	//   private static final  	String Finish=""+(char) 4;;
	private static final  	String Finish="end";

	private  Socket ma_connection;      
	private  String id;
	private PrintWriter output;
	private BufferedReader input;

	private String c_ip;
	private int c_port;

	private  void terminer(){
		try{
			if (ma_connection != null)      
			{System.out.format("Terminaison pour T_%s\n", id); ma_connection.close();} 
		}
		catch (IOException e) {
			System.out.format("Terminaison pour T_%s\n", id);
			e.printStackTrace();
		}
		return;
	}

	public ServiceClientI(Socket la_connection, String mid){
		this.ma_connection= la_connection;
		this.id=mid;
		System.out.format("Thread T_%s créé pour traiter la connection\n",id); 

		try{ 
			InputStreamReader isr = new InputStreamReader(ma_connection.getInputStream(), "UTF-8");
			input = new BufferedReader(isr) ; 
			output = new PrintWriter(ma_connection.getOutputStream() , true);

			String c_ip = ma_connection.getInetAddress().toString() ;
			int c_port= ma_connection.getPort();
		} 
		catch (Exception e1) {
			System.out.format("Erreur d initialisation") ;e1.printStackTrace();} 	


	}

	public  void run(){
		String  message_lu = new String();
		while (  true  ) {	


			if(Thread.interrupted()){
				System.out.format( "[%s] :Service interompu par le serveur, je m'arrete\n" ,id)  ;
				terminer(); 
				return; 
			}
			try {
				message_lu = input.readLine();
			} 
			catch (SocketTimeoutException to)  {continue;}  
			catch (IOException ioe) { ;} 

			if (message_lu.contains(Finish) ){
				System.out.format ("[%s] :  [%s] recu, Transmission finie\n",id,message_lu);
				output.println("Fermeture de la connexion");
				terminer();
				return;		
			}
		}

	}

	public void send(String message) {
		System.out.println("Envoye message sur client"+ id+ ": "+message);
		this.output.format("[serveur] : %s \n",message);
	}

	
	public String read() throws IOException {
		String message_lu = input.readLine();
		return(message_lu);
	}


}



