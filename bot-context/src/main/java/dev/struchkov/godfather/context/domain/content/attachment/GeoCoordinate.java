package dev.struchkov.godfather.context.domain.content.attachment;

/**
 * Сущность для хранения географических координат.
 *
 * @author upagge [08/07/2019]
 */
public class GeoCoordinate {

    /**
     * Широта.
     */
    private Float latitude;

    /**
     * Долгота.
     */
    private Float longitude;

    public GeoCoordinate(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

}
