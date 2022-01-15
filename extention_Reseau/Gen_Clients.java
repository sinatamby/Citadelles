package extention_Reseau;

public class Gen_Clients {

	public static void main(String[] args) {
		String mon_id = String.format("Client_%d", 1);
		Thread_Client currrent_client = new Thread_Client("127.0.0.1",12000,mon_id);
		Thread myThread= new Thread(currrent_client);
		myThread.start();
	}

}
