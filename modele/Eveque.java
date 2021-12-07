package modele;

public class Eveque extends Personnage{
	//constructeur
	public Eveque(){
		super("Eveque",5,Caracteristiques.EVEQUE);
	}
	//méthodes
	public void utiliserPouvoir(){}
	
	public void percevoirRessourcesSpecifiques() {
		for(int i=0;i<this.getJoueur().getCite().length;i++) {
			
		}
	}
}
