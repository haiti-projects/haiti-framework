package dev.struchkov.godfather.context.domain.content.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность для хранения географических координат.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@Setter
@AllArgsConstructor
public class GeoCoordinate {

    /**
     * Широта.
     */
    private Float latitude;

    /**
     * Долгота.
     */
    private Float longitude;

}
