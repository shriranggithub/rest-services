package com.shrirang.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class UserDaoService {

	private static int userCounter = 0;
	private static List<User> users = new ArrayList<>();
	static {
		users.add(new User(++userCounter, "Shree", LocalDate.now().minusYears(30)));
		users.add(new User(++userCounter, "Ram", LocalDate.now().minusYears(28)));
		users.add(new User(++userCounter, "Shyam", LocalDate.now().minusYears(26)));
		users.add(new User(++userCounter, "Rahul", LocalDate.now().minusYears(32)));

	}

	public List<User> findAll() {

		return users;
	}

	public User save(User user) {
		user.setId(++userCounter);
		users.add(user);
		return user;
	}

	public User findOne(int Id) {

		Predicate<? super User> predicate = user -> user.getId().equals(Id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteById(int Id) {

		Predicate<? super User> predicate = user -> user.getId().equals(Id);
		users.removeIf(predicate);
		
	}
}
