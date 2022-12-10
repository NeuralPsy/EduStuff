package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.SubjectDto;
import il.neuralpsy.edustuff.exception.SubjectDoesntExistException;
import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.SubjectRepository;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<SubjectDto> getAll() {
        return subjectRepository.findAll().stream().map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    public SubjectDto getSubjectById(Integer subjectId) {
        return modelMapper.map(subjectRepository.findById(subjectId).get(), SubjectDto.class);
    }

    public SubjectDto addSubject(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        return modelMapper.map(subjectRepository.save(subject), SubjectDto.class);
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
