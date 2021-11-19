package org.mutasa.Newspaper.controllers;

import javax.servlet.http.HttpSession;

import org.mutasa.Newspaper.dao.DAOArticle;
import org.mutasa.Newspaper.dao.DAOComment;
import org.mutasa.Newspaper.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private DAOComment dc;
	
	@Autowired
	private DAOArticle da;
	
	@RequestMapping("/")
	public String profile() {
		return "profile.html";
	}
	
	@PostMapping("/getArticles")
	@ResponseBody
	public String getArticles(HttpSession session) {
		Gson gson = new Gson();
		User u = (User) session.getAttribute("user");
		
		return gson.toJson(da.getUserArticles(u.getId()));
	}
	
	@PostMapping("/getComments")
	@ResponseBody
	public String getComments(HttpSession session) {
		Gson gson = new Gson();
		User u = (User) session.getAttribute("user");
		return gson.toJson(dc.getUserComments(u.getId()));
	}
}
