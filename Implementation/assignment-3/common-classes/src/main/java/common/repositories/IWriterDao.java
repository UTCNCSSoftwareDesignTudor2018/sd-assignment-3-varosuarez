package common.repositories;

import java.sql.SQLException;
import java.util.List;

import common.entities.Writer;

public interface IWriterDao {
	
	public List<Writer> findAll(java.lang.Boolean closeConn) throws SQLException;
	public Writer findByUsernameAndPassword(String username, String password) throws SQLException;
	public Writer findByid(int id) throws SQLException;
	public Writer findByidNumber(String idNumber) throws SQLException;
	public Writer findByName(String name) throws SQLException;
	public void addWriter(Writer w) throws ClassNotFoundException, SQLException;
	public void deleteWriterById(String id) throws ClassNotFoundException, SQLException;
	public void updateWriter(String idNumber, String name, String surname, String username, String password) throws ClassNotFoundException, SQLException;
	public void addWriter(java.lang.String name, java.lang.String surname, java.lang.String username, java.lang.String password, java.lang.String idNumber) throws ClassNotFoundException, SQLException;
}
