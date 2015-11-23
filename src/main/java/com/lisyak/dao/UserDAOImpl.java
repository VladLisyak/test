package com.lisyak.dao;

import com.lisyak.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addUser(User user) {
		getCurrentSession().save(user);
	}

	public void updateUser(User user) {
		User userToUpdate = getUser(user.getId());
		userToUpdate.setName(user.getName());
		userToUpdate.setAge(user.getAge());
		userToUpdate.setIsAdmin(user.getIsAdmin());
		userToUpdate.setCreatedDate(user.getCreatedDate());
		getCurrentSession().update(userToUpdate);
		
	}

	public User getUser(int id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}

	public void deleteUser(int id) {
		User user = getUser(id);
		if (user != null)
			getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return getCurrentSession().createQuery("from User").list();
	}

}
