package com.example.EmployeeData.controller;

import org.aspectj.apache.bcel.classfile.Module.Uses;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.EmployeeData.dataEntities.User;
import com.example.EmployeeData.repo.UserRepository;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repo;
	private Object User;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
		
	}
	
	@GetMapping("/register")
	public String showSignUpFrom(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
		
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 String encodedPassword = passwordEncoder.encode(user.getPassword());
		 user.setPassword(encodedPassword);
		repo.save(user);
		
		return "register_success";
		
	}
	
	@GetMapping("/users")
	public String viewUsersList(Model model) {
	    java.util.List<User> listUsers = repo.findAll();
	    model.addAttribute("listUsers", listUsers);
	    return "users";
	}
	
	@DeleteMapping("/DeleteUserById/{id}")
	public Boolean DeleteUserById(@PathVariable(value ="id") Long id) {
		return true;
		
		
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateCustomer(@RequestBody User user) {
		try {
			return new ResponseEntity<User>(repo.save(user), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
	
	