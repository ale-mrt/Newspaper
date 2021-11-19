package org.mutasa.Newspaper.entities;

import java.sql.Date;

import com.generation.utility.entities.Entity;

public class Article extends Entity{
	private String title;
	private String text;
	private String tags;
	private String imgURL;
	private Date date;
	private int likes;
	private int idAuthor;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
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
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", text=" + text + ", tags=" + tags + ", imgURL=" + imgURL + ", date=" + date
				+ ", likes=" + likes + ", idAuthor=" + idAuthor + "]\n\n";
	}
}
