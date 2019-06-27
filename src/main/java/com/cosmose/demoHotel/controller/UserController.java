package com.cosmose.demoHotel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmose.demoHotel.json.ResultGen;
import com.cosmose.demoHotel.model.User;
import com.cosmose.demoHotel.service.UserService;
import com.google.gson.Gson;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index() {
		return "hello";
	}
	
	@RequestMapping("/add")
	public String registerCustomer(@RequestBody String jsonUserInfo) {
		User user = new Gson().fromJson(jsonUserInfo, User.class);
		return ResultGen.resultJsonGen(userService.addUser(user));
	}
	
}
