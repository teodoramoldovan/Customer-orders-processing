package dao;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import model.Produs;

public class ProdusDAO extends AbstractDAO<Produs>{
	public ProdusDAO() {}
	
	public List<Produs> findALL(){
		return super.findAll();
	}
	public Produs findById(int id) {
		return super.findById(id);
	}
	public List<Produs> createObjects(ResultSet resultSet){
		return super.createObjects(resultSet);
	}
	public Produs insert(Produs p) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException {
		return super.insert(p);
	}
	public Produs update(int id,Produs p) {
		return super.update(id, p);
	}
	public Produs detele(Produs p) {
		return super.delete(p);
	}
}
