package common.repositories;

import java.sql.SQLException;
import java.util.List;

import common.entities.Article;

public interface IArticleDao {
	
	public List<Article> findAll(java.lang.Boolean closeConn) throws SQLException;
	public Article findByTitle(String title) throws SQLException;
	public Article findByid(int id) throws SQLException;
	public List<Article> findArcticlesByName(String name) throws SQLException;
	public void addArticle(Article a) throws ClassNotFoundException, SQLException;
	public void deleteArticleByTitle(String title) throws ClassNotFoundException, SQLException;
	public void updateBody(String title, String body) throws ClassNotFoundException, SQLException;

	

}
