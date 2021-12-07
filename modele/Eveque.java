package modele;

public class Eveque extends Personnage{
	//constructeur
	public Eveque(){
		super("Eveque",5,Caracteristiques.EVEQUE);
	}
	//m�thodes
	public void utiliserPouvoir(){}
	
	public void percevoirRessourcesSpecifiques() {
		for(int i=0;i<this.getJoueur().nbQuartiersDansCite();i++) {
			if(this.getJoueur().getQuartier(i).getType()=="RELIGIEUX") {
				this.getJoueur().ajouterPieces(1);
			}
		}
	}
}
