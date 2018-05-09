package common.entities;


import java.io.Serializable;
import java.util.List;

public class Writer implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2495667956987517505L;
	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String idNumber;
	private List<Article> articles;
	public Writer(int id, String name, String surname, String username, String password, String idNumber,
			List<Article> articles) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.idNumber = idNumber;
		this.articles = articles;
	}
	
	public Writer(String name, String surname, String username, String password, String idNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.idNumber = idNumber;
	}

	public Writer(int id, String name, String surname, String username, String password, String idNumber) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.idNumber = idNumber;
	}

	public Writer() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articles == null) ? 0 : articles.hashCode());
		result = prime * result + id;
		result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Writer other = (Writer) obj;
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		if (id != other.id)
			return false;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		if(this.articles != null){
			return  String.format("{\n\tname:" + name + "\n\tsurname: " + surname + 
				"\n\tusername: " + username + "\n\tpassword: " + password 
				+"\n\tid: "+ idNumber +"\n\tartciles: " + articles.toString()+"\n}");
		}else{
			return  String.format("{\n\tname:" + name + "\n\tsurname: " + surname + 
					"\n\tusername: " + username + "\n\tpassword: " + password 
					+"\n\tid: "+ idNumber +"\n\tartciles: null" + "\n}");
		}
	}
	
	
	
}
