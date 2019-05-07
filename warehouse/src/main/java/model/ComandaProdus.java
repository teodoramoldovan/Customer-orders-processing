package model;

public class ComandaProdus {

	private int id_comanda;
	private int id_produs;
	private int cantitate;
	
	public ComandaProdus() {
		this.id_comanda=0;
		this.id_produs=0;
		this.cantitate=0;
	}
	public ComandaProdus(int id_comanda, int id_produs, int cantitate) {
		this.id_comanda = id_comanda;
		this.id_produs = id_produs;
		this.cantitate = cantitate;
	}


	public int getId_comanda() {
		return id_comanda;
	}


	public void setId_comanda(int id_comanda) {
		this.id_comanda = id_comanda;
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
