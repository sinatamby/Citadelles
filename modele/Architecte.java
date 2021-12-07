package modele;

public class Architecte extends Personnage{
	//constructeur
	public Architecte() {
		super("Architecte",7,Caracteristiques.ARCHITECTE);
	}
	//méthodes
	public void utiliserPouvoir() {
		for(int i=0;i<2;i++) {
			this.getJoueur().getMain().add(i, this.getPlateau().getPioche().piocher()) ;
		}		
	}
}
