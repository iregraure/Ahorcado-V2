package com.ahorcado.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ahorcado.security.model.User;
import com.ahorcado.security.model.dto.UserDTO;
import com.ahorcado.security.model.dto.UserDTOConverter;
import com.ahorcado.security.repo.UserRepository;

@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserDTOConverter converter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}
	
	public User createUser(UserDTO dto)
	{
		return repository.save(converter.fromUserDtoToUser(dto));
	}
	
}
