package dev.struchkov.godfather.core.service.action.cmd;

import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.domain.unit.cmd.TeleportCmd;
import dev.struchkov.godfather.context.service.StorylineService;
import dev.struchkov.godfather.core.service.action.ActionUnit;

import java.util.Optional;

public class TeleportCmdAction<M extends Message> implements ActionUnit<TeleportCmd<M>, M> {

    private final StorylineService<M> storyLineService;

    public TeleportCmdAction(StorylineService<M> storyLineService) {
        this.storyLineService = storyLineService;
    }

    @Override
    public UnitRequest<MainUnit, M> action(UnitRequest<TeleportCmd<M>, M> unitRequest) {
        final TeleportCmd<M> unit = unitRequest.getUnit();
        final M message = unitRequest.getMessage();
        final Optional<MainUnit<M>> optNextUnit = storyLineService.getUnitByName(unit.getUnitNameToTeleport());
        if (optNextUnit.isPresent()) {
            return UnitRequest.of(optNextUnit.get(), message);
        } else {
            return UnitRequest.of(unit, message);
        }
    }

}
