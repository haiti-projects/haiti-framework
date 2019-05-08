package org.sadtech.bot.core.sender;

import org.sadtech.bot.core.domain.BoxAnswer;

public interface Sent {

    void send(Integer idPerson, String message);

    void send(Integer idPerson, BoxAnswer boxAnswer);

}