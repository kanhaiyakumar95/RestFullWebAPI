package net.javaguides.springboot_restfull_webservices.mapper;

import net.javaguides.springboot_restfull_webservices.dto.UserDto;
import net.javaguides.springboot_restfull_webservices.entity.User;

public class UserMapper {

    //Convert User JPA Entity into UserDto
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public static User maptToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }
}
