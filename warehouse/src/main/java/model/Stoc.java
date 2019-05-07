package model;

public class Stoc {
	private int id_produs;
	private int cantitate;
	public Stoc() {
		this.id_produs=0;
		this.cantitate=0;
	}
	public Stoc(int id_produs, int cantitate) {
		this.id_produs = id_produs;
		this.cantitate = cantitate;
	}

	public int getId_produs() {
		return id_produs;
	}

	public void setId_produs(int id_produs) {
		this.id_produs = id_produs;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
}
