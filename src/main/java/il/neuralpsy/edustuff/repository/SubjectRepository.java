package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
