package org.mutasa.Newspaper.controllers;

import org.mutasa.Newspaper.dao.*;
import org.mutasa.Newspaper.entities.Article;
import org.mutasa.Newspaper.entities.Comment;
import org.mutasa.Newspaper.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The HomeController class is what the user sees when he/she contacts the website
 * @author alemu
 */
@Controller
public class HomeController {
	@Autowired
	DAOUser du;
	@Autowired
	DAOComment dc;
	@Autowired
	DAOArticle da;
	
	/**
	 * when the user types www.domain.com/ he will be redirected to the home.html file
	 * @return home.html
	 */
	@GetMapping("/")
	public String home() {
		return "/home.html";
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY: returns the list of all users on a black page
	 * @return a json string of all users
	 */
	@GetMapping("/users")
	@ResponseBody
	public String getUsers() {
		String response = "";
		
		for(User u: du.getUsers()) {
			response += u.toString();
		}
		
		return response;
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY: returns the list of all articles on a blank page
	 * @return a json string of all articles
	 */
	@GetMapping("/articles")
	@ResponseBody
	public String getArticles() {
		String response = "";
		
		for(Article a: da.getArticles()) {
			response += a.toString();
		}
		
		return response;
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY: returns the list of all comments on a blank page
	 * @return a json string of all comments
	 */
	@GetMapping("/comments")
	@ResponseBody
	public String getComments() {
		String response = "";
		
		for(Comment c: dc.getComments()) {
			response += c.toString();
		}
		
		return response;
	}
}
