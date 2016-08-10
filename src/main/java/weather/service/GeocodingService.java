package weather.service;

import weather.service.dto.geocoding.GeocodingResult;

public interface GeocodingService {

    GeocodingResult findBySearchTerm(String q);

}
