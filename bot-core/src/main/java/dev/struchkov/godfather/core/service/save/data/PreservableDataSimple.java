package dev.struchkov.godfather.core.service.save.data;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.save.PreservableData;
import dev.struchkov.haiti.utils.Pair;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [13/07/2019]
 */
public class PreservableDataSimple implements PreservableData<Pair<String, String>, Message> {

    private final String fieldName;

    public PreservableDataSimple(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Pair<String, String> getData(Message content) {
        return new Pair<>(fieldName, content.getText());
    }
}
