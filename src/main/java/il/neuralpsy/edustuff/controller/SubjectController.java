package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.SubjectDto;
import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    private final ModelMapper modelMapper;

    @Autowired
    public SubjectController(SubjectService subjectService, ModelMapper modelMapper){
        this.subjectService = subjectService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Collection<SubjectDto> getAll(){
        return subjectService.getAll().stream().map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{subjectId}")
    public SubjectDto getSubjectById(@PathVariable Integer subjectId){
        return modelMapper.map(subjectService.getSubjectById(subjectId).get(), SubjectDto.class);
    }

    @PostMapping
    public SubjectDto addSubject(@RequestBody SubjectDto subjectDto){
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        return modelMapper.map(subjectService.addSubject(subject), SubjectDto.class);
    }

    @PutMapping("/{subjectId}/user/{userId}")
    public boolean addSubjectToUser(@PathVariable Integer subjectId, @PathVariable Integer userId){
        return subjectService.addSubjectToUser(subjectId, userId);
    }

    @GetMapping("/user/{userId}")
    public Collection<SubjectDto> getSubjectsByUserId(@PathVariable Integer userId){
        return subjectService.getSubjectsByUserId(userId).stream().map(subject -> modelMapper.map(subject,
                        SubjectDto.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{subjectId}")
    public boolean removeSubject(@PathVariable Integer subjectId){
        return subjectService.removeSubject(subjectId);
    }
}
