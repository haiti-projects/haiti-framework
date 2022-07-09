package dev.struchkov.godfather.core.service;

import dev.struchkov.autoresponder.entity.Unit;
import dev.struchkov.godfather.context.domain.StorylineHistory;
import dev.struchkov.godfather.context.domain.UnitPointer;
import dev.struchkov.godfather.context.domain.content.Mail;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.repository.StorylineRepository;
import dev.struchkov.godfather.context.service.StorylineService;
import dev.struchkov.godfather.context.service.UnitPointerService;
import dev.struchkov.godfather.core.Storyline;
import dev.struchkov.godfather.core.StorylineMaker;
import dev.struchkov.haiti.utils.Inspector;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static dev.struchkov.haiti.utils.Inspector.isNotNull;

/**
 * Отвечает за работу со сценарием в личных сообщениях с пользователем.
 */
public class StorylineMailService implements StorylineService<Mail> {

    private final UnitPointerService unitPointerService;
    private final StorylineRepository storylineRepository;
    private final Storyline storyLine;

    public StorylineMailService(
            UnitPointerService unitPointerService,
            StorylineRepository storylineRepository,
            List<Object> unitConfigurations
    ) {
        this.storyLine = new StorylineMaker(unitConfigurations).createStoryLine();
        this.unitPointerService = unitPointerService;
        this.storylineRepository = storylineRepository;
    }

    @Override
    public void save(@NotNull StorylineHistory storylineHistory) {
        isNotNull(storylineHistory);
        storylineHistory.setId(null);
        storylineRepository.save(storylineHistory);
    }

    @Override
    public Optional<MainUnit> getUnitNameByPersonId(@NotNull Long personId) {
        Inspector.isNotNull(personId);
        return unitPointerService.getUnitNameByPersonId(personId)
                .flatMap(storyLine::getUnit);
    }

    @Override
    public Set<MainUnit> getNextUnitByPersonId(@NotNull Long personId) {
        final Optional<Set<MainUnit>> optMainUnits = getUnitNameByPersonId(personId)
                .map(Unit::getNextUnits)
                .filter(mainUnits -> !mainUnits.isEmpty());
        if (optMainUnits.isEmpty()) {
            storylineRepository.cleanHistoryByPersonId(personId);
        }
        return optMainUnits
                .orElse(storyLine.getStartingUnits());
    }

    @Override
    public void save(Long personId, String unitName, Mail mail) {
        Inspector.isNotNull(personId, unitName, mail);
        unitPointerService.save(new UnitPointer(personId, unitName));

        final StorylineHistory storylineHistory = new StorylineHistory();
        storylineHistory.setPersonId(personId);
        storylineHistory.setUnitName(unitName);
        storylineHistory.setMessage(mail);
        storylineRepository.save(storylineHistory);
    }

    @Override
    public Optional<StorylineHistory> replaceUserToBack(long personId, int countUnitsToBack) {
        return storylineRepository.findByCountLast(personId, countUnitsToBack);
    }

    @Override
    public Optional<MainUnit> getDefaultUnit() {
        return storyLine.getDefaultUnit();
    }

    //TODO [22.06.2022]: Временное решение для ленивой инициализации
    @Override
    public void lazyLink(String firstName, String secondName) {
        storyLine.link(firstName, secondName);
    }

    @Override
    public Optional<MainUnit> getUnitByName(String unitName) {
        return storyLine.getUnit(unitName);
    }

}
