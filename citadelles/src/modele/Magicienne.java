package modele;

import java.util.ArrayList;

import controleur.Interaction;

public class Magicienne extends Personnage{
	//constructeur
	public Magicienne() {
		this.nom="Magicienne";
		this.rang=3;
		this.caracteristiques=Caracteristiques.MAGICIENNE;
	}
	//m�thode
	public void utiliserPouvoir() {
		int i=1;
		System.out.println("Voulez-vous �changer vos cartes avec celles d'un autre joueur ?(o/n)");
		boolean ask = Interaction.lireOuiOuNon();
		if(ask) {
			System.out.println("Avec quel personnage voulez-vous �changer vos cartes ?");
			while(i<=this.getPlateau().getNombrePersonnages()) {
				System.out.println(i+" "+this.getPlateau().getPersonnage(i-1).getNom()+" "+this.getPlateau().getJoueur(i-1).nbQuartiersDansMain());
				i++;
			}
			boolean continu=true;
			int lecture=0;
			do {
				try {
					lecture=Interaction.lireUnEntier();
					if(this.getPlateau().getJoueur(lecture-1).getPersonnage().getRang()==3) {
						throw new Exception();
					} else {
						continu=false;
					}
				} catch(Exception e) {
					System.out.println("Vous ne pouvez pas vous choisir");
					System.out.print("Votre choix : ");
				}
			} while(continu);
			ArrayList<Quartier> copieTableauMagicienne=new ArrayList<Quartier>(this.getJoueur().getMain());
			ArrayList<Quartier> copieTableauJoueurChoix=new ArrayList<Quartier>(this.getPlateau().getJoueur(lecture-1).getMain());
			this.getJoueur().getMain().clear();
			this.getPlateau().getJoueur(lecture-1).getMain().clear();
			this.getJoueur().getMain().addAll(copieTableauJoueurChoix);
			this.getPlateau().getJoueur(lecture-1).getMain().addAll(copieTableauMagicienne);
		} else if(this.getJoueur().nbQuartiersDansMain()!=0) {
			System.out.println("Vous ne poss�dez pas de cartes � �changer.");
		} else {
			System.out.println("Combien de cartes voulez-vous prendre dans la pioche ?");
			int nb=Interaction.lireUnEntier(0, this.getJoueur().getMain().size()+1);
			if(nb==0) {
				System.out.println("Vous n'�changez aucune cartes.");
			}
			boolean continu=true;
			int lecture=0;
			do {
				try {
					lecture=Interaction.lireUnEntier();
					if(lecture<3) {
						continu=false;
					}else {
						throw new Exception();
					}
				} catch (Exception e){
					System.out.println("L'entier doit �tre inf�rieur � 3");
					System.out.println("Veuillez rentrer un chiffre : ");
				}
			}while(continu);
			System.out.println("Voici");		}
	}
}
