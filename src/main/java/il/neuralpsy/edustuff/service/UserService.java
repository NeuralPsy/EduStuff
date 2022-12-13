package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.UserDto;
import il.neuralpsy.edustuff.dto.UserRegistrationDto;
import il.neuralpsy.edustuff.exception.NotFoundException;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.model.UserType;
import il.neuralpsy.edustuff.repository.UserRepository;
import il.neuralpsy.edustuff.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserTypeRepository userTypeRepository;


    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder, UserTypeRepository userTypeRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userTypeRepository = userTypeRepository;

    }

    public void addUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(userRegistrationDto.getBirthdate(), formatter);
        UserType userType = userTypeRepository.findByName(userRegistrationDto.getUserType());
        user.setName(userRegistrationDto.getName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setUserType(userType);
        user.setBirthdate(birthdate);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        userRepository.save(user);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public UserDto getUserById(Integer userId) {
        try {
            return mapUserToDto(userRepository.findById(userId).get());
        } catch (NoSuchElementException e){
            throw new NotFoundException("There is no user with ID "+userId);
        }
    }

    public UserDto findUserByEmail(String email) {
        if (userRepository.findUserByEmail(email).isEmpty()) {
            throw new NotFoundException("User with email " + email + " doesn't exist");
        }
        return mapUserToDto(userRepository.findUserByEmail(email).get());
    }

    public boolean checkEmailExistence(String email){
        return userRepository.existsByEmail(email);
    }

    public void updateUser(UserDto userDto) {
        userRepository.update(userDto.getName(), userDto.getEmail(), userDto.getBirthdate(), userDto.getUserId());
    }

    public void removeUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public Collection<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapUserToDto)
                .collect(Collectors.toList());
    }

    public Collection<UserDto> getAllByUserType(Integer userTypeId) {
        return userRepository.findAllByUserType_UserTypeId(userTypeId)
                .stream()
                .map(this::mapUserToDto)
                .collect(Collectors.toList());
    }

    private UserDto mapUserToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserType(user.getUserType());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setBirthdate(user.getBirthdate());

        return userDto;
    }
}
