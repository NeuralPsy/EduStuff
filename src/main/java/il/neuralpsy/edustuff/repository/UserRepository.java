package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User set name = :name, email = :email, birthdate = :birthdate where userId = :userId")
    void update(@Param("name") String name, @Param("email") String email, @Param("birthdate") LocalDate birthdate,
                @Param("userId") Integer userId);

    Collection<User> findAllByUserType_UserTypeId(Integer userTypeId);


}
