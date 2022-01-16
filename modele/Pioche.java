package modele;

import java.util.ArrayList;
import java.util.Collections; //methode shuffle
//import java.util.Random;

public class Pioche {
	//accesseurs
	private ArrayList<Quartier> liste;
	//constructeurs
	public Pioche(){
		this.liste=new ArrayList<Quartier>();
	}
	//méthodes
	public Quartier piocher() {
		Quartier retour=null;
		if(nombreElements()!=0) {
			retour=this.liste.get(0);
			this.liste.remove(0);
		} else {
			retour=null;
		}
		return retour;
	}
	public void ajouter(Quartier nouveau) {
		this.liste.add(nouveau);
	}
	public int nombreElements() {
		return this.liste.size();
	}
	/*
	public void melanger() {//méthode
		int nbEltsPioche=nombreElements();
		Random generateur=new Random();
		
		int i=generateur.nextInt(nbEltsPioche);
		int j=generateur.nextInt(nbEltsPioche);
		
	}
	*/
	public void melanger(){ // melange les éléments de la liste
		Collections.shuffle(liste);
	}
}
