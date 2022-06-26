package dev.struchkov.godfather.core.service;

import dev.struchkov.godfather.context.repository.PersonSettingRepository;
import dev.struchkov.godfather.context.service.PersonSettingService;
import dev.struchkov.haiti.utils.Inspector;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public class PersonSettingServiceImpl implements PersonSettingService {

    private final PersonSettingRepository personSettingRepository;

    public PersonSettingServiceImpl(PersonSettingRepository personSettingRepository) {
        this.personSettingRepository = personSettingRepository;
    }

    @Override
    public Set<Long> getAllPersonIdDisableMessages(@NotNull Set<Long> personIds) {
        Inspector.isNotNull(personIds);
        return personSettingRepository.findAllByAllowedProcessing(personIds);
    }

    @Override
    public Optional<Boolean> getStateProcessingByPersonId(@NotNull Long personId) {
        return personSettingRepository.findStateByPersonId(personId);
    }

    @Override
    public void disableMessageProcessing(@NotNull Long personId) {
        Inspector.isNotNull(personId);
        personSettingRepository.disableMessageProcessing(personId);
    }

    @Override
    public void enableMessageProcessing(@NotNull Long personId) {
        Inspector.isNotNull(personId);
        personSettingRepository.enableMessageProcessing(personId);
    }

}
