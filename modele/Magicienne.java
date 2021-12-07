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
	//méthode
	public void utiliserPouvoir() {
		System.out.println("Voulez-vous échanger vos cartes avec celles d'un autre joueur ?(o/n)");
		boolean ask = Interaction.lireOuiOuNon();
		if(ask) {
			System.out.println("Avec quel personnage voulez-vous échanger vos cartes ?");
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
			System.out.println("Vous ne possédez pas de cartes à échanger.");
		} else {
			System.out.println("Combien de cartes voulez-vous prendre dans la pioche ?");
			int nb=Interaction.lireUnEntier(0, this.getJoueur().getMain().size()+1);
			if(nb==0) {
				System.out.println("Vous n'échangez aucune cartes.");
			} else if(nb==this.getJoueur().getMain().size()) {
				ArrayList<Quartier> copieTableauMagicienne=new ArrayList<Quartier>(this.getJoueur().getMain());
				for(int i=0;i<this.getJoueur().getMain().size();i++) {
					this.getPlateau().getPioche().ajouter(copieTableauMagicienne.get(i)) ;
					this.getPlateau().getPioche().piocher();
				}
			} else {
				ArrayList<Quartier> copieTableauMagicienne=new ArrayList<Quartier>(this.getJoueur().getMain());
				System.out.println("Voici les cartes de votre main : ");
				for(int i=1;i<=this.getJoueur().getMain().size();i++) {
					System.out.println(i+" "+this.getJoueur().getMain().get(i-1).getNom()+" - type : "+this.getJoueur().getMain().get(i-1).getType()+" - pièces : "+this.getJoueur().getMain().get(i-1).getCout());
				}
				
				System.out.println("Quel est le numéro de la carte que vous voulez retirer ? ");
				int num=Interaction.lireUnEntier(1, i+1);
				
			}
					
		}
	}
}
