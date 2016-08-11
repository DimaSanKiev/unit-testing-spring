package weather.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import weather.service.FavoriteService;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private FavoriteController controller;

    @Mock
    private FavoriteService service;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void index_ShouldIncludeFavoritesInModel() throws Exception {
        // Arrange the mock behavior
        // Act (perform the MVC request) and Assert result
    }

    @Test
    public void add_ShouldRedirectToNewFavorite() throws Exception {
        // Arrange the mock behavior
        // Act (perform the MVC request) and Assert result
    }

    @Test
    public void detail_ShouldErrorOnNotFound() throws Exception {
        // Arrange the mock behavior
        // Act (perform the MVC request) and Assert result
    }
}