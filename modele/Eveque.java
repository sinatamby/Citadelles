package modele;

public class Eveque extends Personnage{
	//constructeur
	public Eveque(){
		super("Eveque",5,Caracteristiques.EVEQUE);
	}
	//méthodes
	public void utiliserPouvoir(){}
	
	public void percevoirRessourcesSpecifiques() {
		if(this.joueur!=null && this.assassine==false) {
			for(int i=0;i<this.getJoueur().nbQuartiersDansCite();i++) {
				if(this.getJoueur().getQuartier(i).getType()=="RELIGIEUX") {
					this.getJoueur().ajouterPieces(1);
				}
			}
			if (this.getJoueur().quartierPresentDansCite("École de Magie")) {
				this.getJoueur().ajouterPieces(1);
			}
		}
	}
	public void utiliserPouvoirAvatar() {}
}
