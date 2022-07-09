package dev.struchkov.godfather.core.service.action.cmd;

import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.domain.unit.cmd.TeleportCmd;
import dev.struchkov.godfather.context.service.StorylineService;
import dev.struchkov.godfather.core.service.action.ActionUnit;

import java.util.Optional;

public class TeleportCmdAction<T extends Message> implements ActionUnit<TeleportCmd, T> {

    private final StorylineService<T> storyLineService;

    public TeleportCmdAction(StorylineService<T> storyLineService) {
        this.storyLineService = storyLineService;
    }

    @Override
    public UnitRequest<MainUnit, T> action(UnitRequest<TeleportCmd, T> unitRequest) {
        final TeleportCmd unit = unitRequest.getUnit();
        final T message = unitRequest.getMessage();
        final Optional<MainUnit> optNextUnit = storyLineService.getUnitByName(unit.getUnitNameToTeleport());
        return optNextUnit
                .map(mainUnit -> UnitRequest.of(mainUnit, message))
                .orElseGet(() -> UnitRequest.of(unit, message));
    }

}
