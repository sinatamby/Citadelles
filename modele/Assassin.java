package modele;

import java.util.Random;
import controleur.Interaction;

public class Assassin extends Personnage{
	//constructeur
	public Assassin() {
		super("Assassin",1,Caracteristiques.ASSASSIN);
	}
	//m�thode
	public void utiliserPouvoir() {
		int i=1;
		int lecture=0;
		System.out.println("Quel personnage voulez-vous assassiner ?");
		while(i<=this.getPlateau().getNombrePersonnages()) {
			System.out.println(i+" "+this.getPlateau().getPersonnage(i-1).getNom());
			i++;
		}
		System.out.print("Votre choix : ");
		boolean continu=true;
		do {
			try {
				lecture=Interaction.lireUnEntier();
				if(this.getPlateau().getPersonnage(lecture-1).getRang()==1) {
					throw new Exception();
				} else {
					this.getPlateau().getPersonnage(lecture-1).setAssassine();
					continu=false;
				}
			} catch(Exception e) {
				System.out.println("Vous ne pouvez pas vous assassiner.");
				System.out.print("Votre choix : ");
			}
		} while(continu);
		
	}
	public void utiliserPouvoirAvatar() {
		Random rand=new Random();
		int choixAlea=rand.nextInt(this.getPlateau().getNombrePersonnages());
		while (this.getPlateau().getPersonnage(choixAlea).getRang()==1) {
			choixAlea=rand.nextInt(this.getPlateau().getNombrePersonnages());
		}
		this.getPlateau().getPersonnage(choixAlea).setAssassine();
		System.out.println("Le personnage num�ro "+(choixAlea+1)+" ("+this.getPlateau().getPersonnage(choixAlea).getNom()+") a �t� assassin�.");
	}
}
