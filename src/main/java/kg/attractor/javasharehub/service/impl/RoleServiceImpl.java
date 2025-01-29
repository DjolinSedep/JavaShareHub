package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.entity.Role;
import kg.attractor.javasharehub.repository.RoleRepository;
import kg.attractor.javasharehub.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String roleName){
        return roleRepository.findByName(roleName).orElseThrow(() -> new NoSuchElementException("Role not found"));
    }
}
