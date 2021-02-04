package com.ahorcado.security.model.dto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ahorcado.security.model.User;

public class UserDTOConverter
{
	@Autowired
	private PasswordEncoder passEncoder;
	
	public User fromUserDtoToUser(UserDTO dto)
	{
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPass(passEncoder.encode(dto.getPass()));
		user.setRole(dto.getRole());
		user.setCreatedTime(LocalDateTime.now());
		user.setUpdatedTime(LocalDateTime.now());
		return user;
	}
	
	public UserDTO fromUserToUserDto(User user)
	{
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		dto.setRole(user.getRole());
		// setPass??
		return dto;
	}
}
