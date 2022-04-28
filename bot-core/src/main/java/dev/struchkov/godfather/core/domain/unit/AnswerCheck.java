package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.usercode.CheckData;
import dev.struchkov.godfather.core.utils.TypeUnit;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

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
        super(builder.keyWords, builder.phrase, builder.pattern, builder.matchThreshold, builder.priority, null, builder.activeType, TypeUnit.CHECK);
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
        private Set<String> keyWords = new HashSet<>();
        private String phrase;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private MainUnit unitTrue;
        private MainUnit unitFalse;
        private CheckData<Message> check;
        private UnitActiveType activeType;

        private Builder() {
        }

        public Builder keyWords(Set<String> val) {
            keyWords = val;
            return this;
        }

        public Builder keyWord(String val) {
            keyWords.add(val);
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
