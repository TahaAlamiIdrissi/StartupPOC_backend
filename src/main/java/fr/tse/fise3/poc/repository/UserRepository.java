package fr.tse.fise3.poc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.tse.fise3.poc.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
