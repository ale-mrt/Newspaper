package org.mutasa.Newspaper.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mutasa.Newspaper.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.utility.dao.Database;

/**
 * The DAOArticle class implements the CRUD methods for the newspaper's articles
 * @author alemu
 */
public class DAOArticle {
	//initializing the database with the autowired annotation
	@Autowired
	private Database db;
	
	//initializing the context with the autowired annotation
	@Autowired
	private ApplicationContext context;
	
	/**
	 * CREATE: inserts a new article, passed as input, into the database, using the update method of the Database class
	 * @param a, the new article to be inserted into the database
	 * @return for debugging purposes, if the creation is succesfull create() returns a boolean value
	 */
	public boolean create(Article a) {
		boolean response = false;
		
		//insert query with wildcards
		String query = "insert into articles(title, text, tags, imgURL, date, likes, idAuthor) values(?, ?, ?, ?, ?, ?, ?)";
		//using db.update() to insert the article a into the database
		response = db.update(query, a.getTitle(), a.getText(), a.getTags(), a.getImgURL(), a.getDate()+"", a.getLikes()+"", a.getIdAuthor()+"");
		
		return response;
	}
	
	/**
	 * READ: returns a list of articles from the database with the update method of the Database class
	 * @param query, the selection query
	 * @param params the parameters passed for the wildcards in the query
	 * @return the list of articles
	 */
	public List<Article> read(String query, String... params) {
		//initializing the return array
		List<Article> response = new ArrayList<>();
		
		//using the rows method of the Database class to get all the entries in a Map configuration
		for(Map<String, String> row: db.rows(query, params)){
			//using the article() bean to make a new article, passing the database row
			Article a = (Article) context.getBean("article", row);
			//adding the newly made article to the response array
			response.add(a);
		}
		
		return response;
	}
	
	/**
	 * UPDATE: updates a certain article, using its id to determine which article to update
	 * @param a, the updated article
	 * @return for debugging purposes, if the creation is succesfull, update() returns a boolean value
	 */
	public boolean update(Article a) {
		boolean response = false;
		
		//update query with wildcards: it will modify the content of a row in the articles table where the id int the id column matches the id of the article
		String query = "update articles set title = ?, text = ?, tags = ?, imgURL = ?, date = ?, likes = ?, idAuthor = ? where id = ?";
		//using db.update() to update the article value
		response = db.update(query, a.getTitle(), a.getText(), a.getTags(), a.getImgURL(), a.getDate()+"", a.getLikes()+"", a.getIdAuthor()+"", a.getId()+"");
		
		return response;
	}
	
	/**
	 * DELETE BY ARTICLE: deletes a certain article, using its id to determine which article to delete
	 * @param a, the to-be-deleted article
	 * @return for debugging purposes, if the deletion is succesfull, deleteByArticle() returns a boolean value
	 */
	public boolean deleteByArticle(Article a) {
		boolean response = false;
		
		//delete query with wildcards: it deletes the article that matches the article id
		String query = "delete from articles where id = ?";
		response = db.update(query, a.getId()+"");
		
		return response;
	}
	
	/**
	 * DELETE BY ID: deletes the article 
	 * @param id, the id to the article that must be deleted from the database
	 * @return for debugging purposes, if the deletion is succesfull, deleteById() returns a boolean value
	 */
	public boolean deleteById(int id) {
		boolean response = false;
		
		//delete query with wildcards: it deletes the article that matches the id passed as input
		String query = "delete from articles where id = ?";
		response = db.update(query, id+"");
		
		return response;
	}
	
	/**
	 * getArticles() returns the list of all articles taken from the database
	 * @return the list of all articles
	 */
	public List<Article> getArticles() {
		return read("select * from articles");
	}
	
	/**
	 * getArticleById() returns an article given an id value
	 * @param id, the id of the article to get and return
	 * @return the article with the matching id
	 */
	public Article getArticleById(int id) {
		//using read, the program returns a list: in order to return a single article, the program must get the first item of said list
		Article a = read("select * from articles where id = ?", id+"").get(0);
		
		//if the read method returned null then a will be null; if the read method was succesfull a must be the article that matches the id passed as input
		return a;
	}
	
	/**
	 * gets all the articles from a user, identified with his/her id
	 * @param id, the user's id
	 * @return the list of all articles typed by a user
	 */
	public List<Article> getUserArticles(int id){
		List<Article> response = new ArrayList<>();
		
		response = read("select articles.* from articles join users on articles.idAuthor = users.id where users.id = ?", id+"");
		
		return response;
	}
}
