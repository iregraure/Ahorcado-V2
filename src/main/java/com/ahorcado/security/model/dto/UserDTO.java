package com.ahorcado.security.model.dto;

import com.ahorcado.security.model.UserRole;

public class UserDTO
{
	private String username;
	
	private String pass;
	
	private UserRole role;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPass()
	{
		return pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}

	public UserRole getRole()
	{
		return role;
	}

	public void setRole(UserRole role)
	{
		this.role = role;
	}
}
