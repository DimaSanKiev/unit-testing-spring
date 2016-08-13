package weather.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import weather.dao.FavoriteDao;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteServiceTest {

    @Mock
    private FavoriteDao dao;

    @InjectMocks
    private FavoriteService service = new FavoriteServiceImpl();

    @Test
    public void findAll_ShouldReturnTwo() throws Exception {
        
    }

    @Test
    public void findById_ShouldReturnOne() throws Exception {

    }

    @Test
    public void findById_ShouldThrowFavoriteNotFoundException() throws Exception {

    }
}