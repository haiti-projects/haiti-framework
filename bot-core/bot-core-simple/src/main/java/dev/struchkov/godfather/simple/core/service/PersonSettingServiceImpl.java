package dev.struchkov.godfather.simple.core.service;

import dev.struchkov.godfather.simple.context.service.PersonSettingService;
import dev.struchkov.godfather.simple.data.repository.PersonSettingRepository;
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
    public Set<String> getAllPersonIdDisableMessages(@NotNull Set<String> personIds) {
        Inspector.isNotNull(personIds);
        return personSettingRepository.findAllByAllowedProcessing(personIds);
    }

    @Override
    public Optional<Boolean> getStateProcessingByPersonId(@NotNull String personId) {
        return personSettingRepository.findStateByPersonId(personId);
    }

    @Override
    public void disableMessageProcessing(@NotNull String personId) {
        Inspector.isNotNull(personId);
        personSettingRepository.disableMessageProcessing(personId);
    }

    @Override
    public void enableMessageProcessing(@NotNull String personId) {
        Inspector.isNotNull(personId);
        personSettingRepository.enableMessageProcessing(personId);
    }

}
