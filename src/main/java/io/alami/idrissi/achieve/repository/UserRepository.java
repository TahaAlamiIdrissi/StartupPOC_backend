package io.alami.idrissi.achieve.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.alami.idrissi.achieve.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
