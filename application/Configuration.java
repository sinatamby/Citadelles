package application;
import modele.*;
public class Configuration {
	public static Pioche nouvellePioche() {
		Pioche pioche=new Pioche();
		for(int i=0;i<5;i++) {
			pioche.ajouter(new Quartier("Taverne",Quartier.TYPE_QUARTIERS[4],1));
			pioche.ajouter(new Quartier("Manoir",Quartier.TYPE_QUARTIERS[3],3));
		}
		for(int i=0;i<4;i++) {
			pioche.ajouter(new Quartier("Marché",Quartier.TYPE_QUARTIERS[4],2));
			pioche.ajouter(new Quartier("Château",Quartier.TYPE_QUARTIERS[3],4));
		}
		for(int i=0;i<3;i++) {
			pioche.ajouter(new Quartier("Échoppe",Quartier.TYPE_QUARTIERS[4],2));
			pioche.ajouter(new Quartier("Comptoir",Quartier.TYPE_QUARTIERS[4],3));
			pioche.ajouter(new Quartier("Port",Quartier.TYPE_QUARTIERS[4],4));
			pioche.ajouter(new Quartier("Palais",Quartier.TYPE_QUARTIERS[3],5));
			pioche.ajouter(new Quartier("Tour de Guet",Quartier.TYPE_QUARTIERS[2],1));
			pioche.ajouter(new Quartier("Prison",Quartier.TYPE_QUARTIERS[2],2));
			pioche.ajouter(new Quartier("Caserne",Quartier.TYPE_QUARTIERS[2],3));
			pioche.ajouter(new Quartier("Temple",Quartier.TYPE_QUARTIERS[1],1));
			pioche.ajouter(new Quartier("Église",Quartier.TYPE_QUARTIERS[1],2));
			pioche.ajouter(new Quartier("Monastère",Quartier.TYPE_QUARTIERS[1],3));
		}
		for(int i=0;i<2;i++) {
			pioche.ajouter(new Quartier("Hôtel de Ville",Quartier.TYPE_QUARTIERS[4],5));
			pioche.ajouter(new Quartier("Forteresse",Quartier.TYPE_QUARTIERS[2],5));
			pioche.ajouter(new Quartier("Cathédrale",Quartier.TYPE_QUARTIERS[1],5));
		}
		pioche.melanger();
		return pioche;
	}
	public static PlateauDeJeu configurationDeBase(Pioche p) {
		PlateauDeJeu plateau=new PlateauDeJeu();
		//ajout des joueurs
		plateau.ajouterJoueur(new Joueur("Player1"));
		for(int i=0;i<3;i++) {
			plateau.ajouterJoueur(new Joueur("Bot"+i));
		}
		//ajout des personnages
		plateau.ajouterPersonnage(new Assassin());
		plateau.ajouterPersonnage(new Voleur());
		plateau.ajouterPersonnage(new Magicienne());
		plateau.ajouterPersonnage(new Roi());
		plateau.ajouterPersonnage(new Eveque());
		plateau.ajouterPersonnage(new Marchande());
		plateau.ajouterPersonnage(new Architecte());
		plateau.ajouterPersonnage(new Condottiere());
		//ajout des merveillles
		p.ajouter(new Quartier("Bibliothèque",Quartier.TYPE_QUARTIERS[5],6,""));
		p.ajouter(new Quartier("Carrière",Quartier.TYPE_QUARTIERS[5],5,""));
		p.ajouter(new Quartier("Cour des Miracles",Quartier.TYPE_QUARTIERS[5],2,""));
		p.ajouter(new Quartier("Donjon",Quartier.TYPE_QUARTIERS[5],3,""));
		p.ajouter(new Quartier("Dracoport",Quartier.TYPE_QUARTIERS[5],6,""));
		p.ajouter(new Quartier("École de magie",Quartier.TYPE_QUARTIERS[5],6,""));
		p.ajouter(new Quartier("Fontaine aux Souhaits",Quartier.TYPE_QUARTIERS[5],5,""));
		p.ajouter(new Quartier("Forge",Quartier.TYPE_QUARTIERS[5],5,""));
		p.ajouter(new Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[5],5,""));
		p.ajouter(new Quartier("Manufacture",Quartier.TYPE_QUARTIERS[5],5,""));
		p.ajouter(new Quartier("Salle des Cartes",Quartier.TYPE_QUARTIERS[5],5,""));
		p.ajouter(new Quartier("Statue Équestre",Quartier.TYPE_QUARTIERS[5],3,""));
		p.ajouter(new Quartier("Trésor Impérial",Quartier.TYPE_QUARTIERS[5],5,""));
		p.ajouter(new Quartier("Tripot",Quartier.TYPE_QUARTIERS[5],6,""));
		p.melanger();
		return plateau;
	}
}

