package com.example.CouponsExample.Web.RestControllers;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.CouponsExample.Entities.Service.ClientFacade;
import com.example.CouponsExample.Exceptions.Login.LoginFailedException;
import com.example.CouponsExample.Login.ClientType;
import com.example.CouponsExample.Login.LoginManager;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ClientController {

	@Autowired
	private LoginManager loginManager;

	@PostMapping("/login/{email}/{password}/{type}")
	public String login(@PathVariable String email, @PathVariable String password, @PathVariable ClientType type) {
		try {
			ClientFacade service = loginManager.login(email, password, type);
			if(service !=null) {
				String token = UUID.randomUUID().toString();
			
				return token;
			}
			return "Error: login failed";
		} catch (SQLException e) {
			return "Error: " + e.getMessage();
		} catch (LoginFailedException e) {
			return "Error: " + e.getMessage();
		} 
	}

	@PostMapping("/logout/{token}")
	public void logout(@PathVariable String token) {
		
	}
}