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
			} catch (InputMismatchException e) { //si ce n'est pas un entier, reboucle
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
		boolean continu = true;
		do {
			try {
				i=sc.nextInt();
				if(i>= borneMin && i < borneMax) {
					continu = false;
				}else { 
					throw (new Exception("le chiffre n'est pas compris entre [0;10["));
				}
			} catch(InputMismatchException e) {
				System.out.println("Veuillez rentrer un chiffre: ");
				sc.next();
			} catch (Exception e) {
				System.out.println("Veuillez indiquer un nombre entre 0 et 10 exclus");
			}
		} while (continu);
		return i;

	}

	// lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
	public static boolean lireOuiOuNon() {
		boolean retour = true;
		Scanner saisieUser = new Scanner(System.in);
		do {
			try {
				String maSaisie= saisieUser.next();
				if(maSaisie.equals("oui") || maSaisie.equals("o") || maSaisie.equals("non")|| maSaisie.equals("n")) {
					retour = false;
				}else {
					throw(new Exception(""));
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Saisissez une chaîne de caractère");
			}catch(Exception e) {
				System.out.println("Veuillez saisir \"oui\" ou \"o\" ou \"non\" ou \"n\" ");
			}

		}while(retour);
		return retour;

	}

	// renvoie une chaîne de caractère lue au clavier:
	/*
	 * premier test de ma fonction
	 * public static String lireUneChaine() {
		String retour = "";
		boolean continu = true;
		Scanner saisieUser= new Scanner(System.in);
		do {
			try {
				retour = saisieUser.next();	
				if(retour.matches("^[a-zA-Z]*$")) {// à vérifier
					continu= false;					
				}else {
					throw(new Exception(""));
				}
			}catch(InputMismatchException e) {
				System.out.println("Saisissez une chaîne de caractère");
			}catch(Exception e) {
				System.out.println("Veuillez à bien indiquer une chaine de caractère");
			}
		}
		while(continu);
	 */
	public static String lireUneChaine() {
		String retour = "";
		boolean continu = true;
		Scanner saisieUser= new Scanner(System.in);
		do {
			try {
				retour = saisieUser.next();
				if(retour.equals("")) {
					throw(new InputMismatchException(""));
				}else {
					
				
				continu= false;	
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Saisissez une chaîne de caractère");
			}
		}
		while(continu);





		return retour;
	}



}