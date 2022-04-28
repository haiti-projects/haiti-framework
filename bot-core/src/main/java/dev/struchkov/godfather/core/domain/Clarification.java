package dev.struchkov.godfather.core.domain;

import dev.struchkov.godfather.context.domain.BoxAnswer;

public class Clarification {

    private BoxAnswer question;
    private String value;

    public Clarification(BoxAnswer question, String value) {
        this.question = question;
        this.value = value;
    }

    public BoxAnswer getQuestion() {
        return question;
    }

    public String getValue() {
        return value;
    }

}
