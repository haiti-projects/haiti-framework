package dev.struchkov.godfather.core.service.action.cmd;

import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.domain.unit.cmd.ReplaceCmd;
import dev.struchkov.godfather.core.service.action.ActionUnit;

public class ReplaceCmdAction implements ActionUnit<ReplaceCmd, Message> {

    @Override
    public UnitRequest<MainUnit, Message> action(UnitRequest<ReplaceCmd, Message> unitRequest) {
        final ReplaceCmd unit = unitRequest.getUnit();
        final Message message = unitRequest.getMessage();
        return UnitRequest.of(unit.getThisUnit(), message);
    }

}
