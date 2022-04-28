package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.core.domain.Clarification;
import dev.struchkov.godfather.core.domain.unit.AnswerText;
import dev.struchkov.godfather.core.domain.unit.AnswerValidity;
import dev.struchkov.godfather.core.domain.unit.MainUnit;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Обработчик Unit-а {@link AnswerValidity}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerValidityAction implements ActionUnit<AnswerValidity, Message> {

    public static final Set<String> WORDS_YES = Set.of("да", "ага");
    public static final Set<String> WORDS_NO = Set.of("нет", "неа");
    public static final Set<String> WORDS_YES_NO = Set.of("да", "ага", "нет", "неа");

    @Override
    public MainUnit action(AnswerValidity unit, Message content) {
        String message = content.getText();
        Long personId = content.getPersonId();
        if (WORDS_YES.contains(message.toLowerCase())) {
            unit.getTempSave().getByKey(personId, "temp").ifPresent(content::setText);
            return unit.getUnitYes();
        } else if (WORDS_NO.contains(message.toLowerCase())) {
            unit.getTempSave().getByKey(personId, "temp").ifPresent(content::setText);
            return unit.getUnitNo();
        } else {
            Clarification clarification = unit.getClarificationQuestion().getClarification(content);
            final String value = clarification.getValue();
            if (value == null) {
                return unit.getUnitNull();
            } else {
                unit.getTempSave().save(personId, "temp", value);
                AnswerValidity newValidity = unit.builder()
                        .clearKeyWords().keyWords(WORDS_YES_NO)
                        .build();
                return AnswerText.builder()
                        .boxAnswer(mes -> clarification.getQuestion())
                        .nextUnit(newValidity)
                        .build();
            }
        }
    }

}
