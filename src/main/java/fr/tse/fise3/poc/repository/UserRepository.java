package fr.tse.fise3.poc.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import fr.tse.fise3.poc.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	

	public Optional<User> findByUsername(String username);	
	
	
	public List<User> findByManagerUserId(Long managerId);
	
	
	public List<User> findByEnabled(boolean enabled);
	
	
	public List<User> findAllByManagerUserId(Long userId);


}

