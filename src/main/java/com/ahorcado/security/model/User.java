package com.ahorcado.security.model;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String username;
	
	private String pass;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@CreatedDate
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	private LocalDateTime updatedTime;
	
	private LocalDateTime deletedTime;
	
	private static final long serialVersionUID = 390528776862166898L;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		// Que devuelvo si solo tengo un rol?
		return null;
	}

	@Override
	public String getPassword()
	{
		return this.pass;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return this.getDeletedTime() == null;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return false;
	}

	@Override
	public boolean isEnabled()
	{
		return false;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
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

	public LocalDateTime getCreatedTime()
	{
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime)
	{
		this.createdTime = createdTime;
	}

	public LocalDateTime getUpdatedTime()
	{
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime)
	{
		this.updatedTime = updatedTime;
	}

	public LocalDateTime getDeletedTime()
	{
		return deletedTime;
	}

	public void setDeletedTime(LocalDateTime deletedTime)
	{
		this.deletedTime = deletedTime;
	}
	
}
