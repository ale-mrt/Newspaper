package org.mutasa.Newspaper.controllers;

import javax.servlet.http.HttpSession;

import org.mutasa.Newspaper.dao.DAOUser;
import org.mutasa.Newspaper.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private DAOUser du;
	
	@RequestMapping("/")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		
		return "/";
	}
	
	@PostMapping("/userLogin")
	@ResponseBody
	public String login(@RequestBody MultiValueMap<String, String> userInput, HttpSession session) {
		String username = userInput.getFirst("username");
		String password = userInput.getFirst("password");
		session.setAttribute("user", du.getUserByUsernamePassword(username, password));
		Gson gson = new Gson();
		
		return gson.toJson(session.getAttribute("user"));
	}
	
	@GetMapping("/getUser")
	@ResponseBody
	public String getUser(HttpSession session) {
		Gson gson = new Gson();
		User u = (User) session.getAttribute("user");
		
		return gson.toJson(u);
	}
}
