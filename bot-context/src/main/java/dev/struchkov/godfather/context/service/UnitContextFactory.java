package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.AnswerText;

public interface UnitContextFactory<M extends Message> {

    AnswerText.Builder<M> createAnswerText();

}
