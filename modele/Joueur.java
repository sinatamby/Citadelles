package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {
	//attributs
	private int tresor, nbQuartiers;
	private String nom;
	private boolean possedeCouronne;
	private Quartier[] cite;
	private ArrayList<Quartier> main;
	protected Personnage monPersonnage;
	//constructeur
	public Joueur(String nom) {
		this.nom=nom;
		this.tresor=0;
		this.nbQuartiers=0;
		this.possedeCouronne=false;
		this.cite=new Quartier[8];
		this.main=new ArrayList<Quartier>();
		this.monPersonnage=null;
	}
	//accesseurs
	public String getNom() {
		return this.nom;
	}
	public int nbPieces() {
		return this.tresor;
	}
	public int nbQuartiersDansCite() {
		return this.nbQuartiers;
	}
	public Quartier[] getCite() {
		return this.cite;
	}
		//getQuartier pour la question 4 du 2.5 "Roi"
	public Quartier getQuartier(int quartier) {
		return this.cite[quartier];
	}
		//permet de cibler un Quartier dans la cité sans connaitre son nom.
	public ArrayList<Quartier> getMain() {
		return this.main;
	}
	public int nbQuartiersDansMain() {
		return this.main.size();
	}
	public boolean getPossedeCouronne() {
		return this.possedeCouronne;
	}
	public void setPossedeCouronne(boolean b) {
		this.possedeCouronne=b;
	}
	public Personnage getPersonnage() {
		return this.monPersonnage;
	}
	//méthodes
	public void ajouterPieces(int pieces){
		if(pieces>=0) {
			this.tresor=this.tresor+pieces;
		}
	}
	public void retirerPieces(int pieces){
		if(pieces<=nbPieces() && pieces>0) {
			this.tresor=this.tresor-pieces;
		}
	}
	public void ajouterQuartierDansCite(Quartier quartier) {
		if(this.nbQuartiers<8) {
			this.cite[this.nbQuartiers]=quartier;
			this.nbQuartiers++;
		}
	}
	public boolean quartierPresentDansCite(String nom) {
		int i=0;
		boolean retour=false;
		while(this.cite[i]!=null) {
			if(nom==this.cite[i].getNom()) {
				retour=true;
			}
			i++;
		}
		return retour;
	}
	public Quartier retirerQuartierDansCite(String nom) {
		boolean trouve=false;
		Quartier efface=new Quartier();
		if(quartierPresentDansCite(nom)) {
			for(int i=0;i<this.nbQuartiers+1;i++) {
				if(nom==this.cite[i].getNom()) {
					efface=this.cite[i];
					trouve=true;
					this.cite[i]=null;
					this.nbQuartiers--;
				}else if(trouve) {
					this.cite[i-1]=this.cite[i];
				}
			}
			return efface;
		} else {
			return null;
		}
	}
	public void ajouterQuartierDansMain(Quartier quartier) {
		this.main.add(quartier);
	}
	public Quartier retirerQuartierDansMain() {
		Quartier retour=null;
		if(nbQuartiersDansMain()>0) {
			Random generateur = new Random();
			int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
			retour=this.main.get(numeroHasard);
			this.main.remove(numeroHasard);
		}
		return retour;
	}
	public void reinitialiser() {
		this.tresor=0;
		this.nbQuartiers=0;
		this.cite=new Quartier[8];
		this.main.clear();
	}
}
