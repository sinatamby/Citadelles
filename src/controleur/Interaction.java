package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
	private static Scanner sc = new Scanner(System.in);

	public static int lireUnEntier() {
		int i = 0;
		boolean continu = true;
		do {
			try {
				i = sc.nextInt();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer un chiffre : ");
				sc.next(); // passe l'entier pour éviter de boucler
			}
		} while(continu);
		return i;
	}

	// renvoie un entier lu au clavier compris dans l'intervalle
	//     [borneMin, borneMax[
	public static int lireUnEntier(int borneMin, int borneMax) {
		int i = 0;
		boolean continu=true;
		do {
			try {
				i = sc.nextInt();
				if(i>=borneMin && i<borneMax) {
					continu = false;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.print("Veuillez rentrer un chiffre entre [0;10[ : ");
			}
		} while(continu);
		return i;
	}

	// lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
	public static boolean lireOuiOuNon() {
		String reponse;
		boolean continu=true, retour=false;
		do {
			try {
				reponse=sc.nextLine();
				if(reponse.equals("oui")||reponse.equals("o")) {
					retour=true;
					continu=false;
				} else if(reponse.equals("non")||reponse.equals("n")) {
					retour=false;
					continu=false;
				} else {
					throw new Exception();
				}
				
			} catch (Exception e) {
				System.out.print("N'accepte que \"oui\", \"non\", \"o\" ou \"n\" : ");
			}
		}while(continu);
		return retour;
	}

	// renvoie une chaîne de caractère lue au clavier:
	public static String lireUneChaine() {
		String i="";
		boolean continu = true;
		do {
			try {
				i = sc.nextLine();
				continu = false;
			} catch (InputMismatchException e) {
				System.out.print("Veuillez rentrer une chaine de caractères : ");
			}
		} while(continu);
		return i;
	}


	
}
