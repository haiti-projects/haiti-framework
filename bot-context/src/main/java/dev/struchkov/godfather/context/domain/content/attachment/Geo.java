package dev.struchkov.godfather.context.domain.content.attachment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Вложение типа "Карта".
 *
 * @author upagge [08/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Geo extends Attachment {

    /**
     * Географические координаты.
     */
    private GeoCoordinate geoCoordinate;

    /**
     * Название страны.
     */
    private String country;

    /**
     * Название города.
     */
    private String city;

    private Geo() {
        type = AttachmentType.GEO;
    }

    public static Builder builder() {
        return new Geo().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder coordinate(Float lat, Float aLong) {
            Geo.this.geoCoordinate = new GeoCoordinate(lat, aLong);
            return this;
        }

        public Builder country(String countryName) {
            Geo.this.country = countryName;
            return this;
        }

        public Builder city(String cityName) {
            Geo.this.city = cityName;
            return this;
        }

        public Geo build() {
            return Geo.this;
        }
    }

}
