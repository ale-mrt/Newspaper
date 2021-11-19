package org.mutasa.Newspaper.entities;

import java.sql.Date;

import com.generation.utility.entities.Entity;

public class Comment extends Entity{
	private String text;
	private Date date;
	private int likes;
	private int idAuthor;
	private int idArticle;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	@Override
	public String toString() {
		return "Comment [text=" + text + ", date=" + date + ", likes=" + likes + ", idAuthor=" + idAuthor
				+ ", idArticle=" + idArticle + "]\n\n";
	}
}
