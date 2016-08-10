package weather.service.resttemplate.geocoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import weather.service.dto.geocoding.PlacesResponse;
import weather.service.dto.geocoding.PlacesResult;
import weather.service.resttemplate.PlacesService;
import weather.service.resttemplate.RestApiService;

@Service
@PropertySource("api.properties")
public class PlacesServiceImpl extends RestApiService<PlacesResponse> implements PlacesService {
    private String name;
    private String key;
    private String host;

    @Autowired
    public PlacesServiceImpl(
            @Value("${places.api.name}") String name,
            @Value("${places.api.key}") String key,
            @Value("${places.api.host}") String host) {
        super();
        this.name = name;
        this.key = key;
        this.host = host;
    }

    @Override
    public PlacesResult findByPlaceId(String placeId) {
        PlacesResponse response = get("/maps/api/place/details/json?placeid=#{placeId}&sensor=false&key=#{key}")
                .param("placeId", placeId)
                .execute();
        return response.getResult();
    }

    public String getName() {
        return name;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getApiKey() {
        return key;
    }

    @Override
    public Class<PlacesResponse> getDtoClass() {
        return PlacesResponse.class;
    }
}
