package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataAccessLayer.ConnectionFactory;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}
	private String createSelectQuery(String field) {
		StringBuilder sb=new StringBuilder();
		sb.append("select ");
		sb.append(" * ");
		sb.append(" from ");
		sb.append(type.getSimpleName());
		sb.append(" where "+field+"=?");
		return sb.toString();
	}
	private String createSelectAllQuery() {
		StringBuilder sb=new StringBuilder();
		sb.append("select ");
		sb.append(" * ");
		sb.append(" from ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}
	private String createInsertQuery() {
		StringBuilder sb=new StringBuilder();
		sb.append("insert ");
		sb.append("into ");
		sb.append(type.getSimpleName());
		sb.append(" ( ");
		boolean prim=true;
		for(Field f:type.getDeclaredFields()) {
			if(prim) {
				prim=false;
			}
			else {
				sb.append(", ");
			}
			sb.append(f.getName());
		}
		sb.append(" ) ");
		sb.append(" values ");
		sb.append(" ( " );
		prim=true;
		for(@SuppressWarnings("unused") Field f:type.getDeclaredFields()) {
			if(prim) {
				prim=false;
			}
			else sb.append(", ");
			sb.append("?");
		}
		sb.append(" )");
		return sb.toString();
	}
	private String perechi(int id, T t) {//se creeaza perechi de forma id=new id (de exemplu) pentru toate col unei tabele
										// metoda auxiiara pt update
		StringBuilder sb = new StringBuilder();
		boolean prim = true;
		for (Field f : type.getDeclaredFields()) {
			if (prim)
				prim = false;
			else sb.append(", ");
			sb.append(f.getName());
			sb.append(" = ");
			try {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(t);
				if(isANumber(value.toString()))
					sb.append(value);
				else 
				{
					sb.append("'");
					sb.append(value);
					sb.append("'");
				}
			} catch (IntrospectionException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	private String createUpdateQuery(int id,T t){
		StringBuilder sb=new StringBuilder();
		sb.append("update ");
		sb.append(type.getSimpleName());
		sb.append(" set ");
		sb.append(perechi(id,t));
		sb.append(" where ");
		sb.append(type.getDeclaredFields()[0].getName());
		sb.append(" = ");
		sb.append(id);
		return sb.toString();
	}
	private String createDeleteQuery(T t) {
		StringBuilder sb=new StringBuilder();
		sb.append("delete from ");
		sb.append(type.getSimpleName());
		sb.append(" where id=");
		Field f=type.getDeclaredFields()[0];
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
			Method method = propertyDescriptor.getReadMethod();
			Object value = method.invoke(t);
			sb.append(value);
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}	
		return sb.toString();

	}
	public List<T> findAll() { //gaseste toate elementele unei tabele
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query=createSelectAllQuery();
		System.out.println(query);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	public T findById(int id) {//gaseste elementul cu id-ul transmis ca parametru
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		System.out.println(query);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	public List<T> createObjects(ResultSet resultSet) {//lista cu obiecte de un tip; pt find
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();//instanta noua din clasa pe care o vrem T(se inlocuieste)
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
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
		return list;
	}
	public T insert(T t) throws SQLException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			String query=createInsertQuery();
			System.out.println(query);
			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
			int i=1;
			for(Field f:type.getDeclaredFields()) {
				PropertyDescriptor propertyDescriptor=new PropertyDescriptor(f.getName(),type);
				Method method=propertyDescriptor.getReadMethod();
				Object val=method.invoke(t);
				statement.setObject(i++, val);				
			}
			statement.addBatch();
			statement.executeBatch();
		}finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}
	private boolean isANumber(String s)//Double.parseDouble da fail daca nu poate converti
										// se foloseste metoda pentru a verifica daca stringul introdus de utlizator reprezinta 
										// o valoare care este numerica , daca nu=>numberformatException
	{		
			try {
				Double.parseDouble(s);
			} catch (NumberFormatException e) {
				return false;
			}catch(NullPointerException e) {
				return false;
			}
			return true;		
	}
	public T update( int id,T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query=createUpdateQuery(id,t);
		System.out.println(query);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update exc");
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}
	public T delete(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query=createDeleteQuery(t);
		System.out.println(query);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("delete exc");
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}
}
