package org.mutasa.Newspaper.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mutasa.Newspaper.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.utility.dao.Database;

/**
 * The DAOComment class implements the CRUD methods for the newspaper's comments
 * @author alemu
 */
public class DAOComment {
	//initializing the database with the autowired annotation
	@Autowired
	private Database db;
	
	//initializing the context with the autowired annotation
	@Autowired
	private ApplicationContext context;
	
	/**
	 * CREATE: inserts a new comment, passed as input, into the database, using the update method of the Database class
	 * @param c, the new comment to be inserted into the database
	 * @return for debugging purposes, if the creation is succesfull create() returns a boolean value
	 */
	public boolean create(Comment c) {
		boolean response = false;
		
		//insert query with wildcards
		String query = "insert into comments(text, date, likes, idAuthor, idArticle) values(?, ?, ?, ?, ?)";
		//using db.update() to insert the comment c into the database
		response = db.update(query, c.getText(), c.getDate()+"", c.getLikes()+"", c.getIdAuthor()+"", c.getIdArticle()+"");
		
		return response;
	}
	
	/**
	 * READ: returns a list of comments from the database with the update method of the Database class
	 * @param query, the selection query
	 * @param params the parameters passed for the wildcards in the query
	 * @return the list of comments
	 */
	public List<Comment> read(String query, String... params) {
		//initializing the return array
		List<Comment> response = new ArrayList<>();
		
		//using the rows method of the Database class to get all the entries in a Map configuration
		for(Map<String, String> row: db.rows(query, params)){
			//using the article() bean to make a new comment, passing the database row
			Comment c = (Comment) context.getBean("comment", row);
			response.add(c);
		}
		
		return response;
	}
	
	/**
	 * UPDATE: updates a certain comment, using its id to determine which comment to update
	 * @param c, the updated comment
	 * @return for debugging purposes, if the creation is succesfull, update() returns a boolean value
	 */
	public boolean update(Comment c) {
		boolean response = false;
		
		//update query with wildcards: it will modify the content of a row in the comments table where the id int the id column matches the id of the comment
		String query = "update comments set text = ?, date = ?, likes = ?, idAuthor = ?, idArticle = ? where id = ?";
		//using db.update() to update the comment value
		response = db.update(query, c.getText(), c.getDate()+"", c.getLikes()+"", c.getIdAuthor()+"", c.getIdArticle()+"", c.getId()+"");
		
		return response;
	}
	
	/**
	 * DELETE BY COMMENT: deletes a certain comment, using its id to determine which comment to delete
	 * @param c, the to-be-deleted comment
	 * @return for debugging purposes, if the deletion is succesfull, deleteByComment() returns a boolean value
	 */
	public boolean deleteByComment(Comment c) {
		boolean response = false;
		
		//delete query with wildcards: it deletes the user that matches the comment id
		String query = "delete from comments where id = ?";
		response = db.update(query, c.getId()+"");
		
		return response;
	}
	
	/**
	 * DELETE BY ID: deletes the comment by an id
	 * @param id, the id of the comment that must be deleted from the database
	 * @return for debugging purposes, if the deletion is succesfull, deleteById() returns a boolean value
	 */
	public boolean deleteById(int id) {
		boolean response = false;
		
		String query = "delete from comments where id = ?";
		response = db.update(query, id+"");
		
		return response;
	}
	
	/**
	 * getComments() returns the list of all comments taken from the database
	 * @return the list of all comments
	 */
	public List<Comment> getComments() {
		return read("select * from comments");
	}
	
	/**
	 * getCommentById() returns a comment given an id value
	 * @param id, the id of the comment to get and return
	 * @return the comment with the matching id
	 */
	public Comment getCommentById(int id) {
		//using read, the program returns a list: in order to return a single comment, the program must get the first item of said list
		Comment c = read("select * from comments where id = ?", id+"").get(0);
		
		//if the read method returned null then a will be null; if the read method was succesfull c must be the comment that matches the id passed as input
		return c;
	}
	
	/**
	 * gets all the comments from an article, identified with its id
	 * @param id, the article's id
	 * @return the list of all comments below an article
	 */
	public List<Comment> getArticleComments(int id){
		List<Comment> response = new ArrayList<>();
		
		response = read("select comments.* from comments join articles on comments.idArticle = articles.id where articles.id = ?", id+"");
		
		return response;
	}
	
	/**
	 * gets all the comments from a user, identified with his/her id
	 * @param id, the user's id
	 * @return the list of all comments typed by a user
	 */
	public List<Comment> getUserComments(int id){
		List<Comment> response = new ArrayList<>();
		
		response = read("select comments.* from comments join users on comments.idAuthor = users.id where users.id = ?", id+"");
		
		return response;
	}
}
