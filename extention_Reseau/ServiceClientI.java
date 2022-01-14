package extention_Reseau;

import java.net.*;
import java.util.concurrent.TimeoutException;
import java.io.*;

public class ServiceClientI  implements Runnable{ 
    // FINISH string 
 //   private static final  	String Finish=""+(char) 4;;
	private static final  	String Finish="end";

    private  Socket ma_connection;      
    private  String id;

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

    public ServiceClientI(Socket la_connection, String mid)
    {
	ma_connection= la_connection;
	this.id=mid;
	System.out.format("Thread T_%s créé pour traiter la connection\n",id);  
    }

    public  void run(){
    	BufferedReader flux_entrant=null;
    	PrintWriter ma_sortie =null;
    	
    	try{ 
    	
	    InputStreamReader isr = new InputStreamReader(ma_connection.getInputStream(), "UTF-8");
	     flux_entrant = new BufferedReader(isr) ; 

	    System.out.format("[%s] Tampon de lecture attache\n",id);
	    
	    ma_sortie = new PrintWriter(ma_connection.getOutputStream() , true);

	    String c_ip = ma_connection.getInetAddress().toString() ;
	    int c_port= ma_connection.getPort();
	    
	    System.out.format("[%s] client admis IP %s  sur le port %d\n", id,c_ip, c_port);
	    ma_sortie.format("[%s] : Hello %s  sur le port %d,  vous etes, pour faire simple, disons Admis\n" ,  id, c_ip, c_port );  
	    ma_connection.setSoTimeout(10);
    	} 
    	catch (Exception e1) {
		System.out.format("Erreur d initialisation") ;e1.printStackTrace();} 	
	
	    String  message_lu = new String();
	    int line_num =0 ;
	    while (  true  ) 
 	    {
	    	if(Thread.interrupted())
 	    		{
			    System.out.format( "[%s] :Service interompu par le serveur, je m'arrete\n" ,id)  ;
 	    		terminer(); 
 	    		return; 
 	    		}
	    	try {
				message_lu = flux_entrant.readLine();
			} 
	    	catch (SocketTimeoutException to)  {continue;}  
	    	catch (IOException ioe) { ;} 
	    	if (message_lu == null){
		    	System.out.println( "Client deconnecté, je termine\n" )  ;
			    terminer();
			    return; }
		    System.out.format( "%s [line_%d]--> [%s]]\n", id,line_num, message_lu);
	    	if (message_lu.contains(Finish) ){
	    		System.out.format ("[%s] :  [%s] recu, Transmission finie\n",id,message_lu);
	    		ma_sortie.println("Fermeture de la connexion");
	    		terminer();
	    		return;		
		    }
	    line_num++;
	    }
 	    
    }
}
	    
	    
    	
