package com.springudemy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springudemy.model.User;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String home() {
		return "Home Controller";
	}

	@GetMapping("/user")
	public User getUser() {
		User user = new User();
		user.setId("1");
		user.setEmailId("himanshu@gmail.com");
		user.setName("Himanshu");

		return user;

	}

	@GetMapping("/{id}")

	public String pathVariable(@PathVariable String id) {
		return "The path variable is: " + id;

	}

	@GetMapping("/requestparam")
	public String requestParam(@RequestParam String name,
			@RequestParam(name = "email",required = false,defaultValue = "") String emailId) {

		return "Request parameters: "+name+" & EmailId: "+emailId;
	}

}
