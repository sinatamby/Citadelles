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
			for(int i=0;i<this.getJoueur().nbQuartiersDansCite();i++) {
				if(this.getJoueur().getQuartier(i).getType()=="NOBLE") {
					this.getJoueur().ajouterPieces(1);
				}
			}
			if (this.getJoueur().quartierPresentDansCite("École de Magie")) {
				this.getJoueur().ajouterPieces(1);
			}
		}
		
	}
	public void utiliserPouvoirAvatar() {
		if(this.joueur!=null && this.assassine==false) {
			System.out.println("Je prends la courronne");
			getJoueur().setPossedeCouronne(true);
		}
	}
}
