package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.exception.UserEmailDoesntExistInDB;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
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

    public Optional<User> getUserById(Integer studentId) {
        return userRepository.findById(studentId);
    }

    public Optional<User> findUserByEmail(String email) {
        boolean isValid = userRepository.existsByEmail(email);
        if (!isValid) throw new UserEmailDoesntExistInDB("There is no user with email "+email+" or you did a typo");
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
}
