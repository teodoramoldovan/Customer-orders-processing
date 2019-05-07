package model;

public class Comanda {
	
	private int id;
	private int id_client;
	public Comanda() {
		this.id=0;
		this.id_client=0;
	}
	public Comanda(int id, int id_client) {
		this.id = id;
		this.id_client = id_client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
}
