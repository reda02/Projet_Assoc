package com.assoc.adherentespace;

public class Adherent {
	
	private int id ;
	private String email;
	private String password ;
	
	
	private String nom ;
	private String prenom ; 
	private String situation ;
    private String   dateNaissonce ;
    private String image;
   
	
    public Adherent(int id, String email, String password, String nom, String prenom, String situation,
    		String dateNaissonce, String image) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.situation = situation;
		this.dateNaissonce = dateNaissonce;
		this.image = image;
	}


	public Adherent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getSituation() {
		return situation;
	}


	public void setSituation(String situation) {
		this.situation = situation;
	}


	public String getDateNaissonce() {
		return dateNaissonce;
	}


	public void setDateNaissonce(String dateNaissonce) {
		this.dateNaissonce = dateNaissonce;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
    
    
    
    
    

}
