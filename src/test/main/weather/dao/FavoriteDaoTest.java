package weather.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import weather.Application;
import weather.domain.Favorite;
import weather.domain.User;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static weather.domain.Favorite.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@DatabaseSetup("classpath:favorites.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class FavoriteDaoTest {
    @Autowired
    private FavoriteDao dao;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setId(1L);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null));
    }

    @Test
    public void findAll_ShouldReturnTwo() throws Exception {
        assertThat(dao.findAll(), hasSize(2));
    }

    @Test
    public void save_ShouldPersistEntity() throws Exception {
        String placeId = "ASDSDAFSDAF#";
        Favorite fave = new FavoriteBuilder().withPlaceId(placeId).build();
        dao.saveForCurrentUser(fave);
        assertThat(dao.findByPlaceId(placeId), notNullValue(Favorite.class));
    }
}