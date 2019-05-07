package GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import businessLayer.ClientBLL;
import businessLayer.ComandaBLL;
import businessLayer.ProdusBLL;
import exception.InexistentClientException;
import exception.InexistentProductException;
import exception.StockTooLowException;
import exception.WrongIdException;

public class Interfata extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame=new JFrame("Warehouse");
	private JPanel startPnl=new JPanel();
	private JPanel clientPnl=new JPanel();
	private JPanel produsPnl=new JPanel();
	private JPanel comandaPnl=new JPanel();
	private JPanel clientAddPnl=new JPanel();
	private JPanel produsAddPnl=new JPanel();
	private JPanel comandaAddPnl=new JPanel();
	private JPanel clientUpdatePnl=new JPanel();
	private JPanel produsUpdatePnl=new JPanel();
	private JPanel clientDeletePnl=new JPanel();
	private JPanel produsDeletePnl=new JPanel();
	private JPanel mainPnl=new JPanel();
	private JButton clientBtn=new JButton("Clients");
	private JButton comandaBtn=new JButton("Orders");
	private JButton produseBtn=new JButton("Products");
	private JButton clientBackBtn=new JButton("Back");
	private JButton produsBackBtn=new JButton("Back");
	private JButton comandaBackBtn=new JButton("Back");
	
	private JButton clientAddBtn=new JButton("Add Client");
	private JButton clientAddAddBtn=new JButton("Add Client");
	private JButton backToClientPnlBtn=new JButton("Back");
	
	private JButton clientUpdateBtn=new JButton("Update Client");
	private JButton clientUpdateUpdateBtn=new JButton("Update Client");
	private JButton backToClientPnlBtn2=new JButton("Back");
	
	private JButton clientDeleteBtn=new JButton("Delete Client");
	private JButton clientDeleteDeleteBtn=new JButton("Delete Client");
	private JButton backToClientPnlBtn3=new JButton("Back");
	
	private JButton produsAddBtn=new JButton("Add Product");
	private JButton produsAddAddBtn=new JButton("Add Product");
	private JButton backToProdusPnlBtn=new JButton("Back");
	
	private JButton produsUpdateBtn=new JButton("Update Product");
	private JButton produsUpdateUpdateBtn=new JButton("Update Product");
	private JButton backToProdusPnlBtn2=new JButton("Back");
	
	private JButton produsDeleteBtn=new JButton("Delete Product");
	private JButton produsDeleteDeleteBtn=new JButton("Delete Product");
	private JButton backToProdusPnlBtn3=new JButton("Back");
	
	private JButton comandaAddBtn=new JButton("Add Order");
	private JButton comandaAddAddBtn=new JButton("Add Order");
	private JButton backToComandaPnlBtn=new JButton("Back");
	
	private JLabel startLbl=new JLabel("");
	private JTable clientTbl,produsTbl,comandaTbl;
	private GenericTable table=new GenericTable();
	private GridBagConstraints c=new GridBagConstraints();
	
	private JLabel clientAddNumeLbl=new JLabel("Last name: ");
	private JLabel clientAddPrenumeLbl=new JLabel("First Name: ");
	private JLabel clientAddAdresaLbl=new JLabel("Address: ");
	private JTextField clientAddNumeTxt=new JTextField(20);
	private JTextField clientAddPrenumeTxt=new JTextField(20);
	private JTextField clientAddAdresaTxt=new JTextField(20);
	
	private JLabel clientUpdateIdLbl=new JLabel("Client ID: ");
	private JLabel clientUpdateNumeLbl=new JLabel("Last name: ");
	private JLabel clientUpdatePrenumeLbl=new JLabel("First name: ");
	private JLabel clientUpdateAdresaLbl=new JLabel("Address: ");
	private JTextField clientUpdateIdTxt=new JTextField(20);
	private JTextField clientUpdateNumeTxt=new JTextField(20);
	private JTextField clientUpdatePrenumeTxt=new JTextField(20);
	private JTextField clientUpdateAdresaTxt=new JTextField(20);
	
	private JLabel clientDeleteIdLbl=new JLabel("Client ID: ");
	private JTextField clientDeleteIdTxt=new JTextField(20);
	
	private JLabel produsAddNumeLbl=new JLabel("Name: ");
	private JLabel produsAddPretLbl=new JLabel("Price: ");
	private JLabel produsAddCantitateLbl=new JLabel("Quantity: ");
	private JTextField produsAddNumeTxt=new JTextField(20);
	private JTextField produsAddPretTxt=new JTextField(20);
	private JTextField produsAddCantitateTxt=new JTextField(20);
	
	private JLabel produsUpdateIdLbl=new JLabel("Product ID: ");
	private JLabel produsUpdateNumeLbl=new JLabel("Name: ");
	private JLabel produsUpdatePretLbl=new JLabel("Price: ");
	private JTextField produsUpdateIdTxt=new JTextField(20);
	private JTextField produsUpdateNumeTxt=new JTextField(20);
	private JTextField produsUpdatePretTxt=new JTextField(20);
	
	private JLabel produsDeleteIdLbl=new JLabel("Product ID: ");
	private JTextField produsDeleteIdTxt=new JTextField(20);
	
	private JLabel comandaAddIDClientLbl=new JLabel("Client ID: ");
	private JLabel comandaAddIDProdusLbl=new JLabel("Product ID: ");
	private JLabel comandaAddCantitateLbl=new JLabel("Quantity: ");
	private JTextField comandaAddIDClientTxt=new JTextField(20);
	private JTextField comandaAddIDProdusTxt=new JTextField(20);
	private JTextField comandaAddCantitateTxt=new JTextField(20);
	public Interfata() {
		ClientBLL clientBLL=new ClientBLL();
		ComandaBLL comandaBLL=new ComandaBLL();
		ProdusBLL produsBLL=new ProdusBLL();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,900);
		mainPnl.setLayout(new BorderLayout());
		startPnl.setLayout(new FlowLayout());
		clientBtn.setPreferredSize(new Dimension(200,100));
		produseBtn.setPreferredSize(new Dimension(200,100));
		comandaBtn.setPreferredSize(new Dimension(200,100));
		

		
		clientTbl=new JTable(table.createTable(clientBLL.getClients()));
		clientPnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		clientPnl.add(new JScrollPane(clientTbl),c);
		c.gridx=0;
		c.gridy=1;
		clientPnl.add(clientAddBtn, c);
		c.gridx=0;
		c.gridy=2;
		clientPnl.add(clientUpdateBtn, c);
		c.gridx=0;
		c.gridy=3;
		clientPnl.add(clientDeleteBtn, c);
		c.gridx=0;
		c.gridy=4;
		clientPnl.add(clientBackBtn,c);
		
		clientAddPnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		clientAddPnl.add(clientAddNumeLbl,c);
		c.gridx=1;
		c.gridy=0;
		clientAddPnl.add(clientAddNumeTxt,c);
		c.gridx=0;
		c.gridy=1;
		clientAddPnl.add(clientAddPrenumeLbl,c);
		c.gridx=1;
		c.gridy=1;
		clientAddPnl.add(clientAddPrenumeTxt,c);
		c.gridx=0;
		c.gridy=2;
		clientAddPnl.add(clientAddAdresaLbl,c);
		c.gridx=1;
		c.gridy=2;
		clientAddPnl.add(clientAddAdresaTxt,c);
		c.gridx=1;
		c.gridy=3;
		clientAddPnl.add(clientAddAddBtn, c);
		c.gridx=1;
		c.gridy=4;
		clientAddPnl.add(backToClientPnlBtn, c);
		
		
		clientUpdatePnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		clientUpdatePnl.add(clientUpdateIdLbl,c);
		c.gridx=1;
		c.gridy=0;
		clientUpdatePnl.add(clientUpdateIdTxt, c);
		c.gridx=0;
		c.gridy=1;
		clientUpdatePnl.add(clientUpdateNumeLbl,c);
		c.gridx=1;
		c.gridy=1;
		clientUpdatePnl.add(clientUpdateNumeTxt,c);
		c.gridx=0;
		c.gridy=2;
		clientUpdatePnl.add(clientUpdatePrenumeLbl,c);
		c.gridx=1;
		c.gridy=2;
		clientUpdatePnl.add(clientUpdatePrenumeTxt,c);
		c.gridx=0;
		c.gridy=3;
		clientUpdatePnl.add(clientUpdateAdresaLbl,c);
		c.gridx=1;
		c.gridy=3;
		clientUpdatePnl.add(clientUpdateAdresaTxt,c);
		c.gridx=1;
		c.gridy=4;
		clientUpdatePnl.add(clientUpdateUpdateBtn, c);
		c.gridx=1;
		c.gridy=5;
		clientUpdatePnl.add(backToClientPnlBtn2, c);
		
		clientDeletePnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		clientDeletePnl.add(clientDeleteIdLbl,c);
		c.gridx=1;
		c.gridy=0;
		clientDeletePnl.add(clientDeleteIdTxt, c);
		c.gridx=1;
		c.gridy=1;
		clientDeletePnl.add(clientDeleteDeleteBtn, c);
		c.gridx=1;
		c.gridy=2;
		clientDeletePnl.add(backToClientPnlBtn3, c);
		
		
		produsTbl=new JTable(table.createTable(produsBLL.getProduse()));
		produsPnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		produsPnl.add(new JScrollPane(produsTbl),c);
		c.gridx=0;
		c.gridy=1;
		produsPnl.add(produsAddBtn, c);
		c.gridx=0;
		c.gridy=2;
		produsPnl.add(produsUpdateBtn,c);
		c.gridx=0;
		c.gridy=3;
		produsPnl.add(produsDeleteBtn, c);
		c.gridx=0;
		c.gridy=4;
		produsPnl.add(produsBackBtn,c);
		
		
		
		produsAddPnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		produsAddPnl.add(produsAddNumeLbl,c);
		c.gridx=1;
		c.gridy=0;
		produsAddPnl.add(produsAddNumeTxt,c);
		c.gridx=0;
		c.gridy=1;
		produsAddPnl.add(produsAddPretLbl,c);
		c.gridx=1;
		c.gridy=1;
		produsAddPnl.add(produsAddPretTxt,c);
		c.gridx=0;
		c.gridy=2;
		produsAddPnl.add(produsAddCantitateLbl,c);
		c.gridx=1;
		c.gridy=2;
		produsAddPnl.add(produsAddCantitateTxt,c);
		c.gridx=1;
		c.gridy=3;
		produsAddPnl.add(produsAddAddBtn, c);
		c.gridx=1;
		c.gridy=4;
		produsAddPnl.add(backToProdusPnlBtn, c);
		
		produsUpdatePnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		produsUpdatePnl.add(produsUpdateIdLbl,c);
		c.gridx=1;
		c.gridy=0;
		produsUpdatePnl.add(produsUpdateIdTxt, c);
		c.gridx=0;
		c.gridy=1;
		produsUpdatePnl.add(produsUpdateNumeLbl,c);
		c.gridx=1;
		c.gridy=1;
		produsUpdatePnl.add(produsUpdateNumeTxt,c);
		c.gridx=0;
		c.gridy=2;
		produsUpdatePnl.add(produsUpdatePretLbl,c);
		c.gridx=1;
		c.gridy=2;
		produsUpdatePnl.add(produsUpdatePretTxt,c);
		c.gridx=1;
		c.gridy=3;
		produsUpdatePnl.add(produsUpdateUpdateBtn, c);
		c.gridx=1;
		c.gridy=4;
		produsUpdatePnl.add(backToProdusPnlBtn2, c);
		
		
		produsDeletePnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		produsDeletePnl.add(produsDeleteIdLbl,c);
		c.gridx=1;
		c.gridy=0;
		produsDeletePnl.add(produsDeleteIdTxt, c);
		c.gridx=1;
		c.gridy=1;
		produsDeletePnl.add(produsDeleteDeleteBtn, c);
		c.gridx=1;
		c.gridy=2;
		produsDeletePnl.add(backToProdusPnlBtn3, c);
		
		comandaTbl=new JTable(table.createTable(comandaBLL.getComenzi()));
		comandaPnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		comandaPnl.add(new JScrollPane(comandaTbl),c);
		c.gridx=0;
		c.gridy=1;
		comandaPnl.add(comandaAddBtn, c);
		c.gridx=0;
		c.gridy=2;
		comandaPnl.add(comandaBackBtn,c);
		
		comandaAddPnl.setLayout(new GridBagLayout());
		c.gridx=0;
		c.gridy=0;
		comandaAddPnl.add(comandaAddIDClientLbl,c);
		c.gridx=1;
		c.gridy=0;
		comandaAddPnl.add(comandaAddIDClientTxt,c);
		c.gridx=0;
		c.gridy=1;
		comandaAddPnl.add(comandaAddIDProdusLbl,c);
		c.gridx=1;
		c.gridy=1;
		comandaAddPnl.add(comandaAddIDProdusTxt,c);
		c.gridx=0;
		c.gridy=2;
		comandaAddPnl.add(comandaAddCantitateLbl,c);
		c.gridx=1;
		c.gridy=2;
		comandaAddPnl.add(comandaAddCantitateTxt,c);
		c.gridx=1;
		c.gridy=3;
		comandaAddPnl.add(comandaAddAddBtn, c);
		c.gridx=1;
		c.gridy=4;
		comandaAddPnl.add(backToComandaPnlBtn, c);
		
		clientBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				startPnl.setVisible(false);
				clientPnl.setVisible(true);
				mainPnl.add(clientPnl);
				mainPnl.revalidate();				
			}			
		});
		clientAddBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clientPnl.setVisible(false);
				clientAddPnl.setVisible(true);
				mainPnl.add(clientAddPnl);
				mainPnl.revalidate();
				
			}
			
		});
		clientAddAddBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(clientAddNumeTxt.getText().matches("[a-zA-Z]*")&&!clientAddNumeTxt.getText().isEmpty()
						&&clientAddPrenumeTxt.getText().matches("[a-zA-Z]*")&&!clientAddPrenumeTxt.getText().isEmpty()
						&&clientAddAdresaTxt.getText().matches("[a-zA-Z]*")&&!clientAddAdresaTxt.getText().isEmpty()) {
					ClientBLL cl=new ClientBLL();
					cl.addClient(clientAddNumeTxt.getText(), clientAddPrenumeTxt.getText(), clientAddAdresaTxt.getText());
					cl=new ClientBLL();
					clientTbl.setModel(table.createTable(cl.getClients()));
					clientAddNumeTxt.setText("");
					clientAddPrenumeTxt.setText("");
					clientAddAdresaTxt.setText("");
					clientAddPnl.setVisible(false);
					clientPnl.setVisible(true);
					mainPnl.revalidate();
				}
				else JOptionPane.showMessageDialog(null, "Wrong input format",null,JOptionPane.ERROR_MESSAGE);	
			}
			
		});
		backToClientPnlBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clientAddNumeTxt.setText("");
				clientAddPrenumeTxt.setText("");
				clientAddAdresaTxt.setText("");
				clientPnl.setVisible(true);
				clientAddPnl.setVisible(false);
				mainPnl.revalidate();
			}
			
		});
		clientUpdateBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clientPnl.setVisible(false);
				clientUpdatePnl.setVisible(true);
				mainPnl.add(clientUpdatePnl);
				mainPnl.revalidate();				
			}
			
		});
		clientUpdateUpdateBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(clientUpdateNumeTxt.getText().matches("[a-zA-Z]*")
						&&clientUpdatePrenumeTxt.getText().matches("[a-zA-Z]*")
						&&clientUpdateAdresaTxt.getText().matches("[a-zA-Z]*")) {
					try {
						ClientBLL cl=new ClientBLL();
						cl.updateClient(clientUpdateIdTxt.getText(),
								clientUpdateNumeTxt.getText(), clientUpdatePrenumeTxt.getText(), clientUpdateAdresaTxt.getText());
					} catch (WrongIdException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
					ClientBLL cl2=new ClientBLL();
					clientTbl.setModel(table.createTable(cl2.getClients()));
					clientUpdateIdTxt.setText("");
					clientUpdateNumeTxt.setText("");
					clientUpdatePrenumeTxt.setText("");
					clientUpdateAdresaTxt.setText("");
					clientUpdatePnl.setVisible(false);
					clientPnl.setVisible(true);
					mainPnl.revalidate();
				}
				else JOptionPane.showMessageDialog(null, "Wrong input format",null,JOptionPane.ERROR_MESSAGE);	
			}
			
		});
		backToClientPnlBtn2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clientUpdateIdTxt.setText("");
				clientUpdateNumeTxt.setText("");
				clientUpdatePrenumeTxt.setText("");
				clientUpdateAdresaTxt.setText("");
				clientPnl.setVisible(true);
				clientUpdatePnl.setVisible(false);
				mainPnl.revalidate();
			}
			
		});
		clientDeleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clientPnl.setVisible(false);
				clientDeletePnl.setVisible(true);
				mainPnl.add(clientDeletePnl);
				mainPnl.revalidate();
				
			}
			
		});
		clientDeleteDeleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
						ClientBLL cl=new ClientBLL();
						cl.deleteClient(clientDeleteIdTxt.getText());
					} catch (WrongIdException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
					ClientBLL cl2=new ClientBLL();
					clientTbl.setModel(table.createTable(cl2.getClients()));
					clientDeleteIdTxt.setText("");
					clientDeletePnl.setVisible(false);
					clientPnl.setVisible(true);
					mainPnl.revalidate();
				}
			
		});
		backToClientPnlBtn3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clientDeleteIdTxt.setText("");
				clientPnl.setVisible(true);
				clientDeletePnl.setVisible(false);
				mainPnl.revalidate();
			}
			
		});
		clientBackBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clientPnl.setVisible(false);
				startPnl.setVisible(true);
				mainPnl.revalidate();
				
			}
			
		});
		produseBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				startPnl.setVisible(false);
				produsPnl.setVisible(true);
				mainPnl.add(produsPnl);
				mainPnl.revalidate();
				
			}
			
		});
		produsAddBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				produsPnl.setVisible(false);
				produsAddPnl.setVisible(true);
				mainPnl.add(produsAddPnl);
				mainPnl.revalidate();
				
			}
			
		});
		produsAddAddBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if(produsAddNumeTxt.getText().matches("[a-zA-Z]*")&&!produsAddNumeTxt.getText().isEmpty()
							&&!produsAddPretTxt.getText().isEmpty()
							&&!produsAddCantitateTxt.getText().isEmpty()) {
						ProdusBLL pr=new ProdusBLL();
						pr.addProdus(produsAddNumeTxt.getText(), Float.parseFloat(produsAddPretTxt.getText()),
								Integer.parseInt(produsAddCantitateTxt.getText()));
						pr=new ProdusBLL();
						produsTbl.setModel(table.createTable(pr.getProduse()));
						produsAddNumeTxt.setText("");
						produsAddPretTxt.setText("");
						produsAddCantitateTxt.setText("");
						produsAddPnl.setVisible(false);
						produsPnl.setVisible(true);
						mainPnl.revalidate();
					}
					else JOptionPane.showMessageDialog(null, "Wrong input format",null,JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Wrong input format for price/quantity",null,JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
					

				
			}
			
		});
		backToProdusPnlBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				produsAddNumeTxt.setText("");
				produsAddPretTxt.setText("");
				produsAddCantitateTxt.setText("");
				produsPnl.setVisible(true);
				produsAddPnl.setVisible(false);
				mainPnl.revalidate();
			}
			
		});
		produsUpdateBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				produsPnl.setVisible(false);
				produsUpdatePnl.setVisible(true);
				mainPnl.add(produsUpdatePnl);
				mainPnl.revalidate();
				
			}
			
		});
		produsUpdateUpdateBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(produsUpdateNumeTxt.getText().matches("[a-zA-Z]*")
						||produsUpdatePretTxt.getText().matches("([0-9]*[.])?[0-9]+")) {
					try {
						ProdusBLL pr=new ProdusBLL();
						pr.updateProdus(produsUpdateIdTxt.getText(),
								produsUpdateNumeTxt.getText(), produsUpdatePretTxt.getText());
					} catch (WrongIdException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
					ProdusBLL pr2=new ProdusBLL();
					produsTbl.setModel(table.createTable(pr2.getProduse()));
					produsUpdateIdTxt.setText("");
					produsUpdateNumeTxt.setText("");
					produsUpdatePretTxt.setText("");
					produsUpdatePnl.setVisible(false);
					produsPnl.setVisible(true);
					mainPnl.revalidate();
				}
				else JOptionPane.showMessageDialog(null, "Wrong input format",null,JOptionPane.ERROR_MESSAGE);

			}
			
		});
		backToProdusPnlBtn2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				produsUpdateIdTxt.setText("");
				produsUpdateNumeTxt.setText("");
				produsUpdatePretTxt.setText("");
				produsPnl.setVisible(true);
				produsUpdatePnl.setVisible(false);
				mainPnl.revalidate();
			}
			
		});
		produsDeleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				produsPnl.setVisible(false);
				produsDeletePnl.setVisible(true);
				mainPnl.add(produsDeletePnl);
				mainPnl.revalidate();
				
			}
			
		});
		produsDeleteDeleteBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					    ProdusBLL pr=new ProdusBLL();
						pr.deleteProdus(produsDeleteIdTxt.getText());
					} catch (WrongIdException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
					}
					ProdusBLL pr2=new ProdusBLL();
					produsTbl.setModel(table.createTable(pr2.getProduse()));
					produsDeleteIdTxt.setText("");
					produsDeletePnl.setVisible(false);
					produsPnl.setVisible(true);
					mainPnl.revalidate();
				}
			
		});
		backToProdusPnlBtn3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				produsDeleteIdTxt.setText("");
				produsPnl.setVisible(true);
				produsDeletePnl.setVisible(false);
				mainPnl.revalidate();
			}
			
		});
		produsBackBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				produsPnl.setVisible(false);
				startPnl.setVisible(true);
				mainPnl.revalidate();
				
			}
			
		});
		comandaBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				startPnl.setVisible(false);
				comandaPnl.setVisible(true);
				
				mainPnl.add(comandaPnl);
				mainPnl.revalidate();
				
			}
			
		});
		comandaAddBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				comandaPnl.setVisible(false);
				comandaAddPnl.setVisible(true);
				mainPnl.add(comandaAddPnl);
				mainPnl.revalidate();
				
			}
			
		});
		comandaAddAddBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int id = 0;
				try {
					if(!comandaAddIDClientTxt.getText().isEmpty()
							&&!comandaAddIDProdusTxt.getText().isEmpty()
							&&!comandaAddCantitateTxt.getText().isEmpty()) {
						
							try {
								ComandaBLL co=new ComandaBLL();
								id=co.addComanda(Integer.parseInt(comandaAddIDClientTxt.getText()),
										Integer.parseInt(comandaAddIDProdusTxt.getText()),
										Integer.parseInt(comandaAddCantitateTxt.getText()));
							} catch (StockTooLowException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
							} catch (InexistentClientException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
								
							} catch (InexistentProductException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(),null,JOptionPane.ERROR_MESSAGE);
								
							}
						ComandaBLL co2=new ComandaBLL();
						comandaTbl.setModel(table.createTable(co2.getComenzi()));
						comandaAddIDClientTxt.setText("");
						comandaAddIDProdusTxt.setText("");
						comandaAddCantitateTxt.setText("");
						comandaAddPnl.setVisible(false);
						comandaPnl.setVisible(true);
						mainPnl.revalidate();
						co2.createBill(id);
					}
					else JOptionPane.showMessageDialog(null, "Fields can not be empty",null,JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {

					JOptionPane.showMessageDialog(null, "Wrong input format",null,JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException e1) {

					e1.printStackTrace();
				}

			}
			
		});
		backToComandaPnlBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comandaAddIDClientTxt.setText("");
				comandaAddIDProdusTxt.setText("");
				comandaAddCantitateTxt.setText("");
				comandaPnl.setVisible(true);
				comandaAddPnl.setVisible(false);
				mainPnl.revalidate();
			}
			
		});
		comandaBackBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				comandaPnl.setVisible(false);
				startPnl.setVisible(true);
				mainPnl.revalidate();
				
			}
			
		});
		startPnl.add(clientBtn);
		startPnl.add(produseBtn);
		startPnl.add(comandaBtn);
		mainPnl.setSize(new Dimension(1800,1000));
		startLbl.setPreferredSize(new Dimension(0,300));
		mainPnl.add(startLbl,BorderLayout.NORTH);
		mainPnl.add(startPnl,BorderLayout.CENTER);
		frame.add(mainPnl);
		
		
		frame.setVisible(true);
	}
	
	
}
