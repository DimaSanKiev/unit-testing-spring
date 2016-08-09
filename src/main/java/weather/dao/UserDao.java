package weather.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import weather.domain.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
