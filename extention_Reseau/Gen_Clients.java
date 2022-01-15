package extention_Reseau;

public class Gen_Clients {

	public static void main(String[] args) {
		String mon_id = String.format("Client_%d", 1);
		Thread_Client currrent_client = new Thread_Client(mon_id);
		Thread myThread= new Thread(currrent_client);
		myThread.start();
		currrent_client.send("je suis le client tu me reçois ?");
	}

}
