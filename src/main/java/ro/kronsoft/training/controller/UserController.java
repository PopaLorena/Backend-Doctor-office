package ro.kronsoft.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ro.kronsoft.training.dto.UserDto;
import ro.kronsoft.training.service.UserService;
import ro.kronsoft.training.transformer.UserTransformer;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserTransformer userTransformer;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDto> getUser(){
		return userService.getUsers().stream().map(userTransformer::toDto).collect(Collectors.toList());
		
	}
	
	@PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public  UserDto createUser(@Validated @RequestBody UserDto user) {
		return userTransformer.toDto(userService.createUser(userTransformer.toEntity(user)));
	}
	
	
	@PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE) 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@Validated @RequestBody UserDto user){
		userService.updateUser(userTransformer.toEntity(user));
	}
	
	@DeleteMapping(value = "/{id}/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {
		userService.delete(id);
	}
	
	
	
	
}

