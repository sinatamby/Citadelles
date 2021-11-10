package modele;

public class Quartier {
	
	//Attributs
	private String nom, type,caracteristiques;
	private int coutConstruction;
	public static final String[] TYPE_QUARTIERS= {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};
	
	
	//Constructeurs
	Quartier(String nom, String type, int cout, String caracteristiques){
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
	 public void setType(int type) {
		 if(TYPE_QUARTIERS.length<= type){
			 this.type=TYPE_QUARTIERS[type];
			 
		 }else {
			 this.type="";
		 }
	 }
	//Accesseurs CoutConstruction
	public int getCout() {
		return this.coutConstruction;
	}
	public void setCout(int cout) {
		if(this.coutConstruction<=6 && this.coutConstruction>=1) {
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
