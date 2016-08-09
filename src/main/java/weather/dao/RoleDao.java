package weather.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import weather.domain.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
}
