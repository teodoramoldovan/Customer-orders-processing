package dao;


import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import model.ComandaProdus;

public class ComandaProdusDAO extends AbstractDAO<ComandaProdus> {
	public ComandaProdusDAO() {}
	
	public List<ComandaProdus> findALL(){
		return super.findAll();
	}
	public ComandaProdus findById(int id) {
		return super.findById(id);
	}
	public List<ComandaProdus> createObjects(ResultSet resultSet){
		return super.createObjects(resultSet);
	}
	public ComandaProdus insert(ComandaProdus c) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException {
		return super.insert(c);
	}
	public ComandaProdus update(int id,ComandaProdus c) {
		return super.update(id, c);
	}
	public ComandaProdus detele(ComandaProdus c) {
		return super.delete(c);
	}
}
