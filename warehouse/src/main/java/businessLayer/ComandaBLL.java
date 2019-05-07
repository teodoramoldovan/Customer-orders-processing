package businessLayer;

import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ClientDAO;
import dao.ComandaDAO;
import dao.ComandaProdusDAO;
import dao.ProdusDAO;
import dao.StocDAO;
import exception.InexistentClientException;
import exception.InexistentProductException;
import exception.StockTooLowException;
import model.Client;
import model.Comanda;
import model.ComandaProdus;
import model.Produs;
import model.Stoc;

public class ComandaBLL {
	
	private ArrayList<Comanda> comenzi;
	private ComandaDAO comandaDAO;
	private ArrayList<Stoc> stoc;
	private StocDAO stocDAO;
	private ArrayList<ComandaProdus> comenziProduse;
	private ComandaProdusDAO comandaProdusDAO;
	private ArrayList<Client> clients;
	private ClientDAO clientDAO;
	private ArrayList<Produs> produse;
	private ProdusDAO produsDAO;
	
	public ComandaBLL() {
		this.comandaDAO=new ComandaDAO();
		this.comenzi=(ArrayList<Comanda>)comandaDAO.findAll();
		this.stocDAO=new StocDAO();
		this.stoc=(ArrayList<Stoc>)stocDAO.findAll();
		this.comandaProdusDAO=new ComandaProdusDAO();
		this.comenziProduse=(ArrayList<ComandaProdus>)comandaProdusDAO.findAll();
		this.produsDAO=new ProdusDAO();
		this.produse=(ArrayList<Produs>)produsDAO.findAll();
		this.clientDAO=new ClientDAO();
		this.clients=(ArrayList<Client>)clientDAO.findAll();
	}

	public ArrayList<Comanda> getComenzi() {
		return comenzi;
	}
	public boolean verifyClient(int id_client) {	
		for(Client c:clients) {
			if(id_client==c.getId())return true;
		}
		return false;
	}
	public boolean verifyProduct(int id_produs) {
		for(Produs p:produse) {
			if(id_produs==p.getId())return true;
		}
		return false;
	}
	public int addComanda(int id_client,int id_produs,int cantitate) throws StockTooLowException, InexistentClientException, InexistentProductException {
		int cant=0;
		int maxid=0;
		int id=0;
		for(Stoc st:stoc) {
			if(st.getId_produs()==id_produs) {
				cant=st.getCantitate();	
			}
		}
		if(verifyClient(id_client)==false) throw new InexistentClientException("Client doesn't exist");
		else {
			if(verifyProduct(id_produs)==false)throw new InexistentProductException("Product doesn't exist");
			else {
				if(cant<cantitate)throw new StockTooLowException("Stock is too low");
				else {
					
					if(comenzi.size()>0) {
						for(Comanda c:comenzi) {
							if(maxid<c.getId())maxid=c.getId();
						}
						id=maxid+1;
					}
					Comanda comanda=new Comanda(id,id_client);
					comenzi.add(comanda);
					Stoc s=null;
					for(int i=0;i<stoc.size();i++) {						
						if(stoc.get(i).getId_produs()==id_produs) {
							 s=new Stoc(id_produs,stoc.get(i).getCantitate()-cantitate);							
						}
					}
					ComandaProdus comandaProdus=new ComandaProdus(id,id_produs,cantitate);
					try {
						comandaDAO.insert(comanda);						
						stocDAO.update(s.getId_produs(), s);
						comandaProdusDAO.insert(comandaProdus);						
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
			}
		}		
		return id;
	}

	public void createBill(int id) {
		float total=0;
		Client client = null;
		Comanda comanda=null;
		for(Comanda co:comenzi) {
			if(co.getId()==id) {
				comanda=new Comanda(co.getId(),co.getId_client());
			}
		}
		for(Client c:clients) {
			if(c.getId()==comanda.getId_client()) {
				client=new Client(c.getId(),c.getNume(),c.getPrenume(),c.getAdresa());
			}
		}
		Document doc=new Document();
		String nume=new String("BillForClient"+client.getNume()+client.getPrenume()+"Order"+id+".pdf");
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(nume));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		doc.open();
		for(ComandaProdus cp:comenziProduse) {			
			System.out.println(cp.getId_comanda());
			if(cp.getId_comanda()==comanda.getId()) {
				for(Produs p:produse) {
					if(p.getId()==cp.getId_produs()) {
						try {
							doc.add(new Paragraph(p.getNume()+"................."+String.valueOf(cp.getCantitate())+" X "+String.valueOf(p.getPret())));
						} catch (DocumentException e) {
							e.printStackTrace();
						}
						total+=(p.getPret()*cp.getCantitate());
					}
				}
			}
		}
		try {
			doc.add(new Paragraph("TOTAL................."+total));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		doc.close();		
	}
}
	