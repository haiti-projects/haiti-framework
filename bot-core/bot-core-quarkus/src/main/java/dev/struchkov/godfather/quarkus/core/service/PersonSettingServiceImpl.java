package dev.struchkov.godfather.quarkus.core.service;

import dev.struchkov.godfather.quarkus.context.service.PersonSettingService;
import dev.struchkov.godfather.quarkus.data.repository.PersonSettingRepository;
import dev.struchkov.haiti.utils.Inspector;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class PersonSettingServiceImpl implements PersonSettingService {

    private final PersonSettingRepository personSettingRepository;

    public PersonSettingServiceImpl(PersonSettingRepository personSettingRepository) {
        this.personSettingRepository = personSettingRepository;
    }

    @Override
    public Uni<Set<String>> getAllPersonIdDisableMessages(@NotNull Set<String> personIds) {
        Inspector.isNotNull(personIds);
        return personSettingRepository.findAllByAllowedProcessing(personIds);
    }

    @Override
    public Uni<Boolean> getStateProcessingByPersonId(@NotNull String personId) {
        return personSettingRepository.findStateByPersonId(personId);
    }

    @Override
    public Uni<Void> disableMessageProcessing(@NotNull String personId) {
        Inspector.isNotNull(personId);
        return personSettingRepository.disableMessageProcessing(personId);
    }

    @Override
    public Uni<Void> enableMessageProcessing(@NotNull String personId) {
        Inspector.isNotNull(personId);
        return personSettingRepository.enableMessageProcessing(personId);
    }

}
