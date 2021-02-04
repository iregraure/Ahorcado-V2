package com.ahorcado.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ahorcado.security.model.User;
import com.ahorcado.security.model.dto.UserDTO;
import com.ahorcado.security.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@PostMapping
	private ResponseEntity<User> createUser(@RequestBody UserDTO dto)
	{
		try
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
