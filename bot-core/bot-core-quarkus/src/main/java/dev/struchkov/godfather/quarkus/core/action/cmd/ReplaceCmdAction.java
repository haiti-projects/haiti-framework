package dev.struchkov.godfather.quarkus.core.action.cmd;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.core.action.ActionUnit;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import dev.struchkov.godfather.quarkus.core.unit.UnitRequest;
import dev.struchkov.godfather.quarkus.core.unit.cmd.ReplaceCmd;
import io.smallrye.mutiny.Uni;

public class ReplaceCmdAction implements ActionUnit<ReplaceCmd<Message>, Message> {

    @Override
    public Uni<UnitRequest<MainUnit, Message>> action(UnitRequest<ReplaceCmd<Message>, Message> unitRequest) {
        final ReplaceCmd<Message> unit = unitRequest.getUnit();
        final Message message = unitRequest.getMessage();
        return Uni.createFrom().item(UnitRequest.of(unit.getThisUnit(), message));
    }

}
