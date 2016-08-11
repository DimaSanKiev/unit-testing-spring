package weather.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import weather.domain.Favorite;
import weather.service.FavoriteService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static weather.domain.Favorite.FavoriteBuilder;

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
        List<Favorite> favorites = Arrays.asList(
                new FavoriteBuilder(1L).withAddress("Chikago").withPlaceId("chikago1").build(),
                new FavoriteBuilder(2L).withAddress("Omaha").withPlaceId("omaha1").build()
        );
        when(service.findAll()).thenReturn(favorites);

        // Act (perform the MVC request)
        mockMvc.perform(get("/favorites"))
                .andExpect(status().isOk())
                .andExpect(view().name("favorite/index"))
                .andExpect(model().attribute("favorites", favorites));

        // Assert result
        verify(service).findAll();
    }

    @Test
    public void add_ShouldRedirectToNewFavorite() throws Exception {
        // Arrange the mock behavior
        doAnswer(invocation -> {
            Favorite f = (Favorite) invocation.getArguments()[0];
            f.setId(1L);
            return null;
        }).when(service).save(any(Favorite.class));

        // Act (perform the MVC request) and Assert result
        mockMvc.perform(
                post("/favorites")
                        .param("formattedAddress", "chikago, il")
                        .param("placeId", "windycity")
        ).andExpect(redirectedUrl("/favorites/1"));
        verify(service).save(any(Favorite.class));
    }

    @Test
    public void detail_ShouldErrorOnNotFound() throws Exception {
        // Arrange the mock behavior
        // Act (perform the MVC request) and Assert result
    }
}