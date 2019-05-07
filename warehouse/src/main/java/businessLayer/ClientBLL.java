package businessLayer;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClientDAO;
import exception.WrongIdException;
import model.Client;

public class ClientBLL {
	
	private ArrayList<Client> clients;
	private ClientDAO clientDAO;
	
	public ClientBLL() {
		this.clientDAO=new ClientDAO();
		this.clients=(ArrayList<Client>)clientDAO.findAll();
	}

	public ArrayList<Client> getClients() {
		return clients;
	}
	
	public void addClient(String nume,String prenume,String adresa) {
		int id=0;
		int maxid=0;
		if(clients.size()>0) {
			for(Client c:clients) {
				if(maxid<c.getId())maxid=c.getId();
			}
			id=maxid+1;	
		}
		Client client=new Client(id,nume,prenume,adresa);
		try {
			clientDAO.insert(client);
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
	public void updateClient(String id,String nume,String prenume,String adresa) throws WrongIdException {
		boolean ok=false;
		String n = null,p=null,a=null;
		if(id.isEmpty()||!id.matches("^\\d+$")) throw new WrongIdException("Id field must contain a valid number");
		else {
			for(Client c:clients) {
				if(c.getId()==Integer.parseInt(id)) {
					if(nume.equals(c.getNume())==false&&!nume.isEmpty()) {
						n=nume;
					}
					else n=c.getNume();
					if(prenume.equals(c.getPrenume())==false&&!prenume.isEmpty()) {
						p=prenume;
					}
					else p=c.getPrenume();
					if(adresa.equals(c.getAdresa())==false&&!adresa.isEmpty()) {
						a=adresa;
					}
					else a=c.getAdresa();
					ok=true;
				}
			}
			if(ok==false)throw new WrongIdException("Id field must contain the id of an existing client");
			Client client=new Client(Integer.parseInt(id),n,p,a);
			try {
				clientDAO.update(Integer.parseInt(id), client);//
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}	
		}
	}
	public void deleteClient(String id) throws WrongIdException {
		boolean ok=false;
		Client client = null;
		if(id.isEmpty()||!id.matches("^\\d+$")) throw new WrongIdException("Id field must contain a valid number");
		else {
			for(Client c:clients) {
				if(c.getId()==Integer.parseInt(id)) {
					client=new Client(c.getId(),c.getNume(),c.getPrenume(),c.getAdresa());
					ok=true;
				}
			}
			if(ok==false)throw new WrongIdException("Id field must contain the id of an existing client");
			try {
				clientDAO.delete(client);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
