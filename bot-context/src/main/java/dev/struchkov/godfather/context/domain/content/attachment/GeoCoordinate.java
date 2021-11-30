package dev.struchkov.godfather.context.domain.content.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import dev.struchkov.godfather.context.utils.Description;

/**
 * Сущность для хранения географических координат.
 *
 * @author upagge [08/07/2019]
 */
@Data
@AllArgsConstructor
public class GeoCoordinate {

    @Description("Широта")
    private Float latitude;

    @Description("Долгота")
    private Float longitude;

}
