package persistantdata;

import mediatek2021.Document;

public class Doc implements Document {
	private int id;
	private String titre;
	private String auteur;
	private int type;
	private int disponible;

	public Doc(int id, String titre, String auteur, int type, int disponible) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.type = type;
		this.disponible = disponible;
	}
	
	@Override
	public Object[] data() {
		// TODO Auto-generated method stub
		return new Object[]{
			id,
			titre,
			auteur,
			type,
			disponible
		};
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
