package io.alami.idrissi.achieve.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.alami.idrissi.achieve.domain.VerificationToken;

public interface VerificationTokenRepositoy extends CrudRepository<VerificationToken, Long> {

	Optional<VerificationToken> findByToken(String token);

}
