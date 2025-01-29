package kg.attractor.javasharehub.service;

import kg.attractor.javasharehub.dto.UserDto;
import kg.attractor.javasharehub.entity.User;

public interface UserService {
    void registerUser(UserDto userDto);

    UserDto getUserByEmail(String email);

    boolean checkUserExisting(String email);

    UserDto convertToUserDto(User user);
}
