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
	//méthodes
	public void jouer() {
		System.out.println("Bienvenue dans Citadelles !\n\n");
		int choix=0;
		boolean continu=true;
		do {
			System.out.println("1- Jouer\n2- Règles\n3- Quitter");
			choix=Interaction.lireUnEntier(1,4);
			if(choix==1) {
				System.out.println("Démarrage de la partie");
				jouerPartie();
				System.out.println("");
			} else if(choix==2) {
				System.out.println("Voici les régles du jeu :");
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
		while(!partieFinie()) {
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
		for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			if (this.plateauDeJeu.getJoueur(i).getPersonnage().getCaracteristiques()==Caracteristiques.ROI) {
				this.plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
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
			System.out.println("C'est au tour du personnage de rang "+this.plateauDeJeu.getPersonnage(i).getRang()+" : "+this.plateauDeJeu.getPersonnage(i).getNom());
			if (this.plateauDeJeu.getPersonnage(i).getVole() && this.plateauDeJeu.getPersonnage(i).getJoueur()!=null) {
				System.out.println("le personnage "+this.plateauDeJeu.getPersonnage(i).getNom()+" a été volé");
				for(int j=0;j<this.plateauDeJeu.getNombrePersonnages();j++) {
					if(this.plateauDeJeu.getPersonnage(j).getCaracteristiques()==Caracteristiques.VOLEUR) {
						this.plateauDeJeu.getPersonnage(j).getJoueur().ajouterPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces());
					} 
				}
			} else if(this.plateauDeJeu.getPersonnage(i).getVole() && this.plateauDeJeu.getPersonnage(i).getJoueur()==null){
				System.out.println("Le personnage n'est pas attribué, le voleur ne reçoit pas de pièces");
			}
			if (this.plateauDeJeu.getPersonnage(i).getVole() && this.plateauDeJeu.getPersonnage(i).getAssassine()) {
				System.out.println("Personnage assassiné, le tour passe");
			} else {
				if(this.plateauDeJeu.getPersonnage(i).getJoueur()!=null && this.plateauDeJeu.getPersonnage(i).getJoueur().getNom()=="Player1") {
					percevoirRessources(i);
					this.plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
					System.out.println("Voulez vous utiliser votre pouvoir ?");
					if(Interaction.lireOuiOuNon()) {
						this.plateauDeJeu.getPersonnage(i).utiliserPouvoir();
					}
					System.out.println("Voulez vous construire ?");
					if(Interaction.lireOuiOuNon()) {
						System.out.println("Quel quartier voulez vous construire ?");
						for(int j=0;j<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansMain();j++) {
							System.out.println((j+1)+" "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j).getNom()+" (coût:"+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j).getCout()+")");
						}
						boolean continu=true;
						do {
							try {
								int choix=Interaction.lireUnEntier(1, this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size()+1);
								if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()>this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
									throw new Exception();
								} else {
									this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix));
									System.out.println("Le joueur "+this.plateauDeJeu.getPersonnage(i).getJoueur().getNom()+" construit le quartier "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getNom());
									if (this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("Manufacture") && this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getType()==Quartier.TYPE_QUARTIERS[4]) {
										this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()-1);
									} else {
										this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout());
									}
									this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().remove(choix);
									continu=false;
								}
							} catch (Exception e) {
								System.out.println("Coût trop élevé");
								System.out.println("Votre choix :");
							}
						} while (continu);
						if(this.plateauDeJeu.getPersonnage(i).getCaracteristiques()==Caracteristiques.ARCHITECTE) {
							for(int k=1;k<3;k++) {
								System.out.println("Voulez vous construire un "+(k)+"eme quartier ?");
								if (Interaction.lireOuiOuNon()) {
									System.out.println("Quel quartier voulez vous construire ?");
									for(int j=0;j<this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite();j++) {
										System.out.println((i+1)+" "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j).getNom()+" (coût:"+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(j).getCout()+")");
									}
									continu=true;
									do {
										try {
											int choix=Interaction.lireUnEntier(1, this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size()+1);
											if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()>this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
												throw new Exception();
											} else {
												this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix));
												System.out.println("Le joueur "+this.plateauDeJeu.getPersonnage(i).getJoueur().getNom()+" construit le quartier "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getNom());
												if (this.plateauDeJeu.getPersonnage(i).getJoueur().quartierPresentDansCite("Manufacture") && this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getType()==Quartier.TYPE_QUARTIERS[4]) {
													this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()-1);
												} else {
													this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout());
												}
												this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().remove(choix);
												continu=false;
											}
										} catch (Exception e) {
											System.out.println("Coût trop élevé");
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
				} else if(this.plateauDeJeu.getPersonnage(i).getJoueur()!=null) {
					percevoirRessources(i);
					this.plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
					int rand=generateur.nextInt(2);
					if(rand==1) {
						System.out.println("Le personnage utilise son pouvoir");
						this.plateauDeJeu.getPersonnage(i).utiliserPouvoirAvatar();
					} else {
						System.out.println("Le personnage n'utilise pas son pouvoir");
					}
					rand=generateur.nextInt(1);
					if(rand==1) {
						boolean continu=true;
						do {
							try {
								int choix=generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size()+1);
								if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()>this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
									throw new Exception();
								} else {
									this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix));
									System.out.println("Le joueur "+this.plateauDeJeu.getPersonnage(i).getJoueur().getNom()+" construit le quartier "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getNom()+" dans sa cité.");
									this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout());
									this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().remove(choix);
									continu=false;
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
											int choix=generateur.nextInt(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().size()+1);
											if(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout()>this.plateauDeJeu.getPersonnage(i).getJoueur().nbPieces()) {
												throw new Exception();
											} else {
												this.plateauDeJeu.getPersonnage(i).construire(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix));
												System.out.println("Le joueur "+this.plateauDeJeu.getPersonnage(i).getJoueur().getNom()+" construit le quartier "+this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getNom());
												this.plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().get(choix).getCout());
												this.plateauDeJeu.getPersonnage(i).getJoueur().getMain().remove(choix);
												continu=false;
											}
										} catch (Exception e) {}
									} while (continu);
								} else {
									System.out.println("L'Architecte ne construit pas de quartier");
								}
							}
						}
						if (this.plateauDeJeu.getPersonnage(i).getJoueur().nbQuartiersDansCite()>=7 && !this.citeCompletePremier) {
							this.citeCompletePremier=true;
							this.nomJoueurCiteCompletePremier=this.plateauDeJeu.getPersonnage(i).getJoueur().getNom();
						}
					} else {
						System.out.println("Le personnage ne construit pas de quartier");
					}
				}
			}
		}
	}
	private void choixPersonnages() {
		//élimination de certain personnages (partie qui change si nombre de joueurs change --> NON IMPLÉMENTÉ)
		ArrayList<Personnage> copiePersos=new ArrayList<Personnage>();
		for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
			copiePersos.add(this.plateauDeJeu.getPersonnage(i));
		}
		System.out.println("Choix des personnages :");
		int remove=generateur.nextInt(copiePersos.size());
		System.out.println("Un personnage a été écarté face cachée.");
		int faceCachee=copiePersos.get(remove).getRang();
		copiePersos.remove(remove);
		remove=generateur.nextInt(copiePersos.size());
		System.out.println("Le personnage \""+copiePersos.get(remove).getNom()+"\" est écarté face visible.");
		int faceVisible1=copiePersos.get(remove).getRang();
		copiePersos.remove(remove);
		remove=generateur.nextInt(copiePersos.size());
		System.out.println("Le personnage \""+copiePersos.get(remove).getNom()+"\" est écarté face visible.");
		int faceVisible2=copiePersos.get(remove).getRang();
		copiePersos.remove(remove);
		//joueur qui possède la couronne
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
						int choix=Interaction.lireUnEntier(1, 9);
						if(choix==faceCachee||choix==faceVisible1||choix==faceVisible2) {
							System.out.println("Ce personnage n'est pas disponible.");
							System.out.println("Votre choix :");
						} else {
							for (int j=0;j<copiePersos.size();j++) {
								if(choix==copiePersos.get(j).getRang() && continu) {
									for (int j2 = 0; j2 < this.plateauDeJeu.getNombrePersonnages(); j2++) {
										if (copiePersos.get(j).getRang()==this.plateauDeJeu.getPersonnage(j2).getRang()) {
											this.plateauDeJeu.getPersonnage(j2).setJoueur(this.plateauDeJeu.getJoueur(i));
										}
									}
									copiePersos.remove(j);
									continu=false;
								}
							}
						}
					} while (continu);
				} else {
					boolean continu=true;
					do {
						int choix=generateur.nextInt(7)+1;
						if(choix==faceCachee||choix==faceVisible1||choix==faceVisible2) {
						} else {
							for (int j=0;j<copiePersos.size();j++) {
								if(choix==copiePersos.get(j).getRang() && continu) {
									for (int j2 = 0; j2 < this.plateauDeJeu.getNombrePersonnages(); j2++) {
										if (copiePersos.get(j).getRang()==this.plateauDeJeu.getPersonnage(j2).getRang()) {
											this.plateauDeJeu.getPersonnage(j2).setJoueur(this.plateauDeJeu.getJoueur(i));
										}
									}
									copiePersos.remove(j);
									System.out.println("Le joueur "+this.plateauDeJeu.getJoueur(i).getNom()+" a choisi son personnage");
									continu=false;
								}
							}
						}
					} while (continu);
				}
				
			}
		}
		System.out.println("phase couronne complete");
		//les joueurs restants
		for (int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			if(i==0 && !this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
				for (int j=0;j<copiePersos.size();j++) {
					System.out.println(copiePersos.get(j).getRang()+" "+copiePersos.get(j).getNom());
				}
				System.out.println("Choisissez votre personnage");
				boolean continu=true;
				do {
					int choix=Interaction.lireUnEntier(1, 9);
					if(choix==faceCachee||choix==faceVisible1||choix==faceVisible2) {
						System.out.println("Ce personnage n'est pas disponible.");
						System.out.println("Votre choix :");
					} else {
						for (int j=0;j<copiePersos.size();j++) {
							if(choix==copiePersos.get(j).getRang() && continu) {
								for (int j2 = 0; j2 < this.plateauDeJeu.getNombrePersonnages(); j2++) {
									if (copiePersos.get(j).getRang()==this.plateauDeJeu.getPersonnage(j2).getRang()) {
										this.plateauDeJeu.getPersonnage(j2).setJoueur(this.plateauDeJeu.getJoueur(i));
									}
								}
								copiePersos.remove(j);
								continu=false;
							}
						}
					}
				} while (continu);
			} else if(!this.plateauDeJeu.getJoueur(i).getPossedeCouronne()){
				boolean continu=true;
				do {
					int choix=generateur.nextInt(7)+1;
					if(choix==faceCachee||choix==faceVisible1||choix==faceVisible2) {
					} else {
						for (int j=0;j<copiePersos.size();j++) {
							if(choix==copiePersos.get(j).getRang() && continu) {
								for (int j2 = 0; j2 < this.plateauDeJeu.getNombrePersonnages(); j2++) {
									if (copiePersos.get(j).getRang()==this.plateauDeJeu.getPersonnage(j2).getRang()) {
										this.plateauDeJeu.getPersonnage(j2).setJoueur(this.plateauDeJeu.getJoueur(i));
									}
								}
								copiePersos.remove(j);
								System.out.println("Le joueur "+this.plateauDeJeu.getJoueur(i).getNom()+" a choisi son perso");
								continu=false;
							} 
						}
					}
				} while (continu);
			}
		}
		System.out.println("phase autre joueurs complete");
	}
	private void percevoirRessources(int perso) {
		if(this.plateauDeJeu.getPersonnage(perso).getJoueur()!=null && this.plateauDeJeu.getPersonnage(perso).getJoueur().getNom()=="Player1") {
			System.out.println("Ques souhaitez vous faire ?");
			System.out.println("1 Prendre deux pièces d'or (vous en avez "+this.plateauDeJeu.getPersonnage(perso).getJoueur().nbPieces()+" dans votre trésor)");
			System.out.println("2 piocher deux quartier et en garder un (vous en avez "+this.plateauDeJeu.getPersonnage(perso).getJoueur().nbQuartiersDansMain()+" dans votre main et "+this.plateauDeJeu.getPersonnage(perso).getJoueur().nbQuartiersDansCite()+" dans votre cité)");
			int choix=Interaction.lireUnEntier(1, 3);
			if(choix==1) {
				this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterPieces(2);
				System.out.println("Vous avez maintenant "+this.plateauDeJeu.getPersonnage(perso).getJoueur().nbPieces()+" pièces dans votre trésor.");
			} else if(choix==2) {
				if (this.plateauDeJeu.getPersonnage(perso).getJoueur().quartierPresentDansCite("Bibliothèque")) {
					System.out.println("Grace à la carte Bibliothèque, vous gardez les deux quartiers suivants :");
					Quartier quartier1=this.plateauDeJeu.getPioche().piocher();
					System.out.println("1 "+quartier1.getNom()+" ,type: "+quartier1.getType()+" ,coût:"+quartier1.getCout());
					this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterQuartierDansMain(quartier1);
					Quartier quartier2=this.plateauDeJeu.getPioche().piocher();
					System.out.println("2 "+quartier2.getNom()+" ,type: "+quartier2.getType()+" ,coût:"+quartier2.getCout());
					this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterQuartierDansMain(quartier2);
				} else {
					Quartier quartier1=this.plateauDeJeu.getPioche().piocher();
					System.out.println("1 "+quartier1.getNom()+" ,type: "+quartier1.getType()+" ,coût:"+quartier1.getCout());
					Quartier quartier2=this.plateauDeJeu.getPioche().piocher();
					System.out.println("2 "+quartier2.getNom()+" ,type: "+quartier2.getType()+" ,coût:"+quartier2.getCout());
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
			}
		} else if(this.plateauDeJeu.getPersonnage(perso).getJoueur()!=null) {
			int choix=generateur.nextInt(2);
			if(choix==0) {
				System.out.println("Le joueur "+this.plateauDeJeu.getPersonnage(perso).getJoueur().getNom()+" prends 2 pièces.");
				this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterPieces(2);
			} else if(choix==1) {
				System.out.println("Le joueur "+this.plateauDeJeu.getPersonnage(perso).getJoueur().getNom()+" prends 1 quartier.");
				Quartier quartier1=this.plateauDeJeu.getPioche().piocher();
				Quartier quartier2=this.plateauDeJeu.getPioche().piocher();
				if (this.plateauDeJeu.getPersonnage(perso).getJoueur().quartierPresentDansCite("Bibliothèque")) {
					this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterQuartierDansMain(quartier1);
					this.plateauDeJeu.getPioche().ajouter(quartier2);
					this.plateauDeJeu.getPersonnage(perso).getJoueur().ajouterQuartierDansMain(quartier2);
					this.plateauDeJeu.getPioche().ajouter(quartier1);
				} else {
					int garder=generateur.nextInt(2);
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
	}
	private void calculDesPoints() {
		System.out.println("Scores finaux :");
		for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
			int totalPoints=0;
			boolean rel=false,mil=false,nob=false,com=false,mer=false;
			Quartier[] citeJoueur=this.plateauDeJeu.getJoueur(i).getCite();
			for(int j=0;j<this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite();j++) {
				totalPoints=totalPoints+citeJoueur[j].getCout();
				if (this.plateauDeJeu.getJoueur(i).quartierPresentDansCite("Cour des Miracles")) {
					System.out.println("Vous possédez la Cour des Miracles, quel type voulez vous lui attribuer ?");
					if (this.plateauDeJeu.getJoueur(i).getNom()=="Player1") {
						for (int j2 = 0; j2 < 5; j2++) {
							System.out.println((j2+1)+" "+Quartier.TYPE_QUARTIERS[j2]);
						}
						int choix=Interaction.lireUnEntier(1, 6);
						if 		 (choix==1) {
							rel=true;
						} else if(choix==2) {
							mil=true;
						} else if(choix==3) {
							nob=true;
						} else if(choix==4) {
							com=true;
						} else if(choix==5) {
							mer=true;
						}
					} else {
						int choix=generateur.nextInt(6);
						if 		 (choix==1) {
							rel=true;
						} else if(choix==2) {
							mil=true;
						} else if(choix==3) {
							nob=true;
						} else if(choix==4) {
							com=true;
						} else if(choix==5) {
							mer=true;
						}
					}
				} else {
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
				if (this.plateauDeJeu.getJoueur(i).quartierPresentDansCite("Dracoport")) {
					totalPoints=totalPoints+2;
				}
				if (this.plateauDeJeu.getJoueur(i).quartierPresentDansCite("Fontaine aux Souhaits") && citeJoueur[j].getType()==Quartier.TYPE_QUARTIERS[4]) {
					totalPoints++;
				}
			}
			if (rel&&mil&&nob&&com&&mer) {
				totalPoints=totalPoints+3;
			}
			if (this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()>=7 && this.nomJoueurCiteCompletePremier==this.plateauDeJeu.getJoueur(i).getNom()) {
				totalPoints=totalPoints+4;
			} else if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()>=7) {
				totalPoints=totalPoints+2;
			}
			System.out.println("Score du joueur "+this.plateauDeJeu.getJoueur(i).getNom()+" : "+totalPoints+" pts");
		}
	}
}
