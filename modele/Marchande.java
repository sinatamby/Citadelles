package modele;

import java.util.Random;

public class Marchande extends Personnage{
	//constructeur
	public Marchande() {
		super("Marchande",6,Caracteristiques.MARCHANDE);
	}
	//méthodes
	public void utiliserPouvoir(){
		this.getJoueur().ajouterPieces(1);
	}
	public void percevoirRessourcesSpecifiques() {
		for(int i=0;i<this.getJoueur().nbQuartiersDansCite();i++) {
			if(this.getJoueur().getQuartier(i).getType()=="COMMERCANT") {
				this.getJoueur().ajouterPieces(1);
			}
		}
	}
	public void utiliserPouvoirAvatar(){
		this.getJoueur().ajouterPieces(1);
	}
}
