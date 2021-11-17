package org.mutasa.Newspaper.controllers;

import org.mutasa.Newspaper.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

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
	public String emptyHome() {
		return "/home.html";
	}
	
	/**
	 * when the user types www.domain.com/home he will be redirected to the home.html file
	 * @return home.html
	 */
	@GetMapping("/home")
	public String home() {
		
		return "/home.html";
	}
	
	/**
	 * when the user types www.domain.com/home.html he will be redirected to the home.html file
	 * @return home.html
	 */
	@GetMapping("/home")
	public String HTMLjome() {
		
		return "/home.html";
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY: returns the list of all users on a black page
	 * @return a json string of all users
	 */
	@GetMapping("/users")
	@ResponseBody
	public String getUsers() {
		Gson gson = new Gson();
		
		return gson.toJson(du.getUsers());
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY: returns the list of all articles on a blank page
	 * @return a json string of all articles
	 */
	@GetMapping("/articles")
	@ResponseBody
	public String getArticles() {
		Gson gson = new Gson();
		
		return gson.toJson(da.getArticles());
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY: returns the list of all comments on a blank page
	 * @return a json string of all comments
	 */
	@GetMapping("/comments")
	@ResponseBody
	public String getComments() {
		Gson gson = new Gson();
		
		return gson.toJson(dc.getComments());
	}
}
