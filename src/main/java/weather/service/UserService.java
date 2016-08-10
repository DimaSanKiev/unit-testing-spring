package weather.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import weather.domain.User;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

}
