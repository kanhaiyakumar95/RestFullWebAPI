package net.javaguides.springboot_restfull_webservices.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot_restfull_webservices.dto.UserDto;
import net.javaguides.springboot_restfull_webservices.entity.User;
import net.javaguides.springboot_restfull_webservices.exception.EmailAlreadyExistsException;
import net.javaguides.springboot_restfull_webservices.exception.ResourceNotFoundException;
import net.javaguides.springboot_restfull_webservices.mapper.AutoUserMapper;
import net.javaguides.springboot_restfull_webservices.repository.UserRepository;
import net.javaguides.springboot_restfull_webservices.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //Convert UserDto into User JPA Entity
        //User user = UserMapper.maptToUser(userDto);

//        User user = modelMapper.map(userDto, User.class);

        userRepository.findByEmail(userDto.getEmail()).ifPresent(
                s -> {
                    throw new EmailAlreadyExistsException("Email Already Exists for the user");
                });

//        if(optionalUser.isPresent()) {
//            throw new EmailAlreadyExistsException("Email Already Exists for the user");
//        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        //Convert User JPA enitity to userDto
//        return UserMapper.mapToUserDto(userRepository.save(user));
//
        return AutoUserMapper.MAPPER.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
//        return UserMapper.mapToUserDto(optionalUser.get());
//        return modelMapper.map(optionalUser.get(), UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .toList();
//        return users.stream().map((user -> modelMapper.map(user, UserDto.class)))
//                .toList();
        return users.stream().map((AutoUserMapper.MAPPER::mapToUserDto))
                .toList();
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
//        return UserMapper.mapToUserDto(userRepository.save(existingUser));
        return AutoUserMapper.MAPPER.mapToUserDto(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
