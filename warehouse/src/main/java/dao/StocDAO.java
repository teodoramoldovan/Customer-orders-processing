package dao;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Stoc;

public class StocDAO extends AbstractDAO<Stoc> {
	
	public StocDAO() {}
	
	public List<Stoc> findALL(){
		return super.findAll();
	}
	public Stoc findById(int id) {
		return super.findById(id);
	}
	public List<Stoc> createObjects(ResultSet resultSet){
		return super.createObjects(resultSet);
	}
	public Stoc insert(Stoc s) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException {
		return super.insert(s);
	}
	public Stoc update(int id,Stoc s) {
		return super.update(id, s);
	}
	public Stoc detele(Stoc s) {
		return super.delete(s);
	}
}
