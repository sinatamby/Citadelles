package modele;

public class Quartier {
	
	//Attributs
	private String nom, type,caracteristiques;
	private int coutConstruction;
	public static final String[] TYPE_QUARTIERS= {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	
	
	//Constructeurs
	
	public Quartier(String nom, String type, int cout){
		this.setNom(nom);
		this.setType(type);
		this.setCout(cout);
		this.setCaracteristiques("");
	}
	public Quartier(String nom, String type, int cout, String caracteristiques){
		this.setNom(nom);
		this.setType(type);
		this.setCout(cout);
		this.setCaracteristiques(caracteristiques);
	}
	public Quartier(){
		this.setNom("");
		this.setType("");
		this.setCout(0);
		this.setCaracteristiques("");		
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
		 /*boolean correct = false ;
		 for(int i=0; i< TYPE_QUARTIERS.length; i++) {  //Boucle qui nous permets de parcourir chaque élément du tableau TYPES_QUARTIERS
			 if( type.equals(TYPE_QUARTIERS[i]) ) {
				  correct=true;
			 }
		 }if(correct) {
			 this.type = type;
		 }else {
			 this.type="";
		 }
	 
	 */
		 int  compteur = 0;
		 while (compteur <TYPE_QUARTIERS.length && this.type!=type) {
			 if (type.equals(TYPE_QUARTIERS[compteur])){
				 this.type=type;
			 }else {
				 this.type="";
			 }
			 compteur++;
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
