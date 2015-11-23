package com.lisyak.controller;

import com.lisyak.model.User;
import com.lisyak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;


	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List welcome() {

		List<User> users = userService.getUser();

		return users;
	}

	@RequestMapping(method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String,Object> addingUser(@RequestBody User user) {
		user.setCreatedDate(new Date(new java.util.Date().getTime()));
		userService.addUser(user);
		Map<String, Object> result = new HashMap<>();
		String message = "User was successfully added.";
		result.put("message", message);

		return result;
	}
	
	/*@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<User> listOfUsers() {

		List<User> users = userService.getUser();

		return users;
	}*/
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT, produces = "application/json")
	public @ResponseBody Map<String, Object> editingUser(@RequestBody User user, @PathVariable Integer id) {
		
		userService.updateUser(user);
		
		String message = "User was successfully edited.";

		Map<String, Object> result = new HashMap<>();
		result.put("message", message);

		return result;
	}

	@RequestMapping(value="{id}", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody User editingUser(@PathVariable Integer id) {

		User usr = userService.getUser(id);


		return usr;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody String deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		String message = "User was successfully deleted.";
		return message;
	}

}
