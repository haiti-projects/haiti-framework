package dev.struchkov.godfather.simple.core.action.cmd;

import dev.struchkov.godfather.main.domain.StorylineHistory;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.core.action.ActionUnit;
import dev.struchkov.godfather.simple.core.service.StorylineService;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import dev.struchkov.godfather.simple.core.unit.UnitRequest;
import dev.struchkov.godfather.simple.core.unit.cmd.RollBackCmd;

import static dev.struchkov.godfather.exception.RollBackException.rollBackException;

public class RollBackCmdAction<M extends Message> implements ActionUnit<RollBackCmd<M>, M> {

    private final StorylineService<M> storyLineService;

    public RollBackCmdAction(StorylineService<M> storyLineService) {
        this.storyLineService = storyLineService;
    }

    @Override
    public UnitRequest<MainUnit, M> action(UnitRequest<RollBackCmd<M>, M> unitRequest) {
        final RollBackCmd<M> unit = unitRequest.getUnit();
        final M message = unitRequest.getMessage();

        final int countToBack = unit.getCountBack();
        final String rollbackUnitName = unit.getRollbackUnitName();

        StorylineHistory history = rollbackUnitName != null
                ? storyLineService.replaceUserToBack(message.getPersonId(), rollbackUnitName).orElseThrow(rollBackException("Юнит для возвращения не был найден"))
                : storyLineService.replaceUserToBack(message.getPersonId(), countToBack).orElseThrow(rollBackException("Юнит для возвращения не был найден"));
        final String unitName = history.getUnitName();
        final MainUnit<M> nextUnit = storyLineService.getUnitByName(unitName).orElse(unit);
        final M oldMessage = (M) history.getMessage();
        return UnitRequest.of(nextUnit, oldMessage);
    }

}
