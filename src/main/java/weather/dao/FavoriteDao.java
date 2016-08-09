package weather.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import weather.domain.Favorite;

import java.util.List;

@Repository
public interface FavoriteDao extends CrudRepository<Favorite, Long> {

    @Query("select f from Favorite f where f.user.id=:#{principal.id}")
    List<Favorite> findAll();

    @Query("select f from Favorite f where f.user.id=:#{principal.id} and f.placeId=:#{#placeId}")
    Favorite findByPlaceId(@Param("placeId") String placeId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into favorite (user_id,formattedAddress,placeId) values " +
            "(:#{principal.id},:#{#favorite.formattedAddress},:#{#favorite.placeId})")
    int saveForCurrentUser(@Param("favorite") Favorite favorite);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from favorite where id=:#{#id} and user.id=:#{principal.id}")
    int deleteForCurrentUser(@Param("id") Long id);

}