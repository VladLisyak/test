package com.lisyak.service;


import com.lisyak.model.User;

import java.util.List;

public interface UserService {
	
	public void addUser(User user);
	public void updateUser(User user);
	public User getUser(int id);
	public void deleteUser(int id);
	public List<User> getUser();

}
