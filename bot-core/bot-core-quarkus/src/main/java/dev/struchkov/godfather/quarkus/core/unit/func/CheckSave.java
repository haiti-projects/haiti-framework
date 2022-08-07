package dev.struchkov.godfather.quarkus.core.unit.func;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import io.smallrye.mutiny.Uni;

/**
 * TODO: Добавить описание интерфейса.
 *
 * @author upagge [04/08/2019]
 */
@FunctionalInterface
public interface CheckSave<M extends Message> {

    Uni<MainUnit<M>> check(M content);

}
