package net.codejava.service.impl;

import net.codejava.dto.PropertyDto;
import net.codejava.dto.UserDto;
import net.codejava.exception.UserNotFoundException;
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
    public UserDto getUserByUserId(Integer userId) throws UserNotFoundException{
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser==null) throw new UserNotFoundException();
        return UserDto.getUserDto(optionalUser.get());
    }


    @Override
    public UserDto updateUser(int userId, UserDto userDto) throws UserNotFoundException {
        Optional<User> optionUser = userRepository.findById(userId);
        if (!optionUser.isPresent()) throw new UserNotFoundException();
        User user = optionUser.get();
        user.setFullname(userDto.getFullname());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRoleDescription(userDto.getRoleDescription());

        userRepository.save(user);
        return UserDto.getUserDto(user);
        /*User user = UserDto.getUser(userDto);
        User retUser = userRepository.save(user);
        return UserDto.getUserDto(retUser);*/
    }

    @Override
    public boolean deleteUserByUserId(Integer userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) throw new UserNotFoundException();
        userRepository.delete(optionalUser.get());
        return true;
    }

    @Override
    public List<UserDto> getUsers() throws UserNotFoundException{
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        if(users.isEmpty())throw new UserNotFoundException();
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
