package modele;

public class Quartier {
	
	//Attributs
	private String nom, type,caracteristiques;
	private int coutConstruction;
	public static final String[] TYPE_QUARTIERS= {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	
	
	//Constructeurs
	
	public Quartier(String nom, String type, int cout){
		this.nom=nom;
		this.type=type;
		this.coutConstruction=cout;
		this.caracteristiques="";
	}
	public Quartier(String nom, String type, int cout, String caracteristiques){
		this.nom=nom;
		this.type=type;
		this.coutConstruction=cout;
		this.caracteristiques=caracteristiques;
	}
	public Quartier(){
		this.nom="";
		this.type="";
		this.coutConstruction=0;
		this.caracteristiques="";
		
	}
	
	//Accesseurs Nom
	 public String getNom(){
		 return this.nom ;	 
	 }
	 public void setNom(String nom){
		 this.nom = nom;
		 }

	 //Accesseurs Type
	 public String getType() {
		 return this.type;
	 }
	 
	 public void setType(String type) {
		 for(int i=0; i< TYPE_QUARTIERS.length; i++) {  //Boucle qui nous permets de parcourir chaque élément du tableau TYPES_QUARTIERS
			 if(TYPE_QUARTIERS[i]==type) {
				 this.type=type;
			 }else {
				 this.type="";
			 }
		 }
	 }

	//Accesseurs CoutConstruction
	public int getCout() {
		return this.coutConstruction;
	}
	
	public void setCout(int cout) {
		if(cout<=6 && cout>=1) {
			this.coutConstruction=cout;
		}else {
			this.coutConstruction=0;
		}
	}
	
	//Accesseurs Caractéristiques
	
	public String getCaracteristiques() {
		return this.caracteristiques;
	}
	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques=caracteristiques;
	}
	
}
