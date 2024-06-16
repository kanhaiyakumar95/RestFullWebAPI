package net.javaguides.springboot_restfull_webservices.mapper;

import net.javaguides.springboot_restfull_webservices.dto.UserDto;
import net.javaguides.springboot_restfull_webservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

}
