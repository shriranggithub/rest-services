package com.shrirang.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Post {

	@Id
	@GeneratedValue
	Integer Id;
	
	@Size(min=10)
	String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	public Post() {
		
	}
	public Post(Integer id, String description) {
		super();
		Id = id;
		this.description = description;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Post [Id=" + Id + ", description=" + description + "]";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
