package weather.service.dto.geocoding;

import com.fasterxml.jackson.annotation.JsonProperty;
import weather.service.dto.Dto;

import java.util.List;

public class GeocodingResult extends Dto {
    @JsonProperty("address_components")
    private List<AddressComponent> addressComponents;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    @JsonProperty("place_id")
    private String placeId;

    private Geometry geometry;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
