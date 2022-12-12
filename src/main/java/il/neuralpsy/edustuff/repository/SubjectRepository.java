package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Subject findByName(String name);
}
