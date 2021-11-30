package dev.struchkov.godfather.core.domain;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Clarification {

    private BoxAnswer question;
    private String value;

}
