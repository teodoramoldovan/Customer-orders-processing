package businessLayer;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProdusDAO;
import dao.StocDAO;
import exception.WrongIdException;
import model.Produs;
import model.Stoc;



public class ProdusBLL {
	private ArrayList<Produs> produse;
	private ProdusDAO produsDAO;
	@SuppressWarnings("unused")
	private ArrayList<Stoc> stoc;
	private StocDAO stocDAO;
	
	public ProdusBLL() {
		this.produsDAO=new ProdusDAO();
		this.produse=(ArrayList<Produs>)produsDAO.findAll();
		this.stocDAO=new StocDAO();
		this.stoc=(ArrayList<Stoc>)stocDAO.findAll();
	}

	public ArrayList<Produs> getProduse() {
		return produse;
	}
	public void addProdus(String nume,float pret,int cantitate) {
		int id=0;
		int maxid=0;
		if(produse.size()>0) {
			for(Produs p:produse) {
				if(maxid<p.getId())maxid=p.getId();
			}
			id=maxid+1;
		}		
		Produs produs=new Produs(id,nume,pret);
		Stoc s=new Stoc(id,cantitate);	
		try {
			produsDAO.insert(produs);
			stocDAO.insert(s);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProdus(String id,String nume,String pret) throws WrongIdException {
		boolean ok=false;
		String n = null;
		float p=0;
		if(id.isEmpty()||!id.matches("^\\d+$")) throw new WrongIdException("Id field must contain a valid number");
		else {
			for(Produs produs:produse) {
				if(produs.getId()==Integer.parseInt(id)) {
					if(nume.equals(produs.getNume())==false&&!nume.isEmpty()) {
						n=nume;
					}
					else n=produs.getNume();					
					if(!pret.isEmpty()) {
						if(Float.parseFloat(pret)!=produs.getPret()) {
							p=Float.parseFloat(pret);
						}
						
					}else p=produs.getPret();					
					ok=true;
				}
			}
			if(ok==false)throw new WrongIdException("Id field must contain the id of an existing client");
			Produs produs=new Produs(Integer.parseInt(id),n,p);
			try {
				produsDAO.update(Integer.parseInt(id), produs);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		}
	}
	public void deleteProdus(String id) throws WrongIdException {
		boolean ok=false;
		Produs produs = null;
		if(id.isEmpty()||!id.matches("^\\d+$")) throw new WrongIdException("Id field must contain a valid number");
		else {
			for(Produs p:produse) {
				if(p.getId()==Integer.parseInt(id)) {
					produs=new Produs(p.getId(),p.getNume(),p.getPret());
					ok=true;
				}
			}
			if(ok==false)throw new WrongIdException("Id field must contain the id of an existing client");
			try {
				produsDAO.delete(produs);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}			
		}
	}
}
