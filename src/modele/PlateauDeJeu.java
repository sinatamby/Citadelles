package modele;

public class PlateauDeJeu {
	//attributs
	private Personnage[] listePersonnages;
	private Joueur[] listeJoueurs;
	private Pioche pioche;
	private int nombrePersonnages, nombreJoueurs;
	//constructeur
	public PlateauDeJeu(){
		this.listePersonnages=new Personnage[9];
		this.listeJoueurs=new Joueur[9];
		this.pioche=new Pioche();
		this.nombrePersonnages=0;
		this.nombreJoueurs=0;
	}
	//accesseurs
	public int getNombrePersonnages(){
		return this.nombrePersonnages;
	}
	public int getNombreJoueurs() {
		return this.nombreJoueurs;
	}
	public Pioche getPioche() {
		return this.pioche;
	}
	public Personnage getPersonnage(int i) {
		Personnage retour;
		if(i>=0 && i<=getNombrePersonnages()) {
			retour=this.listePersonnages[i];
		} else {
			retour=null;
		}
		return retour;
	}
	public Joueur getJoueur(int i) {
		Joueur retour;
		if(i>=0 && i<=getNombreJoueurs()) {
			retour=this.listeJoueurs[i];
		} else {
			retour=null;
		}
		return retour;
	}
	//méthodes
	public void ajouterPersonnage(Personnage personnage) {
		if(this.nombrePersonnages<=8 && personnage!=null) {
			this.listePersonnages[this.nombrePersonnages]=personnage;
			personnage.setPlateau(this);
			this.nombrePersonnages++;
		}
	}
	public void ajouterJoueur(Joueur joueur) {
		if(this.nombreJoueurs<=8 && joueur!=null) {
			this.listeJoueurs[this.nombreJoueurs]=joueur;
			this.nombreJoueurs++;
		}
	}
}
