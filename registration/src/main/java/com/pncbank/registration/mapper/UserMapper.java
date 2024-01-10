package com.pncbank.registration.mapper;

import com.pncbank.registration.dto.UserDto;
import com.pncbank.registration.entity.User;

/**
 * Utility class for mapping between User and UserDto entities.
 */
public class UserMapper {

    /**
     * Maps a User entity to a UserDto.
     *
     * @param user    The User entity to be mapped.
     * @param userDto The target UserDto to which the mapping is performed.
     * @return A UserDto with attributes mapped from the provided User entity.
     */
    public static UserDto mapToUserDto(User user, UserDto userDto) {
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setIpAddress(user.getIpAddress());

        return userDto;
    }

    /**
     * Maps a UserDto to a User entity.
     *
     * @param userDto The UserDto to be mapped.
     * @param user    The target User entity to which the mapping is performed.
     * @return A User entity with attributes mapped from the provided UserDto.
     */
    public static User mapToUser(UserDto userDto, User user) {
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setIpAddress(userDto.getIpAddress());
        return user;
    }
}
