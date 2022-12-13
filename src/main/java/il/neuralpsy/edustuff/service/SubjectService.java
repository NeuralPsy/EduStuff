package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.dto.SubjectDto;
import il.neuralpsy.edustuff.exception.NotFoundException;
import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class SubjectService {

    private final SubjectRepository subjectRepository;


    private final ModelMapper modelMapper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<SubjectDto> getAll() {
        return subjectRepository.findAll().stream().map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public SubjectDto getSubjectById(Integer subjectId) {
        return modelMapper.map(subjectRepository.findById(subjectId).get(), SubjectDto.class);
    }

    public SubjectDto addSubject(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        return modelMapper.map(subjectRepository.save(subject), SubjectDto.class);
    }


    public void removeSubject(Integer subjectId) {
        try {
            subjectRepository.deleteById(subjectId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("There is no subject with ID " + subjectId);
        }
    }
}
