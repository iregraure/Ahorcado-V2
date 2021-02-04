package com.ahorcado.security.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahorcado.security.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
	public Optional<User> findByUsername(String username);
}
