package application;

import java.util.ArrayList;
import java.util.Random;
import controleur.Interaction;
import modele.*;

public class Jeu {
	//attributs
	private PlateauDeJeu plateauDeJeu;
	private int numeroConfiguration;
	private Random generateur=new Random();
	private boolean citeCompletePremier;
	private String nomJoueurCiteCompletePremier;
	//constructeur
	public Jeu(){
		this.plateauDeJeu=new PlateauDeJeu();
		this.numeroConfiguration=0;
		this.generateur=new Random();
		this.citeCompletePremier=false;
	}
	//m�thodes
	public void jouer() {
		System.out.println("Bienvenue dans Citadelles !\n\n");
		int choix=0;
		boolean continu=true;
		do {
			System.out.println("1- Jouer\n2- R�gles\n3- Quitter");
			choix=Interaction.lireUnEntier(1,4);
			if(choix==1) {
				System.out.println("D�marrage de la partie");
				jouerPartie();
			} else if(choix==2) {
				System.out.println("Voici les r�gles du jeu :");
				afficherLesRegles();
			} else if(choix==3) {
				System.out.println("Vous quittez Citadelles, au revoir !");
				continu=false;
			}
		} while (continu);
		
	}
	private void afficherLesRegles() {
		System.out.println("regles.pdf");
	}
	private void jouerPartie() {
		initialisation();
		partieFinie();
		if (!partieFinie()) {
			tourDeJeu();
			gestionCouronne();
			reinitialisationPersonnages();
			partieFinie();
		}
		calculDesPoints();
	}
	private void initialisation() {
		this.plateauDeJeu=Configuration.configurationDeBase(Configuration.nouvellePioche());
		this.plateauDeJeu.setPioche(Configuration.nouvellePioche());
		this.plateauDeJeu.getPioche().melanger();
		for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			this.plateauDeJeu.getJoueur(i).ajouterPieces(2);
			for (int j=0;j<4;j++) {
				this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(this.plateauDeJeu.getPioche().piocher());
			}
			this.plateauDeJeu.getPioche().melanger();
		}
		int joueur=generateur.nextInt(3);
		this.plateauDeJeu.getJoueur(joueur).setPossedeCouronne(true);
	}
	private void gestionCouronne() {
		for(int i=0;i<this.plateauDeJeu.getNombrePersonnages();i++) {
			if (this.plateauDeJeu.getPersonnage(i).getCaracteristiques()==Caracteristiques.ROI) {
				this.plateauDeJeu.getPersonnage(i).getJoueur().setPossedeCouronne(true);
			} else {
				for(int j=0;j<this.plateauDeJeu.getNombreJoueurs();j++) {
					if (this.plateauDeJeu.getJoueur(j).getPossedeCouronne()) {
						this.plateauDeJeu.getJoueur(j).setPossedeCouronne(true);
					}
				}
			}
		}
		
	}
	private void reinitialisationPersonnages() {
		for(int i=0;i<this.plateauDeJeu.getNombrePersonnages();i++) {
			this.plateauDeJeu.getPersonnage(i).reinitialiser();
		}
	}
	private boolean partieFinie() {
		boolean retour=false;
		for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()>=7) {
				retour=true;
			}
		}
		return retour;
	}
	private void tourDeJeu() {
		choixPersonnages();
		for(int i=0;i<this.plateauDeJeu.getNombrePersonnages();i++) {
			System.out.println("C'est au tour du personnage de rang "+this.plateauDeJeu.getPersonnage(i).getRang());
			if (this.plateauDeJeu.getPersonnage(i).getVole()) {
				System.out.println("le personnage "+this.plateauDeJeu.getPersonnage(i).getNom()+" a �t� vol�");
				for(int j=0;j<this.plateauDeJeu.getNombrePersonnages();j++) {
					if(this.plateauDeJeu.getPersonnage(j).getCaracteristiques()==Caracteristiques.VOLEUR) {
						this.plateauDeJeu.getPersonnage(j).getJoueur().ajouterPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces());
					}
				}
				this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces());
			}
			if (this.plateauDeJeu.getPersonnage(i).getAssassine()) {
				System.out.println("Personnage assassin�, le tour passe");
			} else {
				if(this.plateauDeJeu.getPersonnage(i).getJoueur().getNom()=="Player1") {
					percevoirRessources(i);
					this.plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
					System.out.println("Voulez utiliser votre pouvoir ?");
					if(Interaction.lireOuiOuNon()) {
						this.plateauDeJeu.getPersonnage(i).utiliserPouvoir();
					}
					System.out.println("Voulez vous construire ?");
					if(Interaction.lireOuiOuNon()) {
						System.out.println("Quel quartier voulez vous construire ?");
						for(int j=0;j<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite();j++) {
							System.out.println((j+1)+" "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j).getNom()+" (co�t:"+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j).getCout()+")");
						}
						boolean continu=true;
						do {
							try {
								int choix=Interaction.lireUnEntier(1, this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size()+1);
								if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()>this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
									throw new Exception();
								} else {
									this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix));
									this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout());
									this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().remove(choix);
								}
							} catch (Exception e) {
								System.out.println("Co�t trop �lev�");
								System.out.println("Votre choix :");
							}
						} while (continu);
						if(this.plateauDeJeu.getPersonnage(i).getCaracteristiques()==Caracteristiques.ARCHITECTE) {
							for(int k=1;k<3;k++) {
								System.out.println("Voulez vous construire un "+(k)+"eme quartier ?");
								if (Interaction.lireOuiOuNon()) {
									System.out.println("Quel quartier voulez vous construire ?");
									for(int j=0;j<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite();j++) {
										System.out.println((i+1)+" "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j).getNom()+" (co�t:"+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j).getCout()+")");
									}
									continu=true;
									do {
										try {
											int choix=Interaction.lireUnEntier(1, this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size()+1);
											if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()>this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
												throw new Exception();
											} else {
												this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix));
												this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout());
												this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().remove(choix);
											}
										} catch (Exception e) {
											System.out.println("Co�t trop �lev�");
											System.out.println("Votre choix :");
										}
									} while (continu);
								}
							}
						}
						if (this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite()>=7 && !this.citeCompletePremier) {
							this.citeCompletePremier=true;
							this.nomJoueurCiteCompletePremier=this.plateauDeJeu.getPersonnage(i).getJoueur().getNom();
						}
					}
				} else {
					percevoirRessources(i);
					this.plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
					int rand=generateur.nextInt(1);
					if(rand==1) {
						this.plateauDeJeu.getPersonnage(i).utiliserPouvoir();
					}
					rand=generateur.nextInt(1);
					if(rand==1) {
						boolean continu=true;
						do {
							try {
								int choix=generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size())+1;
								if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()>this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
									throw new Exception();
								} else {
									this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix));
									this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout());
									this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().remove(choix);
								}
							} catch (Exception e) {}
						} while (continu);
						if(this.plateauDeJeu.getPersonnage(i).getCaracteristiques()==Caracteristiques.ARCHITECTE) {
							for(int k=1;k<3;k++) {
								rand=generateur.nextInt(1);
								if (rand==1) {
									continu=true;
									do {
										try {
											int choix=generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size())+1;
											if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()>this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
												throw new Exception();
											} else {
												this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix));
												this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout());
												this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().remove(choix);
											}
										} catch (Exception e) {}
									} while (continu);
								}
							}
						}
						if (this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite()>=7 && !this.citeCompletePremier) {
							this.citeCompletePremier=true;
							this.nomJoueurCiteCompletePremier=this.plateauDeJeu.getPersonnage(i).getJoueur().getNom();
						}
					}
				}
			}
		}
		
		
		
	}
	private void choixPersonnages() {
		//�limination de certain personnages (partie qui change si nombre de joueurs change --> NON IMPL�MENT�)
		ArrayList<Personnage> copiePersos=new ArrayList<Personnage>();
		for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
			copiePersos.add(this.plateauDeJeu.getPersonnage(i));
		}
		System.out.println("Choix des personnages :");
		int faceCachee=generateur.nextInt(copiePersos.size());
		System.out.println("Un personnage a �t� �cart� face cach�e.");
		copiePersos.remove(faceCachee);
		int faceVisible1=generateur.nextInt(copiePersos.size());
		System.out.println("Le personnage \""+copiePersos.get(faceVisible1).getNom()+"\" est �cart� face visible.");
		copiePersos.remove(faceVisible1);
		int faceVisible2=generateur.nextInt(copiePersos.size());
		System.out.println("Le personnage \""+copiePersos.get(faceVisible2).getNom()+"\" est �cart� face visible.");
		copiePersos.remove(faceVisible2);
		//joueur qui poss�de la couronne
		for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			if (this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				System.out.println(this.plateauDeJeu.getJoueur(i).getNom()+" a la couronne !");
				if(this.plateauDeJeu.getJoueur(0).getPossedeCouronne()) {
					for (int j=0;j<copiePersos.size();j++) {
						System.out.println(copiePersos.get(j).getRang()+" "+copiePersos.get(j).getNom());
					}
					System.out.println("Choisissez votre personnage");
					boolean continu=true;
					do {
						try {
							int choix=Interaction.lireUnEntier(1, 9);
							for (int j=0;j<copiePersos.size();j++) {
								if(choix==copiePersos.get(j).getRang()) {
									copiePersos.get(j).setJoueur(this.plateauDeJeu.getJoueur(i));
									copiePersos.remove(j);
									continu=false;
								}
								
							}
							if(continu) {
								throw new Exception();
							}
						} catch (Exception e) {
							System.out.println("Ce personnage n'est pas disponible.");
							System.out.println("Votre choix :");
						}
					} while (continu);
				} else {
					boolean continu=true;
					do {
						try {
							int choix=generateur.nextInt(7)+1;
							for (int j=0;j<copiePersos.size();j++) {
								if(choix==copiePersos.get(j).getRang()) {
									copiePersos.get(j).setJoueur(this.plateauDeJeu.getJoueur(i));
									copiePersos.remove(j);
									System.out.println("Le joueur "+this.plateauDeJeu.getJoueur(i).getNom()+" a choisi son perso");
									continu=false;
								}
							}
							if(continu) {
								throw new Exception();
							}
						} catch (Exception e) {}
					} while (continu);
				}
				
			}
		}
		//les joueurs restants
		for (int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			if(i==0 && !this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				for (int j=0;j<copiePersos.size();j++) {
					System.out.println(copiePersos.get(j).getRang()+" "+copiePersos.get(j).getNom());
				}
				System.out.println("Choisissez votre personnage");
				boolean continu=true;
				do {
					try {
						int choix=Interaction.lireUnEntier(1, 9);
						for (int j=0;j<copiePersos.size();j++) {
							if(choix==copiePersos.get(j).getRang()) {
								System.out.println(choix+" --> "+copiePersos.get(j).getRang());
								copiePersos.get(j).setJoueur(this.plateauDeJeu.getJoueur(i));
								copiePersos.remove(j);
								continu=false;
							}
						}
						if(continu) {
							throw new Exception();
						}
					} catch (Exception e) {
						System.out.println("Ce personnage n'est pas disponible.");
						System.out.println("Votre choix :");
					}
				} while (continu);
			} else if(!this.plateauDeJeu.getJoueur(i).getPossedeCouronne()){
				boolean continu=true;
				do {
					try {
						int choix=generateur.nextInt(7)+1;
						for (int j=0;j<copiePersos.size();j++) {
							if(choix==copiePersos.get(j).getRang()) {
								copiePersos.get(j).setJoueur(this.plateauDeJeu.getJoueur(i));
								copiePersos.remove(j);
								System.out.println("Le joueur "+this.plateauDeJeu.getJoueur(i).getNom()+" a choisi son perso");
								continu=false;
							} 
						}
						if(continu) {
							throw new Exception();
						}
					} catch (Exception e) {}
				} while (continu);
			}
		}
	}
	private void percevoirRessources(int perso) {
		if(this.plateauDeJeu.getPersonnage(perso).getJoueur().getNom()=="Player1") {
			System.out.println("Ques souhaitez vous faire ?");
			System.out.println("1 Prendre deux pi�ces d'or");
			System.out.println("2 piocher deux quartier et en garder un");
			int choix=Interaction.lireUnEntier(1, 3);
			if(choix==1) {
				this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterPieces(2);
			} else if(choix==2) {
				Quartier quartier1=this.plateauDeJeu.getPioche().piocher();
				System.out.println("1 "+quartier1.getNom()+" ,type: "+quartier1.getType()+" ,co�t:"+quartier1.getCout());
				Quartier quartier2=this.plateauDeJeu.getPioche().piocher();
				System.out.println("2 "+quartier2.getNom()+" ,type: "+quartier2.getType()+" ,co�t:"+quartier2.getCout());
				System.out.println("Lequel gardez vous ?");
				int garder=Interaction.lireUnEntier(1, 3);
				if(garder==1){
					this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterQuartierDansMain(quartier1);
					this.plateauDeJeu.getPioche().ajouter(quartier2);
				} else if(garder==2) {
					this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterQuartierDansMain(quartier2);
					this.plateauDeJeu.getPioche().ajouter(quartier1);
				}
			}
		} else {
			int choix=generateur.nextInt(1);
			if(choix==0) {
				this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterPieces(2);
			} else if(choix==1) {
				Quartier quartier1=this.plateauDeJeu.getPioche().piocher();
				Quartier quartier2=this.plateauDeJeu.getPioche().piocher();
				int garder=generateur.nextInt(1);
				if(garder==0){
					this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterQuartierDansMain(quartier1);
					this.plateauDeJeu.getPioche().ajouter(quartier2);
				} else if(garder==1) {
					this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterQuartierDansMain(quartier2);
					this.plateauDeJeu.getPioche().ajouter(quartier1);
				}
			}
		}
	}
	private void calculDesPoints() {
		System.out.println("Scores finaux :");
		for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			int totalCout=0;
			boolean rel=false,mil=false,nob=false,com=false,mer=false;
			Quartier[] citeJoueur=this.plateauDeJeu.getJoueur(i).getCite();
			for(int j=0;j<this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite();j++) {
				totalCout=totalCout+citeJoueur[j].getCout();
				if 		 (citeJoueur[j].getType()==Quartier.TYPE_QUARTIERS[0]) {
					rel=true;
				} else if(citeJoueur[j].getType()==Quartier.TYPE_QUARTIERS[1]) {
					mil=true;
				} else if(citeJoueur[j].getType()==Quartier.TYPE_QUARTIERS[2]) {
					nob=true;
				} else if(citeJoueur[j].getType()==Quartier.TYPE_QUARTIERS[3]) {
					com=true;
				} else if(citeJoueur[j].getType()==Quartier.TYPE_QUARTIERS[4]) {
					mer=true;
				}
			}
			if (rel&&mil&&nob&&com&&mer) {
				totalCout=totalCout+3;
			}
			if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()>=7 && this.nomJoueurCiteCompletePremier==this.plateauDeJeu.getJoueur(i).getNom()) {
				totalCout=totalCout+4;
			} else if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()>=7) {
				totalCout=totalCout+2;
			}
			System.out.println("Score du joueur "+this.plateauDeJeu.getJoueur(i).getNom()+" : "+totalCout+" pts");
		}
	}
}
