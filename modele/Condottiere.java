package modele;

public class Condottiere extends Personnage{
	//constructeur
	public Condottiere() {
		super("Condottiere",8,Caracteristiques.CONDOTTIERE);
	}
	//méthodes
	public void percevoirRessourcesSpecifiques() {
		for(int i=0;i<this.getJoueur().nbQuartiersDansCite();i++) {
			if(this.getJoueur().getQuartier(i).getType()=="MILITAIRE") {
				this.getJoueur().ajouterPieces(1);
			}
		}
	}
	
	public void utiliserPouvoir() {
		
	}
}
