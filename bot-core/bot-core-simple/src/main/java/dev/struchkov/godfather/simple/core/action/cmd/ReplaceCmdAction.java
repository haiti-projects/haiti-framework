package dev.struchkov.godfather.simple.core.action.cmd;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.core.action.ActionUnit;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import dev.struchkov.godfather.simple.core.unit.UnitRequest;
import dev.struchkov.godfather.simple.core.unit.cmd.ReplaceCmd;

public class ReplaceCmdAction implements ActionUnit<ReplaceCmd<Message>, Message> {

    @Override
    public UnitRequest<MainUnit, Message> action(UnitRequest<ReplaceCmd<Message>, Message> unitRequest) {
        final ReplaceCmd<Message> unit = unitRequest.getUnit();
        final Message message = unitRequest.getMessage();
        return UnitRequest.of(unit.getThisUnit(), message);
    }

}
