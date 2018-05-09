package ass3.data.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ass.data.DBConnection;
import common.entities.Article;
import common.repositories.IArticleDao;

public class ArticleDao implements IArticleDao {
	public DBConnection conn = new DBConnection();
	private WriterDao wD;

	public ArticleDao(WriterDao wD) {
		super();
		this.wD = wD;
	}
	@Override
	public List<Article> findAll(java.lang.Boolean closeConn) throws SQLException {
		try {
			conn.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stat = conn.getConn().createStatement();
		String query = "SELECT * FROM article ORDER BY ID";
		ResultSet rs = stat.executeQuery(query);
		List<Article> ws = new ArrayList<Article>();
		while (rs.next()) {
			Article a = new Article();
			int id = rs.getInt("id");
			a.setId(id);
			String title = rs.getString("title");
			a.setTitle(title);
			String abstractHeader = rs.getString("abstractHeader");
			a.setAbstractHeader(abstractHeader);
			String body = rs.getString("body");
			a.setBody(body);
			int writer_id = Integer.parseInt(rs.getString("writer_id"));
			a.setAuthor(wD.findByid(writer_id));
			ws.add(a);
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
	
	public Article findByTitle(String title) throws SQLException {
			for(Article a: findAll(false))
			{
				if(a.getTitle().equals(title))
				{
					return a;
				}
			}
			return null;
	}
	public Article findByid(int id) throws SQLException {
		for(Article a: findAll(false))
		{
			if(a.getId() == id)
			{
				return a;
			}
		}
		return null;
	}
	public List<Article> findArcticlesByName(String name) throws SQLException {
		List<Article> as = new ArrayList<>();
		for(Article a: findAll(false))
		{
			if(a.getAuthor().getName().equals(name))
			{
				as.add(a);
			}
		}
		return as;
	}
	
	public void addArticle(Article a) throws ClassNotFoundException, SQLException{
		conn.start();
		String query2 = "INSERT INTO article"
				+ "(id, title, abstractHeader, body, writer_id) VALUES" + "(?,?,?,?,?)";
		
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);
		
		int i=0;
		for(Article s: findAll(false)){
			if(s.getId()==i){
				i++;
			}else{
				break;
			}	
		}
		psQuery2.setInt(1, i);
		psQuery2.setString(2, a.getTitle());
		psQuery2.setString(3, a.getAbstractHeader());
		psQuery2.setString(4, a.getBody());
		psQuery2.setInt(5, a.getAuthor().getId());
		
		psQuery2.executeUpdate();
		conn.close();
	}
	public void deleteArticleByTitle(String title) throws ClassNotFoundException, SQLException{
		conn.start();
		String query2 = "DELETE FROM article WHERE title=?";
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);
		psQuery2.setString(1, title);
		psQuery2.executeUpdate();
		conn.close();
	}
	
	public void updateBody(String title, String body) throws ClassNotFoundException, SQLException{
		
		conn.start();
		String query2 = "UPDATE article SET body=?"
				+ "WHERE title=?";
		
		PreparedStatement psQuery2 = conn.getConn().prepareStatement(query2);
		psQuery2.setString(1, body);
		psQuery2.setString(2, title);
		
		psQuery2.executeUpdate();
		conn.close();
	}

}
