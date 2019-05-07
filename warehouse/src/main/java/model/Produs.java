package model;

public class Produs {
	
	private int id;
	private String nume;
	private float pret;
	
	public Produs() {
		this.id=0;
		this.nume=null;
		this.pret=0;
	}
	public Produs(int id, String nume, float pret) {
		this.id = id;
		this.nume = nume;
		this.pret = pret;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNume() {
		return nume;
	}


	public void setNume(String nume) {
		this.nume = nume;
	}


	public float getPret() {
		return pret;
	}


	public void setPret(float pret) {
		this.pret = pret;
	}
}
