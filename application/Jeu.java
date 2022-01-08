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
	//méthodes
	public void jouer() {
		System.out.println("Bienvenue dans Citadelles !\n\n");
		System.out.println("1- Jouer\n2- Règles\n3- Quitter");
		int choix=0;
		choix=Interaction.lireUnEntier(1,4);
		if(choix==1) {
			System.out.println("Démarrage de la partie");
			jouerPartie();
		} else if(choix==2) {
			System.out.println("Voici les régles du jeu :");
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
			
			if (this.plateauDeJeu.getPersonnage(i).getAssassine()) {
				System.out.println("Vous etes assassiné, vous passez votre tour");
			} else if (this.plateauDeJeu.getPersonnage(i).getVole()) {
				for(int j=0;j<this.plateauDeJeu.getNombrePersonnages();j++) {
					if(this.plateauDeJeu.getPersonnage(j).getCaracteristiques()==Caracteristiques.VOLEUR) {
						this.plateauDeJeu.getPersonnage(j).getJoueur().ajouterPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces());
					}
				}
				this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces());
			} else {
				
			}
		}
		
		
		
	}
	private void choixPersonnages() {
		//élimination de certain personnages (partie qui change si nombre de joueurs change --> NON IMPLÉMENTÉ)
		System.out.println("Choix des personnages :");
		int faceCachee=generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
		System.out.println("Un personnage a été écarté face cachée.");
		this.plateauDeJeu.retirerPersonnage(this.plateauDeJeu.getPersonnage(faceCachee));
		int faceVisible1=generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
		System.out.println("Le personnage \""+this.plateauDeJeu.getPersonnage(faceVisible1).getNom()+"\" est écarté face visible.");
		this.plateauDeJeu.retirerPersonnage(this.plateauDeJeu.getPersonnage(faceVisible1));
		int faceVisible2=generateur.nextInt(this.plateauDeJeu.getNombrePersonnages());
		System.out.println("Le personnage \""+this.plateauDeJeu.getPersonnage(faceVisible2).getNom()+"\" est écarté face visible.");
		this.plateauDeJeu.retirerPersonnage(this.plateauDeJeu.getPersonnage(faceVisible2));
		//joueur qui possède la couronne
		for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			if (this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				System.out.println("Vous avez la couronne !");
				for (int j=0;j<this.plateauDeJeu.getNombrePersonnages();j++) {
					System.out.println(this.plateauDeJeu.getPersonnage(j).getRang()+" "+this.plateauDeJeu.getPersonnage(j).getNom());
				}
				System.out.println("Choisissez votre personnage");
				boolean continu=true;
				do {
					try {
						int choix=Interaction.lireUnEntier(1, 9);
						for (int j=0;j<this.plateauDeJeu.getNombrePersonnages();j++) {
							if(choix==this.plateauDeJeu.getPersonnage(j).getRang()) {
								this.plateauDeJeu.getPersonnage(j).setJoueur(this.plateauDeJeu.getJoueur(i));
								this.plateauDeJeu.retirerPersonnage(this.plateauDeJeu.getPersonnage(j));
								continu=false;
							} else {
								throw new Exception();
							}
						}
					} catch (Exception e) {
						System.out.println("Ce personnage n'est pas disponible.");
						System.out.println("Votre choix :");
					}
				} while (continu);
			}
		}
		//les joueurs restants
		for (int i=0;i<this.plateauDeJeu.getNombreJoueurs()-1;i++) {
			boolean continu=true;
			do {
				try {
					int choix=generateur.nextInt(7)+1;
					for (int j=0;j<this.plateauDeJeu.getNombrePersonnages();j++) {
						if(choix==this.plateauDeJeu.getPersonnage(j).getRang()) {
							this.plateauDeJeu.getPersonnage(j).setJoueur(this.plateauDeJeu.getJoueur(i));
							this.plateauDeJeu.retirerPersonnage(this.plateauDeJeu.getPersonnage(j));
							continu=false;
						} else {
							throw new Exception();
						}
					}
				} catch (Exception e) {}
			} while (continu);
		}
	}
	private void percevoirRessources() {
		
	}
	private void calculDesPoints() {
		
	}
}
