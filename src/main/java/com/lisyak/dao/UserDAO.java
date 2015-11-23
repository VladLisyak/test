package com.lisyak.dao;

import com.lisyak.model.User;

import java.util.List;


public interface UserDAO {
	
	public void addUser(User user);
	public void updateUser(User user);
	public User getUser(int id);
	public void deleteUser(int id);
	public List<User> getUsers();

}
