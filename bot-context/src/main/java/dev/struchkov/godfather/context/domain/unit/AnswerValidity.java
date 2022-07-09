package dev.struchkov.godfather.context.domain.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.service.Accessibility;
import dev.struchkov.godfather.context.service.ClarificationQuestion;
import dev.struchkov.godfather.context.service.save.LocalPreservable;
import dev.struchkov.godfather.context.service.save.Preservable;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Обработка данных со страницы пользователя.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerValidity extends MainUnit {

    /**
     * Unit обрабатывается, если пользователь подтверждает данные.
     */
    private final MainUnit unitYes;

    /**
     * Unit обрабатывается, если пользователь отклоняет данные.
     */
    private final MainUnit unitNo;

    /**
     * Unit обрабатывается, если данные не найдены.
     */
    private final MainUnit unitNull;

    private final Preservable<String> tempSave = new LocalPreservable<>();

    private final ClarificationQuestion clarificationQuestion;

    private AnswerValidity(Builder builder) {
        super(
                builder.name,
                builder.keyWords,
                builder.phrase,
                builder.pattern,
                builder.matchThreshold,
                builder.priority,
                builder.nextUnits,
                UnitActiveType.DEFAULT,
                builder.accessibility,
                TypeUnit.VALIDITY
        );
        unitYes = builder.unitYes;
        unitNo = builder.unitNo;
        unitNull = builder.unitNull;
        clarificationQuestion = builder.clarificationQuestion;
    }

    public static Builder builder() {
        return new Builder();
    }

    public MainUnit getUnitYes() {
        return unitYes;
    }

    public MainUnit getUnitNo() {
        return unitNo;
    }

    public MainUnit getUnitNull() {
        return unitNull;
    }

    public Preservable<String> getTempSave() {
        return tempSave;
    }

    public ClarificationQuestion getClarificationQuestion() {
        return clarificationQuestion;
    }

    public static final class Builder {
        private String name;
        private MainUnit unitYes;
        private MainUnit unitNo;
        private MainUnit unitNull;
        private ClarificationQuestion clarificationQuestion;
        private Set<KeyWord> keyWords = new HashSet<>();
        private String phrase;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private Set<MainUnit> nextUnits = new HashSet<>();
        private Accessibility accessibility;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder unitYes(MainUnit val) {
            unitYes = val;
            return this;
        }

        public Builder unitNo(MainUnit val) {
            unitNo = val;
            return this;
        }

        public Builder unitNull(MainUnit val) {
            unitNull = val;
            return this;
        }

        public Builder clarificationQuestion(ClarificationQuestion val) {
            clarificationQuestion = val;
            return this;
        }

        public Builder keyWords(Set<KeyWord> val) {
            keyWords.addAll(val);
            return this;
        }

        public Builder keyWord(KeyWord val) {
            keyWords.add(val);
            return this;
        }

        public Builder stringKeyWords(Set<String> val) {
            keyWords.addAll(val.stream().map(KeyWord::of).collect(Collectors.toSet()));
            return this;
        }

        public Builder keyWord(String val) {
            keyWords.add(KeyWord.of(val));
            return this;
        }

        public Builder phrase(String val) {
            phrase = val;
            return this;
        }

        public Builder pattern(Pattern val) {
            pattern = val;
            return this;
        }

        public Builder matchThreshold(Integer val) {
            matchThreshold = val;
            return this;
        }

        public Builder priority(Integer val) {
            priority = val;
            return this;
        }

        public Builder nextUnits(Set<MainUnit> val) {
            nextUnits = val;
            return this;
        }

        public Builder nextUnit(MainUnit val) {
            nextUnits.add(val);
            return this;
        }

        public Builder clearKeyWords() {
            keyWords.clear();
            return this;
        }

        public Builder accessibility(Accessibility val) {
            accessibility = val;
            return this;
        }

        public AnswerValidity build() {
            return new AnswerValidity(this);
        }
    }

}
