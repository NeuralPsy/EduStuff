package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.exception.UserDoesntExistException;
import il.neuralpsy.edustuff.exception.UserEmailDoesntExistException;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.UserRepository;
import il.neuralpsy.edustuff.repository.UserTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private UserTypeRepository userTypeRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder, UserTypeRepository userTypeRepository){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userTypeRepository = userTypeRepository;

    }

    public UserDto addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return mapUserToDto(userRepository.save(user));
    }

    public UserDto getUserById(Integer userId) {
        try {
            return mapUserToDto(userRepository.findById(userId).get());
        } catch (NoSuchElementException e){
            throw new UserDoesntExistException("There is no user with ID "+userId);
        }
    }

    public UserDto findUserByEmail(String email) {
        boolean doesExist = userRepository.existsByEmail(email);
        if (!doesExist) throw new UserEmailDoesntExistException("There is no user with email "+email+" or a typo was done");
        return mapUserToDto(userRepository.findUserByEmail(email).get());
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
        return userRepository.findAll().stream().map(user -> mapUserToDto(user))
                .collect(Collectors.toList());
    }

    public Collection<UserDto> getAllByUserType(Integer userTypeId) {
        return userRepository.findAllByUserType_UserTypeId(userTypeId).stream().map(user -> mapUserToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapUserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(userDto.getUserId());
        userDto.setUserType(user.getUserType());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setBirthdate(user.getBirthdate());

        return userDto;
    }
}
