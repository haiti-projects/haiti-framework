package dev.struchkov.godfather.core.service.save.push;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.service.save.Pusher;
import dev.struchkov.godfather.context.service.sender.Sending;

import java.util.Map;

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
        sending.send(this.personId, BoxAnswer.builder().message(stringBuilder.toString()).build());
    }

}
