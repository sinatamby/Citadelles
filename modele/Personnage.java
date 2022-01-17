package modele;
public abstract class Personnage{
	//attributs
	protected int rang;
	protected String nom, caracteristiques;
	protected boolean assassine, vole;
	protected Joueur joueur;
	private PlateauDeJeu plateau;
	//constructeur
	public Personnage(String nom, int rang, String caracteristiques) {
		this.nom=nom;
		this.rang=rang;
		this.caracteristiques=caracteristiques;
		this.assassine=false;
		this.vole=false;
		this.joueur=null;
	}
	public Personnage() {
		this.nom="";
		this.rang=0;
		this.caracteristiques="";
		this.assassine=false;
		this.vole=false;
		this.joueur=null;
	}
	//accesseurs
	public String getNom() {
		return this.nom;
	}
	public int getRang() {
		return this.rang;
	}
	public String getCaracteristiques() {
		return this.caracteristiques;
	}
	public boolean getAssassine() {
		return this.assassine;
	}
	public boolean getVole() {
		return this.vole;
	}
	public Joueur getJoueur() {
		return this.joueur;
	}
	public void setJoueur(Joueur j) {
		this.joueur=j;
		this.joueur.monPersonnage=this;
	}
	public void setVole() {
		this.vole=true;
	}
	public void setAssassine() {
		this.assassine=true;
	}
	public PlateauDeJeu getPlateau() {
		return this.plateau;
	}
	public void setPlateau(PlateauDeJeu nouveau) {
		this.plateau=nouveau;
	}
	//méthodes
	public void ajouterPieces() {
		if(this.joueur!=null && this.assassine==false) {
			this.joueur.ajouterPieces(2);
		}
	}
	
	public void ajouterQuartier(Quartier nouveau) {
		if(this.joueur!=null && this.assassine==false) {
			this.joueur.ajouterQuartierDansMain(nouveau);
		}
	}
	
	public void construire(Quartier nouveau) {
		if(this.joueur!=null && this.assassine==false) {
			this.joueur.ajouterQuartierDansCite(nouveau);
		}
	}
	
	public void percevoirRessourcesSpecifiques() {
		if(this.joueur!=null && this.assassine==false) {
			System.out.println("aucune ressources spécifiques");
		}
	}
	
	public abstract void utiliserPouvoir();
		//méthode abstraite nécessite classe abstraite
	public abstract void utiliserPouvoirAvatar();
	
	public void reinitialiser() {
		this.assassine=false;
		this.vole=false;
		if(this.joueur!=null) {
			this.joueur.monPersonnage=null;
			this.joueur=null;
		}
	}
}
