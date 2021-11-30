package dev.struchkov.godfather.context.domain.content.attachment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import dev.struchkov.godfather.context.utils.Description;

/**
 * Вложение типа "Карта".
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Geo extends Attachment {

    @Description("Географические координаты")
    private GeoCoordinate geoCoordinate;

    @Description("Название страны")
    private String country;

    @Description("Название города")
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
