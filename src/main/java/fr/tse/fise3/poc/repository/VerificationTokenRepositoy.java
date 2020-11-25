package fr.tse.fise3.poc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.tse.fise3.poc.domain.VerificationToken;

public interface VerificationTokenRepositoy extends CrudRepository<VerificationToken, Long> {

	Optional<VerificationToken> findByToken(String token);

}
