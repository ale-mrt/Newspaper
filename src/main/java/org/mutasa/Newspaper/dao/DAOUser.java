package org.mutasa.Newspaper.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mutasa.Newspaper.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.utility.dao.Database;

/**
 * The DAOUser class implements the CRUD methods for the newspaper's users
 * @author alemu
 */
public class DAOUser {
	//initializing the database with the autowired annotation
	@Autowired
	private Database db;
	
	//initializing the context with the autowired annotation
	@Autowired
	private ApplicationContext context;
	
	/**
	 * CREATE: inserts a new user, passed as input, into the database, using the update method of the Database class
	 * @param u, the new user to be inserted into the database
	 * @return for debugging purposes, if the creation is succesfull create() returns a boolean value
	 */
	public boolean create(User u) {
		boolean response = false;
		
		//insert query with wildcards
		String query = "insert into users(name, username, password, email, imgURL, tagline, isAuthor) values (?, ?, ?, ?, ?, ?, ?)";
		//using db.update() to insert the user u into the database
		response = db.update(query, u.getName(), u.getUsername(), u.getPassword(), u.getEmail(), u.getImgURL(), u.getTagline(), u.getAuthor()+"");
		
		return response;
	}
	
	/**
	 * READ: returns a list of users from the database with the update method of the Database class
	 * @param query, the selection query
	 * @param params the parameters passed for the wildcards in the query
	 * @return the list of users
	 */
	public List<User> read(String query, String... params) {
		//initializing the return array
		List<User> response = new ArrayList<>();
		
		//using the rows method of the Database class to get all the entries in a Map configuration
		for(Map<String, String> row: db.rows(query, params)){
			//using the article() bean to make a new user, passing the database row
			User u = (User) context.getBean("user", row);
			//adding the newly made user to the response array
			response.add(u);
		}
		
		return response;
	}
	
	/**
	 * UPDATE: updates a certain user, using its id to determine which comment to update
	 * @param c, the updated comment
	 * @return for debugging purposes, if the creation is succesfull, update() returns a boolean value
	 */
	public boolean update(User u) {
		boolean response = false;
		
		//update query with wildcards: it will modify the content of a row in the users table where the id int the id column matches the id of the user
		String query = "update users set name = ?, username = ?, password = ?, email = ?, imgURL = ?, tagline = ?, isauthor = ? where id = ?";
		//using db.update() to update the article value
		response = db.update(query, u.getName(), u.getUsername(), u.getPassword(), u.getEmail(), u.getImgURL(), u.getTagline(), u.getAuthor()+"", u.getId()+"");
		
		return response;
	}
	
	/**
	 * DELETE BY USER: deletes a certain user, using its id to determine which user to delete
	 * @param u, the to-be-deleted user
	 * @return for debugging purposes, if the deletion is succesfull, deleteByUser() returns a boolean value
	 */
	public boolean deleteByUser(User u) {
		boolean response = false;
		
		//delete query with wildcards: it deletes the user that matches the user id
		String query = "delete from users where id = ?";
		response = db.update(query, u.getId()+"");
		
		return response;
	}
	
	/**
	 * DELETE BY ID: deletes a user by an id
	 * @param id, the id of the user that must be deleted from the database
	 * @return for debugging purposes, if the deletion is succesfull, deleteById() returns a boolean value
	 */
	public boolean deleteById(int id) {
		boolean response = false;
		
		String query = "delete from users where id = ?";
		response = db.update(query, id+"");
		
		return response;
	}
	
	/**
	 * getUsers() returns the list of all users taken from the database
	 * @return the list of all users
	 */
	public List<User> getUsers() {
		return read("select * from users");
	}
	
	/**
	 * getUserById() returns a user given an id value
	 * @param id, the id of the user to get and return
	 * @return the user with the matching id
	 */
	public User getUserById(int id) {
		return read("select * from users where id = ?", id+"").get(0);
	}
	
	/**
	 * getUserByUsernamePassword() returns a username/password combination
	 * @param username/password of the user
	 * @return the user with the matching username/password combination
	 */
	public User getUserByUsernamePassword(String username, String password){
		User u = read("select * from users where username = ? and password = ?", username, password).get(0);
		
		if(u == null) {
			return null;
		}else {
			return u;
		}
	}
}
