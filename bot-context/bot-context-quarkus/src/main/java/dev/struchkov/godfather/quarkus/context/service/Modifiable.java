package dev.struchkov.godfather.quarkus.context.service;

import dev.struchkov.godfather.main.domain.content.Message;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

/**
 * Интерфес для изменения запроса пользователя перед тем, как он попадет в подсистему обработки.
 * Например можно исправить опечатки, перевести сообщение на другой язык и так далее.
 *
 * @author upagge [08/07/2019]
 */
@FunctionalInterface
public interface Modifiable<T extends Message> {

    Uni<Void> change(@NotNull T content);

}
