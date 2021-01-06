package fr.tse.fise3.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.fise3.poc.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
