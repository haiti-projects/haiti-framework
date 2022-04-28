package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.content.Message;
import org.jetbrains.annotations.NotNull;

/**
 * Интерфес для изменения запроса пользователя перед тем, как он попадет в подсистему обработки.
 * Например можно исправить опечатки, перевести сообщение на другой язык и так далее.
 *
 * @author upagge [08/07/2019]
 */
@FunctionalInterface
public interface Modifiable<T extends Message> {

    void change(@NotNull T content);

}
