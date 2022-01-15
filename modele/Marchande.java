package modele;

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
		if(this.joueur!=null && this.assassine==false) {
			for(int i=0;i<this.getJoueur().nbQuartiersDansCite();i++) {
				if(this.getJoueur().getQuartier(i).getType()=="COMMERCANT") {
					this.getJoueur().ajouterPieces(1);
				}
			}
			if (this.getJoueur().quartierPresentDansCite("École de Magie")) {
				this.getJoueur().ajouterPieces(1);
			}
		}
	}
	public void utiliserPouvoirAvatar(){
		this.getJoueur().ajouterPieces(1);
	}
}
