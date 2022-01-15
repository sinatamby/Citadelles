package modele;

import java.util.Random;

import controleur.Interaction;

public class Condottiere extends Personnage{
	//constructeur
	public Condottiere() {
		super("Condottiere",8,Caracteristiques.CONDOTTIERE);
	}
	//m�thodes
	public void percevoirRessourcesSpecifiques() {
		for(int i=0;i<this.getJoueur().nbQuartiersDansCite();i++) {
			if(this.getJoueur().getQuartier(i).getType()=="MILITAIRE") {
				this.getJoueur().ajouterPieces(1);
			}
		}
	}
	
	public void utiliserPouvoir() {
		System.out.println("Voulez-vous utiliser votre pouvoir de destruction ?");
		boolean reponse=Interaction.lireOuiOuNon();
		if(reponse) {
			System.out.println("Voici la liste des joueurs et le contenu de leur cit� :");
			for(int i=1;i<=this.getPlateau().getNombreJoueurs();i++) {
				System.out.print(i+" "+this.getPlateau().getJoueur(i-1).getNom()+": ");
				for(int j=1;j<=this.getPlateau().getJoueur(i-1).nbQuartiersDansCite();j++) {
					System.out.print(j+" "+this.getPlateau().getJoueur(i-1).getQuartier(j-1).getNom()+"(co�t "+this.getPlateau().getJoueur(i-1).getQuartier(j-1).getCout()+") ");
				}
				System.out.println("");
			}
			System.out.println("Pour information, vous avez "+this.getJoueur().nbPieces()+" pi�ces d'or dans votre tr�sor.");
			System.out.println("Quel joueur choisissez vous ? (0 pour ne rien faire)");
			boolean continu=true;
			boolean possible=false;
			int joueur=0;
			do {
				try {
					joueur=Interaction.lireUnEntier(0, this.getPlateau().getNombreJoueurs()+2);
					if(joueur==0) {
						continu=false;
					}
					for(int i=0;i<this.getPlateau().getJoueur(joueur-1).nbQuartiersDansCite();i++) {
						if(this.getPlateau().getJoueur(joueur-1).getQuartier(i).getCout()-1<=this.getJoueur().nbPieces()) {
							possible=true;
						}
					}
					if(possible==false){
						System.out.println("Vous ne pouvez pas choisir ce joueur car ces quartiers valent trop cher.");
						throw new Exception();
					} else if(this.getPlateau().getJoueur(joueur-1).getPersonnage().getRang()==8) {
						System.out.println("Vous ne pouvez pas vous choisir.");
						throw new Exception();
					} else if(this.getPlateau().getJoueur(joueur-1).getPersonnage().getRang()==5) {
						System.out.println("L'Eveque est prot�g� de votre pouvoir.");
						throw new Exception();
					} else {
						continu=false;
					}
				} catch(Exception e) {
					System.out.print("Votre choix : ");
				}
			} while(continu);
			if(joueur==0) { 
				System.out.println("Vous ne faites rien.");
			} else {
				System.out.print(this.getPlateau().getJoueur(joueur-1).getNom()+": ");
				for(int j=1;j<=this.getPlateau().getJoueur(joueur-1).nbQuartiersDansCite();j++) {
					System.out.print(j+" "+this.getPlateau().getJoueur(joueur-1).getQuartier(j-1).getNom()+"(co�t "+this.getPlateau().getJoueur(joueur-1).getQuartier(j-1).getCout()+") ");
				}
				System.out.println("");
				System.out.println("Quel quartier choisissez-vous ?");
				continu=true;
				int quartier=0;
				do {
					try {
						quartier=Interaction.lireUnEntier(1, this.getPlateau().getJoueur(joueur-1).nbQuartiersDansCite()+1);
						if(quartier-1>this.getPlateau().getJoueur(joueur-1).nbQuartiersDansCite()) {
							System.out.println("Il n'y a pas plus de "+quartier+" quartiers.");
							throw new Exception();
						} else if(this.getPlateau().getJoueur(joueur-1).getQuartier(quartier-1).getCout()-1>this.getJoueur().nbPieces()) {
							System.out.println("Votre tr�sor n'est pas suffisant");
							throw new Exception();
						} else if(this.getPlateau().getJoueur(joueur-1).getQuartier(quartier-1).getNom()=="Donjon") {
							System.out.println("Ce quartier n'est pas affect�");
							throw new Exception();
						} else {
							continu=false;
						}
					} catch(Exception e) {
						System.out.print("Votre choix : ");
					}
				} while(continu);
				System.out.println("Le quartier \""+this.getPlateau().getJoueur(joueur-1).getQuartier(quartier-1).getNom()+"\" de "+this.getPlateau().getJoueur(joueur-1).getNom()+" a �t� supprim�");
				this.getPlateau().getJoueur(joueur-1).retirerQuartierDansCite(this.getPlateau().getJoueur(joueur-1).getQuartier(quartier-1).getNom());
			}
		} else {
		System.out.println("Vous n'utilisez pas votre pouvoir.");
		}
	}
	public void utiliserPouvoirAvatar() {
		Random rand=new Random();
		int reponse=rand.nextInt(2);
		if(reponse==1) {
			boolean continu=true;
			boolean possible=false;
			int joueur=0;
			do {
				try {
					joueur=rand.nextInt(this.getPlateau().getNombreJoueurs()+1);
					if(joueur==0) {
						continu=false;
					}
					for(int i=0;i<this.getPlateau().getJoueur(joueur-1).nbQuartiersDansCite();i++) {
						if(this.getPlateau().getJoueur(joueur-1).getQuartier(i).getCout()-1<=this.getJoueur().nbPieces()) {
							possible=true;
						}
					}
					if(possible==false){
						throw new Exception();
					} else if(this.getPlateau().getJoueur(joueur-1).getPersonnage().getRang()==8) {
						throw new Exception();
					} else if(this.getPlateau().getJoueur(joueur-1).getPersonnage().getRang()==5) {
						throw new Exception();
					} else {
						continu=false;
					}
				} catch(Exception e) {}
			} while(continu);
			if(joueur==0) {
				System.out.println("Le joueur ne fait rien.");
			} else {
				continu=true;
				int quartier=0;
				do {
					try {
						quartier=rand.nextInt(this.getPlateau().getJoueur(joueur-1).nbQuartiersDansCite());
						if(quartier>this.getPlateau().getJoueur(joueur-1).nbQuartiersDansCite()) {
							throw new Exception();
						} else if(this.getPlateau().getJoueur(joueur-1).getQuartier(quartier-1).getCout()-1>this.getJoueur().nbPieces()) {
							throw new Exception();
						} else if(this.getPlateau().getJoueur(joueur-1).getQuartier(quartier-1).getNom()=="Donjon") {
							throw new Exception();
						} else {
							continu=false;
						}
					} catch(Exception e) {}
				} while(continu);
				System.out.println("Le quartier \""+this.getPlateau().getJoueur(joueur-1).getQuartier(quartier-1).getNom()+"\" de "+this.getPlateau().getJoueur(joueur-1).getNom()+" a �t� supprim�");
				this.getPlateau().getJoueur(joueur-1).retirerQuartierDansCite(this.getPlateau().getJoueur(joueur-1).getQuartier(quartier-1).getNom());
			}
		} else if(reponse==0){
		System.out.println("Le joueur n'utilise pas son pouvoir.");
		}
	}
}
