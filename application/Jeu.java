package application;

import java.util.Random;
import controleur.Interaction;
import modele.*;

public class Jeu {
	//attributs
	private PlateauDeJeu plateauDeJeu;
	private int numeroConfiguration;
	private Random generateur=new Random();
	//constructeur
	public Jeu(){
		plateauDeJeu=new PlateauDeJeu();
		numeroConfiguration=0;
		generateur=new Random();
	}
	//m�thodes
	public void jouer() {
		System.out.println("Bienvenue dans Citadelles !\n\n");
		System.out.println("1- Jouer\n2- R�gles\n3- Quitter");
		int choix=0;
		choix=Interaction.lireUnEntier(1,4);
		if(choix==1) {
			System.out.println("D�marrage de la partie");
			jouerPartie();
		} else if(choix==2) {
			System.out.println("Voici les r�gles du jeu :");
			afficherLesRegles();
		} else if(choix==3) {
			System.out.println("Vous quittez Citadelles, au revoir !");
			partieFinie();
		}
	}
	private void afficherLesRegles() {
		System.out.println("regles.pdf");
	}
	private void jouerPartie() {
		initialisation();
		int i=0;
		if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()<=7) {
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();
		}
		partieFinie();
	}
	private void initialisation() {
		Configuration.configurationDeBase(Configuration.nouvellePioche());
		for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			this.plateauDeJeu.getJoueur(i).ajouterPieces(2);
			for (int j=0;j<4;j++) {
				this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(this.plateauDeJeu.getPioche().piocher());
			}
			this.plateauDeJeu.getPioche().melanger();
		}
		int joueur=generateur.nextInt(3);
		this.plateauDeJeu.getJoueur(joueur).getPossedeCouronne();
	}
	private void gestionCouronne() {
		
	}
	private void reinitialisationPersonnages() {
		
	}
	private boolean partieFinie() {
		return true;
	}
	private void tourDeJeu() {
		choixPersonnages();
		for(int i=0;i<this.plateauDeJeu.getNombrePersonnages();i++) {
			
		}
	}
	private void choixPersonnages() {
		System.out.println("Choix des personnages :");
		int faceCachee=generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
		this.plateauDeJeu.retirerPersonnage(this.plateauDeJeu.getPersonnage(faceCachee));
		System.out.println("Un personnage a �t� �cart� face cach�e.");
		int faceVisible1=generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
		this.plateauDeJeu.retirerPersonnage(this.plateauDeJeu.getPersonnage(faceVisible1));
		System.out.println("Le personnage \""+this.plateauDeJeu.getPersonnage(faceVisible1).getNom()+"\" est �cart� face visible.");
		int faceVisible2=generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
		this.plateauDeJeu.retirerPersonnage(this.plateauDeJeu.getPersonnage(faceVisible2));
		System.out.println("Le personnage \""+this.plateauDeJeu.getPersonnage(faceVisible2).getNom()+"\" est �cart� face visible.");
	}
	private void percevoirRessources() {
		
	}
	private void calculDesPoints() {
		
	}
}
