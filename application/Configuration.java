package application;
import modele.*;
public class Configuration {
	public static Pioche nouvellePioche() {
		Pioche pioche=new Pioche();
		for(int i=0;i<5;i++) {
			pioche.ajouter(new Quartier("Taverne","COMMERCANT",1,""));
			pioche.ajouter(new Quartier("Manoir","NOBLE",3,""));
		}
		for(int i=0;i<4;i++) {
			pioche.ajouter(new Quartier("Marché","COMMERCANT",2,""));
			pioche.ajouter(new Quartier("Château","NOBLE",4,""));
		}
		for(int i=0;i<3;i++) {
			pioche.ajouter(new Quartier("Échoppe","COMMERCANT",2,""));
			pioche.ajouter(new Quartier("Comptoir","COMMERCANT",3,""));
			pioche.ajouter(new Quartier("Port","COMMERCANT",4,""));
			pioche.ajouter(new Quartier("Palais","NOBLE",5,""));
			pioche.ajouter(new Quartier("Tour de Guet","MILITAIRE",1,""));
			pioche.ajouter(new Quartier("Prison","MILITAIRE",2,""));
			pioche.ajouter(new Quartier("Caserne","MILITAIRE",3,""));
			pioche.ajouter(new Quartier("Temple","RELIGIEUX",1,""));
			pioche.ajouter(new Quartier("Église","RELIGIEUX",2,""));
			pioche.ajouter(new Quartier("Monastère","RELIGIEUX",3,""));
		}
		for(int i=0;i<2;i++) {
			pioche.ajouter(new Quartier("Hôtel de Ville","COMMERCANT",5,""));
			pioche.ajouter(new Quartier("Forteresse","MILITAIRE",5,""));
			pioche.ajouter(new Quartier("Cathédrale","RELIGIEUX",5,""));
		}
		pioche.melanger();
		return pioche;
	}
	public static PlateauDeJeu configurationDeBase(Pioche p) {
		PlateauDeJeu plateau=new PlateauDeJeu();
		//ajout des joueurs
		for(int i=0;i<4;i++) {
			plateau.ajouterJoueur(new Joueur("j"+i));
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
		p.ajouter(new Quartier("Bibliothèque","MERVEILLE",6,""));
		p.ajouter(new Quartier("Carrière","MERVEILLE",5,""));
		p.ajouter(new Quartier("Cour des Miracles","MERVEILLE",2,""));
		p.ajouter(new Quartier("Donjon","MERVEILLE",3,""));
		p.ajouter(new Quartier("Dracoport","MERVEILLE",6,""));
		p.ajouter(new Quartier("École de magie","MERVEILLE",6,""));
		p.ajouter(new Quartier("Fontaine aux Souhaits","MERVEILLE",5,""));
		p.ajouter(new Quartier("Forge","MERVEILLE",5,""));
		p.ajouter(new Quartier("Laboratoire","MERVEILLE",5,""));
		p.ajouter(new Quartier("Manufacture","MERVEILLE",5,""));
		p.ajouter(new Quartier("Salle des Cartes","MERVEILLE",5,""));
		p.ajouter(new Quartier("Statue Équestre","MERVEILLE",3,""));
		p.ajouter(new Quartier("Trésor Impérial","MERVEILLE",5,""));
		p.ajouter(new Quartier("Tripot","MERVEILLE",6,""));
		p.melanger();
		return plateau;
	}
}

