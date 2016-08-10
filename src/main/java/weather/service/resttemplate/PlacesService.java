package weather.service.resttemplate;

import weather.service.dto.geocoding.PlacesResult;

public interface PlacesService {

    PlacesResult findByPlaceId(String placeId);

}
