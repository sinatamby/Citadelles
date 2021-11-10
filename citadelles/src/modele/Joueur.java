package modele;
import java.util.ArrayList;

public class Joueur {

	
	//Attributs
	private String nom;
	private int tresor, nbQuartiers;
	private boolean possedeCouronne;
	private Quartier[]cite;
	//private static final Quartier[] cite = {};
	private ArrayList<Quartier> main= new ArrayList<Quartier>();
	
	//Constructeur
	public Joueur(String nom){
		this.setNom(nom); //probablement à changer en mode : this.nom=nom;
		this.tresor=0;
		this.nbQuartiers=0;
		this.possedeCouronne=false;
		this.cite= new Quartier[8];
		this.main = new ArrayList<Quartier>();

	}
	
	//Accesseurs de nom
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	//Accesseurs de Pieces
	public int nbPieces() {
		return this.tresor;
	}
	
	//Accesseurs de nbQuartiersDansCite
	public int nbQuartiersDansCite() {
		return this.nbQuartiers;
	}
	//Accesseurs cité
	public Quartier[] getCite() {
		return this.cite;
	}
	
	//Accesseurs Main
	public ArrayList<Quartier> getMain(){
		return this.main;
	}
	
	//Accesseur de nbQuartierDansMain
	public int nbQuartiersDansMain() {
		//for(int i=0; i<this.main.size();i++)
			// peut etre simplement return la taile de main si ce dernier est une list évolutif à revoir avant de se compliquer la tache
		return this.main.size() ; // après vérification il semble que cela soit fonctionnel.
	}
	
	
	
	
}
