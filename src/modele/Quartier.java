package modele;

public class Quartier {
	//attributs
	private String nom, type, caracteristiques;
	private int coutConstruction;
	public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	//constructeurs
	public Quartier() {
		//initialisation
		this.setNom("");
		this.setType("");
		this.setCout(0);
		this.setCaracteristiques("");
	}
	public Quartier(String nom,String type,int cout,String caracteristiques) {
		this.setNom(nom);
		this.setType(type);
		this.setCout(cout);
		this.setCaracteristiques(caracteristiques);
	}
	public Quartier(String nom,String type,int cout) {
		this.setNom(nom);
		this.setType(type);
		this.setCout(cout);
		this.setCaracteristiques("");
	}
	//accesseurs NOM
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	//accesseurs TYPE
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		int i=0;
		while(i<TYPE_QUARTIERS.length && this.type!=type) {
			if(type==TYPE_QUARTIERS[i]) {
				this.type=type;
			} else {
				this.type="";
			}
			i++;
		}
	}
	//accesseurs COUT
	public int getCout() {
		return this.coutConstruction;
	}
	public void setCout(int cout) {
		if(cout>=1 && cout<=6) {
			this.coutConstruction=cout;
		} else {
			this.coutConstruction=0;
		}
	}
	//accesseurs CARACTERISTIQUES
	public String getCaracteristiques() {
		return this.caracteristiques;
	}
	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques=caracteristiques;
	}
}
