package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.event.FeedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface FeedRepository extends JpaRepository<FeedEvent, Integer> {

    Collection<FeedEvent> findAllByUser_UserId(Integer userId);


}
