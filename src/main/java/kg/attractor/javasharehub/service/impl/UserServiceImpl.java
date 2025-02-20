package kg.attractor.javasharehub.service.impl;

import kg.attractor.javasharehub.dto.FileDto;
import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.entity.Role;
import kg.attractor.javasharehub.entity.User;
import kg.attractor.javasharehub.repository.UserRepository;
import kg.attractor.javasharehub.service.FileService;
import kg.attractor.javasharehub.service.RoleService;
import kg.attractor.javasharehub.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, @Lazy FileService fileService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
    }

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
    public User getUserByEmail(String email) {
        log.info("Get user by email: {}", email);
        return userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        log.info("Get userDto by email: {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found"));
        return convertToUserDto(user);
    }

    @Override
    public boolean checkUserExisting(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserDto convertToUserDto(User user){
        List<FileDto> fileDtoList = user.getFiles().stream()
                .map(fileService::convertToFileDto)
                .toList();
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .files(fileDtoList)
                .build();
    }
}
