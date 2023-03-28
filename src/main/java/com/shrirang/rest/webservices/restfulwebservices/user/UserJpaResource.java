package com.shrirang.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shrirang.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.shrirang.rest.webservices.restfulwebservices.jpa.UserRepository;

@RestController
public class UserJpaResource {

	// @Autowired
	private UserDaoService userDaoService;
	private UserRepository userRepository;
	private PostRepository postRepository;

	
	public UserJpaResource(UserDaoService userDaoService,UserRepository userRepository, PostRepository postRepository) {
		super();
		this.userDaoService = userDaoService;
		this.userRepository=userRepository;
		this.postRepository=postRepository;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id) {
		
		
		 Optional<User> user=userRepository.findById(id);
		 if(user.isEmpty()) {
			 throw new UserNotFoundException("id:"+id);
		 }
		 
		 EntityModel<User> entityModel=EntityModel.of(user.get());
		 
		 WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retriveAllUsers());
		 entityModel.add(link.withRel("all-users"));
		 return entityModel;
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		 userRepository.deleteById(id);
		
	}
	

	
	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser=userRepository.save(user);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping(path = "/jpa/users/{id}/post")
	public List<Post> retriveUserPost(@PathVariable int id) {
		
		 Optional<User> user=userRepository.findById(id);
		 if(user.isEmpty()) {
			 throw new UserNotFoundException("id:"+id);
		 }
		return user.get().getPost();
		
	}
	
	
	@GetMapping(path = "/jpa/users/{id}/post/{postId}")
	public Post retriveUserPostById(@PathVariable int id, @PathVariable int postId) {
		
		 Optional<User> user=userRepository.findById(id);
		 if(user.isEmpty()) {
			 throw new UserNotFoundException("id:"+id);
		 }
		 
		 
		 
		return postRepository.getById(postId);
		
	}
	
	@PostMapping(path = "/jpa/users/{id}/post")
	public ResponseEntity<Post> createUserPost(@PathVariable int id, @RequestBody Post post) {
		
		
		 Optional<User> user=userRepository.findById(id);
		 if(user.isEmpty()) {
			 throw new UserNotFoundException("id:"+id);
		 }
		 
		 post.setUser(user.get());
		 Post savedPost=postRepository.save(post);
		 URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
			return ResponseEntity.created(location).build();
		 
	}
	
}
