package dev.struchkov.godfather.context.domain.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.Accessibility;
import dev.struchkov.godfather.context.service.usercode.CheckData;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static dev.struchkov.godfather.context.exception.UnitConfigException.unitConfigException;
import static dev.struchkov.haiti.utils.Inspector.isAnyNotNull;
import static dev.struchkov.haiti.utils.Inspector.isNotNull;

/**
 * Обработчик запроса, который реализует конструкцию IF в сценарии.
 *
 * @author upagge [08/07/2019]
 */
public class AnswerCheck extends MainUnit {

    /**
     * Unit для true.
     */
    private final MainUnit unitTrue;

    /**
     * Unit для false.
     */
    private final MainUnit unitFalse;

    /**
     * Условие проверки.
     */
    private final CheckData<Message> check;

    private AnswerCheck(Builder builder) {
        super(
                builder.name,
                builder.keyWords,
                builder.phrase,
                builder.pattern,
                builder.matchThreshold,
                builder.priority,
                new HashSet<>(),
                builder.activeType,
                builder.accessibility,
                TypeUnit.CHECK
        );
        unitTrue = builder.unitTrue;
        unitFalse = builder.unitFalse;
        check = builder.check;
    }

    public static Builder builder() {
        return new Builder();
    }

    public MainUnit getUnitTrue() {
        return unitTrue;
    }

    public MainUnit getUnitFalse() {
        return unitFalse;
    }

    public CheckData<Message> getCheck() {
        return check;
    }

    public static final class Builder {
        private String name;
        private Set<KeyWord> keyWords = new HashSet<>();
        private String phrase;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private MainUnit unitTrue;
        private MainUnit unitFalse;
        private CheckData<Message> check;
        private UnitActiveType activeType;
        private Accessibility accessibility;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
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

        public Builder unitTrue(MainUnit unitTrue) {
            this.unitTrue = unitTrue;
            return this;
        }

        public Builder unitFalse(MainUnit unitFalse) {
            this.unitFalse = unitFalse;
            return this;
        }

        public Builder check(CheckData<Message> check) {
            this.check = check;
            return this;
        }

        public Builder accessibility(Accessibility val) {
            accessibility = val;
            return this;
        }

        public Builder activeType(UnitActiveType val) {
            activeType = val;
            return this;
        }

        public AnswerCheck build() {
            isNotNull(check, unitConfigException("Необходимо установить параметр проверки."));
            isAnyNotNull(unitConfigException("Необходимо задать хотя бы один unit результата проверки."));
            return new AnswerCheck(this);
        }

    }

}
