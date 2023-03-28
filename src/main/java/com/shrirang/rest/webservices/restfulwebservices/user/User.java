package com.shrirang.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="user_details")
public class User {

	@Id
	@GeneratedValue
	Integer Id;
	
	@Size(min=2, message = "name at least 2 character")
	String name;
	@Past
	LocalDate birthDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Post> post;
	
	protected User() {
		
	}
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		Id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
}
