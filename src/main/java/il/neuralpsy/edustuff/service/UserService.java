package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.exception.UserDoesntExistException;
import il.neuralpsy.edustuff.exception.UserEmailDoesntExistException;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer userId) {
        try {
            return userRepository.findById(userId);
        } catch (NoSuchElementException e){
            throw new UserDoesntExistException("There is no user with ID "+userId);
        }
    }

    public Optional<User> findUserByEmail(String email) {
        boolean doesExist = userRepository.existsByEmail(email);
        if (!doesExist) throw new UserEmailDoesntExistException("There is no user with email "+email+" or you did a typo");
        return userRepository.findUserByEmail(email);
    }

    public boolean updateUser(User user) {
        userRepository.update(user.getName(), user.getEmail(), user.getBirthdate(), user.getUserId());
        return true;
    }

    public boolean removeUser(Integer userId) {
        userRepository.deleteById(userId);
        return true;
    }

    public Collection<User> gelAll() {
        return userRepository.findAll();
    }

    public Collection<User> getAllByUserType(Integer userTypeId) {
        return userRepository.findAllByUserType_UserTypeId(userTypeId);
    }
}
