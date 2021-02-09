package fr.tse.fise3.poc.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.tse.fise3.poc.domain.Role;
import fr.tse.fise3.poc.repository.RoleRepository;
import fr.tse.fise3.poc.service.RoleService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRespository;

	@Override
	public List<Role> findAllRoles() {
		return roleRespository.findAll();
	}
	
	

}
