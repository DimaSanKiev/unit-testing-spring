package weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.dao.FavoriteDao;
import weather.domain.Favorite;
import weather.exception.FavoriteDeleteException;
import weather.exception.FavoriteNotFoundException;
import weather.exception.FavoriteSaveException;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public List<Favorite> findAll() {
        return favoriteDao.findAll();
    }

    @Override
    public Favorite findById(Long id) throws FavoriteNotFoundException {
        Favorite fave = favoriteDao.findOne(id);
        if (fave == null) {
            throw new FavoriteNotFoundException();
        }
        return fave;
    }

    @Override
    public void save(Favorite favorite) throws FavoriteSaveException {
        if (favoriteDao.saveForCurrentUser(favorite) == 1) {
            Favorite newFave = favoriteDao.findByPlaceId(favorite.getPlaceId());
            favorite.setId(newFave.getId());
        } else {
            throw new FavoriteSaveException();
        }
    }

    @Override
    public void delete(Long id) throws FavoriteDeleteException {
        if (favoriteDao.deleteForCurrentUser(id) < 1) {
            throw new FavoriteDeleteException();
        }
    }
}
