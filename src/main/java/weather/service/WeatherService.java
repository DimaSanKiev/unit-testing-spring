package weather.service;

import weather.service.dto.geocoding.Location;
import weather.service.dto.weather.Weather;

public interface WeatherService {

    Weather findByLocation(Location location);

}
