package com.assoc.events;

public class Event {

	private String titre;
	private String description;
	private String date;
	private String image;

	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Event(String titre, String description, String date, String image) {
		super();
		this.titre = titre;
		this.description = description;
		this.date = date;
		this.image = image;
	}


	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
