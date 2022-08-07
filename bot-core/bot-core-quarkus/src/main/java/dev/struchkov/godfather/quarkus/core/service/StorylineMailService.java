package dev.struchkov.godfather.quarkus.core.service;

import dev.struchkov.godfather.main.domain.StorylineHistory;
import dev.struchkov.godfather.main.domain.UnitPointer;
import dev.struchkov.godfather.main.domain.content.Mail;
import dev.struchkov.godfather.quarkus.context.service.UnitPointerService;
import dev.struchkov.godfather.quarkus.core.Storyline;
import dev.struchkov.godfather.quarkus.core.StorylineFactory;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import dev.struchkov.godfather.quarkus.data.repository.StorylineRepository;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static dev.struchkov.haiti.utils.Checker.checkEmpty;
import static dev.struchkov.haiti.utils.Checker.checkNotEmpty;
import static dev.struchkov.haiti.utils.Checker.checkNotNull;
import static dev.struchkov.haiti.utils.Inspector.isNotNull;

/**
 * Отвечает за работу со сценарием в личных сообщениях с пользователем.
 */
public class StorylineMailService implements StorylineService<Mail> {

    private final UnitPointerService unitPointerService;
    private final StorylineRepository storylineRepository;
    private final Storyline<Mail> storyLine;
    private String defaultUnitName;

    public StorylineMailService(
            UnitPointerService unitPointerService,
            StorylineRepository storylineRepository,
            List<Object> unitConfigurations
    ) {
        this.storyLine = new StorylineFactory<Mail>(unitConfigurations).createStoryLine();
        this.unitPointerService = unitPointerService;
        this.storylineRepository = storylineRepository;
    }

    @Override
    public Uni<Void> save(@NotNull StorylineHistory storylineHistory) {
        isNotNull(storylineHistory);
        return storylineRepository.save(storylineHistory);
    }

    @Override
    public Uni<MainUnit<Mail>> getUnitNameByPersonId(@NotNull Long personId) {
        isNotNull(personId);
        return unitPointerService.getUnitNameByPersonId(personId)
                .onItem().transform(
                        unitName -> {
                            if (checkNotNull(unitName)) {
                                return storyLine.getUnit(unitName).orElse(null);
                            } else {
                                return null;
                            }
                        }
                );
    }

    @Override
    public Uni<Set<MainUnit<Mail>>> getNextUnitByPersonId(@NotNull Long personId) {
        return getUnitNameByPersonId(personId)
                .flatMap(
                        unit -> {
                            if (checkNotNull(unit) && checkNotEmpty(unit.getNextUnits())) {
                                return Uni.createFrom().item(unit.getNextUnits());
                            } else {
                                return storylineRepository.cleanHistoryByPersonId(personId)
                                        .onItem().transform(v -> storyLine.getStartingUnits());
                            }
                        }
                ).map(
                        nextUnits -> {
                            if (checkEmpty(nextUnits)) {
                                return storyLine.getGlobalUnits();
                            } else {
                                nextUnits.addAll(storyLine.getGlobalUnits());
                                return nextUnits;
                            }
                        }
                );
    }

    @Override
    public Uni<Void> save(Long personId, String unitName, Mail mail) {
        isNotNull(personId, unitName, mail);
        return unitPointerService.save(new UnitPointer(personId, unitName))
                .map(u -> {
                    final StorylineHistory storylineHistory = new StorylineHistory();
                    storylineHistory.setPersonId(personId);
                    storylineHistory.setUnitName(unitName);
                    storylineHistory.setMessage(mail);
                    return storylineRepository.save(storylineHistory);
                }).replaceWithVoid();
    }

    @Override
    public Uni<StorylineHistory> replaceUserToBack(long personId, int countUnitsToBack) {
        return storylineRepository.findByCountLast(personId, countUnitsToBack);
    }

    @Override
    public Uni<StorylineHistory> replaceUserToBack(long personId, String unitName) {
        return storylineRepository.findByCountLast(personId, unitName);
    }

    @Override
    public Optional<MainUnit<Mail>> getDefaultUnit() {
        if (defaultUnitName == null) return Optional.empty();
        return storyLine.getUnit(defaultUnitName);
    }

    @Override
    public void setDefaultUnit(String defaultUnit) {
        defaultUnitName = defaultUnit;
    }

    //TODO [22.06.2022]: Временное решение для ленивой инициализации
    @Override
    public void lazyLink(String firstName, String secondName) {
        storyLine.link(firstName, secondName);
    }

    @Override
    public Optional<MainUnit<Mail>> getUnitByName(String unitName) {
        return storyLine.getUnit(unitName);
    }

}
