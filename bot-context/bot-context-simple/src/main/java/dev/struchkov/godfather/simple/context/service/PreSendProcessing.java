package dev.struchkov.godfather.simple.context.service;

import dev.struchkov.godfather.main.domain.BoxAnswer;

@FunctionalInterface
public interface PreSendProcessing {

    BoxAnswer pretreatment(BoxAnswer boxAnswer);

}
