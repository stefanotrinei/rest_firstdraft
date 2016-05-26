package asw.rest.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import asw.rest.model.Esame;

public class EsameDAO {


	public List<Esame> doRetrieveAll() throws PersistenceException {
		
		DataSource ds = new DataSource();
		Connection cn = ds.getConnection();
		PreparedStatement st = null;
		String select = "select id,docente_id,corso,aula,data from esami";
		List<Esame> esami = null;
		Esame es = null;
		ResultSet set = null;
		try {
			st = cn.prepareStatement(select);
			set = st.executeQuery();
			esami = new LinkedList<Esame>();
			while(set.next()){
				
				es = new Esame();
				es.setId(set.getInt("id"));
				es.setIdDocente(set.getInt("docente_id"));
				es.setCorso(set.getString("corso"));
				es.setAula(set.getString("aula"));
				es.setData(set.getString("data"));
				esami.add(es);
			}
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(st!=null)
					st.close();
				if(cn!=null)
					cn.close();
				if(set!=null)
					set.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
				}	
			}
		return esami;
	}


	public void saveOrUpdate(Esame esame) throws PersistenceException  {
		
		
		if(esame.getId() == 0)
			save(esame);
		else 
			update(esame);
	}
	
	public void save(Esame esame) throws PersistenceException {
		
		DataSource ds = new DataSource();
		Connection connection = ds.getConnection();
		PreparedStatement statement=null;
		try {
			String insert = "insert into esami(id,docente_id,corso,aula,data) values (?,?,?,?,?)";
			statement = connection.prepareStatement(insert);
			statement.setInt(1, esame.getId());
			statement.setInt(2, esame.getIdDocente());
			statement.setString(3, esame.getCorso());
			statement.setString(4, esame.getAula());
			statement.setString(5, esame.getData());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(statement!=null)
					statement.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	private void update(Esame esame) throws PersistenceException {
		
		DataSource ds = new DataSource();
		Connection connection = ds.getConnection();
		PreparedStatement statement=null;
		try {
			String update = "update esami set docente_id=?,corso=?,aula=?,data=?,where id=?";
			statement = connection.prepareStatement(update);
			statement.setInt(1, esame.getIdDocente());
			statement.setString(2, esame.getCorso());
			statement.setString(3, esame.getAula());
			statement.setString(4, esame.getData());
			statement.setInt(5, esame.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(statement!=null)
					statement.close();
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}


	public List<Esame> doRetrieveByDocente(int idDocente)
			throws PersistenceException {
		
		DataSource ds = new DataSource();
		Connection cn = ds.getConnection();
		PreparedStatement st = null;
		String select = "select corso,aula,data from esami where docente_id=?";
		List<Esame> esami = null;
		Esame es = null;
		ResultSet set = null;
		try {
			st = cn.prepareStatement(select);
			st.setInt(1, idDocente);
			set = st.executeQuery();
			esami = new LinkedList<Esame>();
			while(set.next()){
				
				es = new Esame();
				es.setCorso(set.getString("corso"));
				es.setAula(set.getString("aula"));
				es.setData(set.getString("data"));
				esami.add(es);
			}
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(st!=null)
					st.close();
				if(cn!=null)
					cn.close();
				if(set!=null)
					set.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
				}	
			}
		return esami;
	}
	public static void main(String[] args) throws PersistenceException{
//		Esame e = new Esame(2,20,"c2","a2","d2");
//		
//		EsameDAO dao = new EsameDAO();
//		dao.save(e);
//		List<Esame> list = dao.doRetrieveAll();
//		list.forEach(a->System.out.println(a));
		
		
	}
	

}