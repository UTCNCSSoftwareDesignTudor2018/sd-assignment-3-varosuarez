package common.entities;

import java.io.Serializable;

public class Article implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958015621320317258L;
	private int id;
	private String title;
	private String abstractHeader;
	private Writer author;
	private String body;
	public Article(int id, String title, String abstractHeader, Writer author, String body) {
		super();
		this.id = id;
		this.title = title;
		this.abstractHeader = abstractHeader;
		this.author = author;
		this.body = body;
	}
	public Article(String title, String abstractHeader, Writer author, String body) {
		super();
		this.title = title;
		this.abstractHeader = abstractHeader;
		this.author = author;
		this.body = body;
	}

	public Article() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstractHeader() {
		return abstractHeader;
	}
	public void setAbstractHeader(String abstractHeader) {
		this.abstractHeader = abstractHeader;
	}
	public Writer getAuthor() {
		return author;
	}
	public void setAuthor(Writer author) {
		this.author = author;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abstractHeader == null) ? 0 : abstractHeader.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Article other = (Article) obj;
		if (abstractHeader == null) {
			if (other.abstractHeader != null)
				return false;
		} else if (!abstractHeader.equals(other.abstractHeader))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("{\n\tabTitle:" + title + "\n\tabAbstractHeader: " + abstractHeader + 
				"\n\tabBody: " + body + "\n\tabAuthor: " + author.getName() +"\n}");
	}
	
	
}
