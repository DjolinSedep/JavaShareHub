package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.entity.Role;
import kg.attractor.javasharehub.repository.RoleRepository;
import kg.attractor.javasharehub.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String roleName){
        return roleRepository.findByName(roleName).orElseThrow(() -> new NoSuchElementException("Role not found"));
    }
}
