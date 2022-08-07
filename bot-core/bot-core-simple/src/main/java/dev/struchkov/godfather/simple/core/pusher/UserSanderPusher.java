package dev.struchkov.godfather.simple.core.pusher;

import dev.struchkov.godfather.simple.context.service.Pusher;
import dev.struchkov.godfather.simple.context.service.Sending;

import java.util.Map;

import static dev.struchkov.godfather.main.domain.BoxAnswer.boxAnswer;

public class UserSanderPusher implements Pusher<String> {

    private final Long personId;
    private final String nameForm;
    private final Sending sending;

    public UserSanderPusher(Long personId, String nameForm, Sending sending) {
        this.personId = personId;
        this.nameForm = nameForm;
        this.sending = sending;
    }

    @Override
    public void push(Long personId, Map<String, String> saveElement) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("========= ").append(nameForm).append(" =========\n");
        saveElement.forEach((key, value) -> stringBuilder.append(key).append(": ").append(value).append("\n"));
        stringBuilder.append("====================");
        sending.send(this.personId, boxAnswer(stringBuilder.toString()));
    }

}
