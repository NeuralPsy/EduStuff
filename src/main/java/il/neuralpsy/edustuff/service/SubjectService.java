package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.SubjectDto;
import il.neuralpsy.edustuff.exception.SubjectDoesntExistException;
import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.SubjectRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Component
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final UserRepository userRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    public Collection<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Integer subjectId) {
        return subjectRepository.findById(subjectId);
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public boolean addSubjectToUser(Integer subjectId, Integer userId) {
        User user = userRepository.findById(userId).get();
        subjectRepository.addSubjectToUser(user, subjectId);
        return true;
    }

    public Collection<Subject> getSubjectsByUserId(Integer userId) {
        return subjectRepository.findSubjectsByUserUserId(userId);
    }

    public boolean removeSubject(Integer subjectId) {
        try {
            subjectRepository.deleteById(subjectId);
        } catch (EmptyResultDataAccessException e) {
            throw new SubjectDoesntExistException("There is no subject with ID " + subjectId);
        }
        return true;
    }
}
