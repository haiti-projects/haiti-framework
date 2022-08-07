package dev.struchkov.godfather.quarkus.core.action.cmd;

import dev.struchkov.godfather.main.domain.StorylineHistory;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.core.action.ActionUnit;
import dev.struchkov.godfather.quarkus.core.service.StorylineService;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import dev.struchkov.godfather.quarkus.core.unit.UnitRequest;
import dev.struchkov.godfather.quarkus.core.unit.cmd.RollBackCmd;
import io.smallrye.mutiny.Uni;

import static dev.struchkov.godfather.exception.RollBackException.rollBackException;

public class RollBackCmdAction<M extends Message> implements ActionUnit<RollBackCmd<M>, M> {

    private final StorylineService<M> storyLineService;

    public RollBackCmdAction(StorylineService<M> storyLineService) {
        this.storyLineService = storyLineService;
    }

    @Override
    public Uni<UnitRequest<MainUnit, M>> action(UnitRequest<RollBackCmd<M>, M> unitRequest) {
        final RollBackCmd<M> unit = unitRequest.getUnit();
        final M message = unitRequest.getMessage();

        final int countToBack = unit.getCountBack();
        final String rollbackUnitName = unit.getRollbackUnitName();

        final Uni<StorylineHistory> uniHistory = (rollbackUnitName != null)
                ? storyLineService.replaceUserToBack(message.getPersonId(), rollbackUnitName).onItem().ifNull().failWith(rollBackException("Юнит для возвращения не был найден"))
                : storyLineService.replaceUserToBack(message.getPersonId(), countToBack).onItem().ifNull().failWith(rollBackException("Юнит для возвращения не был найден"));

        return uniHistory
                .onItem().transform(
                        history -> {
                            final String unitName = history.getUnitName();
                            final MainUnit<M> nextUnit = storyLineService.getUnitByName(unitName).orElse(unit);
                            final M oldMessage = (M) history.getMessage();
                            return UnitRequest.of(nextUnit, oldMessage);
                        }
                );
    }

}
