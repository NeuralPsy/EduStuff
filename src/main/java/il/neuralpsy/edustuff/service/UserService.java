package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.exception.UserDoesntExistException;
import il.neuralpsy.edustuff.exception.UserEmailDoesntExistException;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    public UserDto getUserById(Integer userId) {
        try {
            return modelMapper.map(userRepository.findById(userId).get(), UserDto.class);
        } catch (NoSuchElementException e){
            throw new UserDoesntExistException("There is no user with ID "+userId);
        }
    }

    public UserDto findUserByEmail(String email) {
        boolean doesExist = userRepository.existsByEmail(email);
        if (!doesExist) throw new UserEmailDoesntExistException("There is no user with email "+email+" or a typo was done");
        return modelMapper.map(userRepository.findUserByEmail(email).get(), UserDto.class);
    }

    public boolean updateUser(UserDto userDto) {
        userRepository.update(userDto.getName(), userDto.getEmail(), userDto.getBirthdate(), userDto.getUserId());
        return true;
    }

    public boolean removeUser(Integer userId) {
        userRepository.deleteById(userId);
        return true;
    }

    public Collection<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public Collection<UserDto> getAllByUserType(Integer userTypeId) {
        return userRepository.findAllByUserType_UserTypeId(userTypeId).stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
