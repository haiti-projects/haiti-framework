package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.Clarification;
import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.AnswerText;
import dev.struchkov.godfather.context.domain.unit.AnswerValidity;
import dev.struchkov.godfather.context.domain.unit.MainUnit;

import java.util.Set;

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
    public UnitRequest<MainUnit, Message> action(UnitRequest<AnswerValidity, Message> unitRequest) {
        final AnswerValidity unit = unitRequest.getUnit();
        final Message content = unitRequest.getMessage();

        String message = content.getText();
        Long personId = content.getPersonId();
        if (WORDS_YES.contains(message.toLowerCase())) {
            unit.getTempSave().getByKey(personId, "temp").ifPresent(content::setText);
            return UnitRequest.of(unit.getUnitYes(), content);
        } else if (WORDS_NO.contains(message.toLowerCase())) {
            unit.getTempSave().getByKey(personId, "temp").ifPresent(content::setText);
            return UnitRequest.of(unit.getUnitNo(), content);
        } else {
            Clarification clarification = unit.getClarificationQuestion().getClarification(content);
            final String value = clarification.getValue();
            if (value == null) {
                return UnitRequest.of(unit.getUnitNull(), content);
            } else {
                unit.getTempSave().save(personId, "temp", value);
                AnswerValidity newValidity = unit.builder()
                        .clearKeyWords()
                        .stringKeyWords(WORDS_YES_NO)
                        .build();
                return UnitRequest.of(
                        AnswerText.builder()
                                .message(mes -> clarification.getQuestion())
                                .nextUnit(newValidity)
                                .build(),
                        content
                );
            }
        }
    }
}
