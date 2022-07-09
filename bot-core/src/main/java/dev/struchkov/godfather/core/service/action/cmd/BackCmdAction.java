package dev.struchkov.godfather.core.service.action.cmd;

import dev.struchkov.godfather.context.domain.StorylineHistory;
import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.cmd.RollBackCmd;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.service.StorylineService;
import dev.struchkov.godfather.core.service.action.ActionUnit;

import java.util.Optional;

public class BackCmdAction<T extends Message> implements ActionUnit<RollBackCmd, T> {

    private final StorylineService<T> storyLineService;

    public BackCmdAction(StorylineService<T> storyLineService) {
        this.storyLineService = storyLineService;
    }

    @Override
    public UnitRequest<MainUnit, T> action(UnitRequest<RollBackCmd, T> unitRequest) {
        final RollBackCmd unit = unitRequest.getUnit();
        final T message = unitRequest.getMessage();

        final int countToBack = unit.getCountBack();

        final Optional<StorylineHistory> optHistory = storyLineService.replaceUserToBack(message.getPersonId(), countToBack);
        if (optHistory.isPresent()) {
            final StorylineHistory history = optHistory.get();
            final String unitName = history.getUnitName();
            final MainUnit nextUnit = storyLineService.getUnitByName(unitName).orElse(unit);
            final T oldMessage = (T) history.getMessage();
            return UnitRequest.of(nextUnit, oldMessage);
        }
        return UnitRequest.of(unit, message);
    }
}
