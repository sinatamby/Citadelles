package modele;

import java.util.ArrayList;
import java.util.Random;

import controleur.Interaction;

public class Magicienne extends Personnage{
	//constructeur
	public Magicienne() {
		super("Magicienne",3,Caracteristiques.MAGICIENNE);
	}
	//m�thode
	public void utiliserPouvoir() {
		System.out.println("Voulez-vous �changer vos cartes avec celles d'un autre joueur ?(o/n)");
		boolean ask = Interaction.lireOuiOuNon();
		if(ask) {
			System.out.println("Avec quel personnage voulez-vous �changer vos cartes ?");
			int i=1;
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
		} else if(this.getJoueur().nbQuartiersDansMain()==0) {
			System.out.println("Vous ne poss�dez pas de cartes � �changer.");
		} else {
			System.out.println("Combien de cartes voulez-vous prendre dans la pioche ?");
			int nb=Interaction.lireUnEntier(0, this.getJoueur().getMain().size()+1);
			if(nb==0) {
				System.out.println("Vous n'�changez aucune cartes.");
			} else if(nb==this.getJoueur().getMain().size()) {
				ArrayList<Quartier> copieTableauMagicienne=new ArrayList<Quartier>(this.getJoueur().getMain());
				this.getJoueur().getMain().clear();
				for(int i=0;i<nb;i++) {
					this.getPlateau().getPioche().ajouter(copieTableauMagicienne.get(i));
					copieTableauMagicienne.remove(i);
					copieTableauMagicienne.add(i, this.getPlateau().getPioche().piocher()) ;
				}
				this.getJoueur().getMain().addAll(copieTableauMagicienne);
			} else {
				ArrayList<Quartier> copieTableauMagicienne=new ArrayList<Quartier>(this.getJoueur().getMain());
				System.out.println("Voici les cartes de votre main : ");
				for(int i=1;i<=this.getJoueur().getMain().size();i++) {
					System.out.println(i+" "+this.getJoueur().getMain().get(i-1).getNom()+" - type : "+this.getJoueur().getMain().get(i-1).getType()+" - pi�ces : "+this.getJoueur().getMain().get(i-1).getCout());
				}
				if(nb>1) {
					System.out.println("Quel est le num�ro de la carte que vous voulez retirer ? ");
				} else {
					System.out.println("Quel sont les num�ros des cartes que vous voulez retirer ? ");
				}
				for(int i=1;i<=nb;i++) {
					int num=Interaction.lireUnEntier(1, nb+2);
					this.getPlateau().getPioche().ajouter(copieTableauMagicienne.get(num-1));
					copieTableauMagicienne.remove(num-1);
					this.getPlateau().getPioche().piocher();
				}
				this.getJoueur().getMain().clear();
				this.getJoueur().getMain().addAll(copieTableauMagicienne);
			}	
		}
	}
	public void utiliserPouvoirAvatar() {
		Random rand=new Random();
		int choixAlea=rand.nextInt(this.getPlateau().getNombrePersonnages());
		while (this.getPlateau().getPersonnage(choixAlea).getRang()==3) {
			choixAlea=rand.nextInt(this.getPlateau().getNombrePersonnages());
		}
		
	}
}
