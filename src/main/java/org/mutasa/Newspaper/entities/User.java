package org.mutasa.Newspaper.entities;

import java.sql.Date;

import com.generation.utility.entities.Entity;

public class User extends Entity{
	private String name;
	private String username;
	private String password;
	private String email;
	private String imgURL;
	private String tagline;
	private boolean author;
	private Date registerDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public boolean getAuthor() {
		return author;
	}
	public void setAuthor(boolean isAuthor) {
		this.author = isAuthor;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", imgURL=" + imgURL + ", tagline=" + tagline + ", isAuthor=" + author + "]\n\n";
	}
	
}