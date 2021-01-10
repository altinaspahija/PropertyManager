package net.codejava.service.impl;

import net.codejava.dto.PropertyDto;
import net.codejava.dto.UserDto;
import net.codejava.model.Property;
import net.codejava.model.User;
import net.codejava.repository.UserRepository;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUserByUserId(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser==null) return null;
        return UserDto.getUserDto(optionalUser.get());
    }


    @Override
    public UserDto updateUserByUserId(UserDto userDto, Integer userId) {
        User user = UserDto.getUser(userDto);
        User retUser = userRepository.save(user);
        return UserDto.getUserDto(retUser);
    }

    @Override
    public boolean deleteUserByUserId(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return false;
        userRepository.delete(optionalUser.get());
        return true;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.getUsers();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach(user -> userDtos.add(UserDto.getUserDto(user)));
        return userDtos;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = UserDto.getUser(userDto);
        userRepository.saveAndFlush(user);
        return UserDto.getUserDto(user);
    }

}
