package dao;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Client;

public class ClientDAO extends AbstractDAO<Client> {
	public ClientDAO() {}
	
	public List<Client> findALL(){
		return super.findAll();
	}
	public Client findById(int id) {
		return super.findById(id);
	}
	public List<Client> createObjects(ResultSet resultSet){
		return super.createObjects(resultSet);
	}
	public Client insert(Client c) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException {
		return super.insert(c);
	}
	public Client update(int id,Client c) {
		return super.update(id, c);
	}
	public Client detele(Client c) {
		return super.delete(c);
	}
}
