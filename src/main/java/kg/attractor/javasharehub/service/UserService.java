package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.dto.UserDto;

public interface UserService {
    void registerUser(UserDto userDto);

    boolean checkUserExisting(String email);
}
