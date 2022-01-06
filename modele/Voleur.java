package modele;

import java.util.Random;

import controleur.Interaction;

public class Voleur extends Personnage{
	//constructeur
	public Voleur() {
		super("Voleur",2,Caracteristiques.VOLEUR);
	}
	//méthode
	public void utiliserPouvoir() {
		int i=1;
		int lecture=0;
		System.out.println("Quel personnage voulez-vous voler ?");
		while(i<=this.getPlateau().getNombrePersonnages()) {
			System.out.println(i+" "+this.getPlateau().getPersonnage(i-1).getNom());
			i++;
		}
		System.out.print("Votre choix : ");
		boolean continu=true;
		do {
			try {
				lecture=Interaction.lireUnEntier();
				if(this.getPlateau().getPersonnage(lecture-1).getRang()<=2) {
					throw new Exception();
				} else {
					this.getPlateau().getPersonnage(lecture-1).setVole();
					this.getJoueur().ajouterPieces(this.getPlateau().getJoueur(lecture-1).nbPieces());
					this.getPlateau().getJoueur(lecture-1).retirerPieces(this.getPlateau().getJoueur(lecture-1).nbPieces());
					continu=false;
				}//
			}catch (Exception e){
				System.out.println("Vous ne pouvez pas vous voler ou voler l'assassin.");
				System.out.print("Votre choix : ");
			}
		} while (continu);			
	}
	public void utiliserPouvoirAvatar() {
		Random rand=new Random();
		int choixAlea=rand.nextInt(this.getPlateau().getNombrePersonnages());
		while (this.getPlateau().getPersonnage(choixAlea).getRang()<=2) {
			choixAlea=rand.nextInt(this.getPlateau().getNombrePersonnages());
		}
		this.getPlateau().getPersonnage(choixAlea).setVole();
		this.getJoueur().ajouterPieces(this.getPlateau().getJoueur(choixAlea).nbPieces());
		this.getPlateau().getJoueur(choixAlea).retirerPieces(this.getPlateau().getJoueur(choixAlea).nbPieces());
		System.out.println("Le personnage numéro "+(choixAlea+1)+" ("+this.getPlateau().getPersonnage(choixAlea).getNom()+") a été volé.");
	}
}
