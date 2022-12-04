package il.neuralpsy.edustuff.controller;

import il.neuralpsy.edustuff.dto.SubjectDto;
import il.neuralpsy.edustuff.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping
    public Collection<SubjectDto> getAll(){
        return subjectService.getAll();
    }

    @GetMapping("/{subjectId}")
    public SubjectDto getSubjectById(@PathVariable Integer subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    @PostMapping
    public SubjectDto addSubject(@RequestBody SubjectDto subjectDto){
        return subjectService.addSubject(subjectDto);
    }

    @PutMapping("/{subjectId}/user/{userId}")
    public boolean addSubjectToUser(@PathVariable Integer subjectId, @PathVariable Integer userId){
        return subjectService.addSubjectToUser(subjectId, userId);
    }

    @GetMapping("/user/{userId}")
    public Collection<SubjectDto> getSubjectsByUserId(@PathVariable Integer userId){
        return subjectService.getSubjectsByUserId(userId);
    }

    @DeleteMapping("/{subjectId}")
    public boolean removeSubject(@PathVariable Integer subjectId){
        return subjectService.removeSubject(subjectId);
    }
}
