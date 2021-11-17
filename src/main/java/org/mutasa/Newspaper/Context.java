package org.mutasa.Newspaper;

import java.util.Map;

import org.mutasa.Newspaper.dao.*;
import org.mutasa.Newspaper.entities.*;
import org.springframework.context.annotation.*;

import com.generation.utility.dao.Database;

/**
 * The context class contains all the entities involved in the program, and the logic in which said entities are created and managed. Since the spring framework works with annotations,
 * here's a brief explanation of all the 
 * @author alemu
 */
@Configuration
public class Context {
	@Bean
	@Scope("prototype")
	@Primary
	public User user(Map<String, String> row) {
		User u = new User();
		u.fromMap(row);
		return u;
	}
	
	@Bean
	@Scope("prototype")
	@Primary
	public Article article(Map<String, String> row) {
		Article a = new Article();
		a.fromMap(row);
		return a;
	}
	
	@Bean
	@Scope("prototype")
	@Primary
	public Comment comment(Map<String, String> row) {
		Comment c = new Comment();
		c.fromMap(row);
		return c;
	}
	
	@Bean
	@Scope("singleton")
	public Database database() {
	    return new Database("newspaper", "root", "root");
	}
	
	@Bean
	@Scope("singleton")
	public DAOUser daoUser() {
		return new DAOUser();
	}
	
	@Bean
	@Scope("singleton")
	public DAOComment daoComment() {
		return new DAOComment();
	}
	
	@Bean
	@Scope("singleton")
	public DAOArticle daoArticle() {
		return new DAOArticle();
	}
}
