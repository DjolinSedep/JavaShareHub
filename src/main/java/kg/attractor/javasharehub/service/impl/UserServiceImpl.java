package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.entity.Role;
import kg.attractor.javasharehub.entity.User;
import kg.attractor.javasharehub.repository.UserRepository;
import kg.attractor.javasharehub.service.RoleService;
import kg.attractor.javasharehub.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDto userDto) {
        log.info("Register user: {}", userDto);
        Role role = roleService.getRoleByName("ROLE_USER");
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public boolean checkUserExisting(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
