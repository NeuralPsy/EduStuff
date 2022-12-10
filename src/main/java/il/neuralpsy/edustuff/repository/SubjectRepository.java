package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Subject findByName(String name);
}
