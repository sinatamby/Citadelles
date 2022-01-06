package modele;

import java.util.Random;

public class Roi extends Personnage{
	//constructeur
	public Roi() {
		this.nom="Roi";
		this.rang=4;
		this.caracteristiques=Caracteristiques.ROI;
	}
	//méthodes
	public void utiliserPouvoir() {
		if(this.joueur!=null && this.assassine==false) {
			System.out.println("Je prends la courronne");
			getJoueur().setPossedeCouronne(true);
		}		
	}
	public void percevoirRessourcesSpecifiques() {
		if(this.joueur!=null && this.assassine==false) {
			int compte=0;
			for(int i=0;i<getJoueur().nbQuartiersDansCite();i++) {
				if(getJoueur().getQuartier(i).getType()=="NOBLE") {//getQuartier cible un Quartier dans le tableau Cite du Joueur
					compte++;
				}
			}
			this.joueur.ajouterPieces(compte);
			System.out.println("Vous avez "+compte+" pieces dans votre trésor !");
		}
	}
	public void utiliserPouvoirAvatar() {
		if(this.joueur!=null && this.assassine==false) {
			System.out.println("Je prends la courronne");
			getJoueur().setPossedeCouronne(true);
		}
	}
}
