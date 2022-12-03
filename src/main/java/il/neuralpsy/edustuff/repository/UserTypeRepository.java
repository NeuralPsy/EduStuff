package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserTypeRepository extends CrudRepository<UserType, Integer> {

    UserType findByName(String name);
}
