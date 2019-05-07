package dao;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import model.Comanda;

public class ComandaDAO extends AbstractDAO<Comanda>{
	
	public ComandaDAO() {}
	
	public List<Comanda> findALL(){
		return super.findAll();
	}
	public Comanda findById(int id) {
		return super.findById(id);
	}
	public List<Comanda> createObjects(ResultSet resultSet){
		return super.createObjects(resultSet);
	}
	public Comanda insert(Comanda c) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException {
		return super.insert(c);
	}
	public Comanda update(int id,Comanda c) {
		return super.update(id, c);
	}
	public Comanda detele(Comanda c) {
		return super.delete(c);
	}
}
