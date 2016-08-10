package weather.service;

import weather.domain.Favorite;
import weather.exception.FavoriteDeleteException;
import weather.exception.FavoriteNotFoundException;
import weather.exception.FavoriteSaveException;

import java.util.List;

public interface FavoriteService {

    List<Favorite> findAll();

    Favorite findById(Long id) throws FavoriteNotFoundException;

    void save(Favorite favorite) throws FavoriteSaveException;

    void delete(Long id) throws FavoriteDeleteException;
}
