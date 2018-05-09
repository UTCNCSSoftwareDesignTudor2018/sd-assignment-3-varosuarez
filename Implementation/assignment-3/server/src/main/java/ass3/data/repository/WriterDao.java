package ass3.data.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ass.data.DBConnection;
import common.entities.Writer;
import common.repositories.IWriterDao;

public class WriterDao implements IWriterDao{
	
	public DBConnection conn = new DBConnection();
	@Override
	public List<Writer> findAll(java.lang.Boolean closeConn) throws SQLException {
		try {
			conn.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stat = conn.getConn().createStatement();
		String query = "SELECT * FROM writer ORDER BY ID";
		ResultSet rs = stat.executeQuery(query);
		List<Writer> ws = new ArrayList<Writer>();
		while (rs.next()) {
			Writer w = new Writer();
			int id = rs.getInt("id");
			w.setId(id);
			String name = rs.getString("name");
			w.setName(name);
			String surname = rs.getString("surname");
			w.setSurname(surname);
			String username = rs.getString("username");
			w.setUsername(username);
			String password = rs.getString("password");
			w.setPassword(password);
			String idNumber = rs.getString("idNumber");
			w.setIdNumber(idNumber);	
			ws.add(w);
		}
		rs.close();
		stat.close();
		if(closeConn){
			try {
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ws;		
	}
	@Override
	public Writer findByUsernameAndPassword(String username, String password) throws SQLException {
			for(Writer w: findAll(false))
			{
				if(w.getUsername().equals(username) && w.getPassword().equals(password))
				{
					return w;
				}
			}
			return null;
	}
	@Override
	public Writer findByid(int id) throws SQLException {
		for(Writer w: findAll(false))
		{
			if(w.getId() == id)
			{
				return w;
			}
		}
		return null;
	}
	@Override
	public Writer findByidNumber(String idNumber) throws SQLException {
		for(Writer w: findAll(false))
		{
			if(w.getIdNumber().equals(idNumber))
			{
				return w;
			}
		}
		return null;
	}
	@Override
	public Writer findByName(String name) throws SQLException {
		for(Writer w: findAll(false))
		{
			if(w.getName() == name)
			{
				return w;
			}
		}
		return null;
	}
	@Override
	public void addWriter(Writer w) throws ClassNotFoundException, SQLException{
		conn.start();
		String query2 = "INSERT INTO writer"
				+ "(id, name, surname, username, password, idNumber) VALUES" + "(?,?,?,?,?,?)";
		
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);
		
		int i=0;
		for(Writer s: findAll(false)){
			if(s.getId()==i){
				i++;
			}else{
				break;
			}	
		}
		psQuery2.setInt(1, i);
		psQuery2.setString(2, w.getName());
		psQuery2.setString(3, w.getSurname());
		psQuery2.setString(4, w.getUsername());
		psQuery2.setString(5, w.getPassword());
		psQuery2.setString(6, w.getIdNumber());
		
		psQuery2.executeUpdate();
		conn.close();
	}
	@Override
	public void deleteWriterById(String id) throws ClassNotFoundException, SQLException{
		conn.start();
		String query2 = "DELETE FROM writer WHERE idNumber=?";
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);
		psQuery2.setString(1, id);
		psQuery2.executeUpdate();
		conn.close();
	}
	@Override
	public void updateWriter(String idNumber, String name, String surname, String username, String password) throws ClassNotFoundException, SQLException{
		conn.start();
		Writer a = findByidNumber(idNumber);
		deleteWriterById(idNumber);
		String query2 = "INSERT INTO writer"
				+ "(id, name, surname, username, password, idNumber) VALUES" + "(?,?,?,?,?,?)";
		
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);
		
		psQuery2.setInt(1, a.getId());
		psQuery2.setString(2, name);
		psQuery2.setString(3, surname);
		psQuery2.setString(4, username);
		psQuery2.setString(5, password);
		psQuery2.setString(6, idNumber);
		
		psQuery2.executeUpdate();
		conn.close();
	}
	@Override
	public void addWriter(String name, String surname, String username, String password, String idNumber) throws ClassNotFoundException, SQLException {
		conn.start();
		
		
		String query2 = "INSERT INTO writer"
				+ "(id, name, surname, username, password, idNumber) VALUES" + "(?,?,?,?,?,?)";
		
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);
		int i=0;
		for(Writer s: findAll(false)){
			if(s.getId()==i){
				i++;
			}else{
				break;
			}	
		}
		psQuery2.setInt(1, i);
		psQuery2.setString(2, name);
		psQuery2.setString(3, surname);
		psQuery2.setString(4, username);
		psQuery2.setString(5, password);
		psQuery2.setString(6, idNumber);
		
		psQuery2.executeUpdate();
		conn.close();
	}

}
