package org.sadtech.vkbot.core.service.impl;

import com.vk.api.sdk.objects.users.User;
import org.apache.log4j.Logger;
import org.sadtech.vkbot.core.entity.Person;
import org.sadtech.vkbot.core.repository.PersonRepository;
import org.sadtech.vkbot.core.repository.impl.PersonRepositoryMap;
import org.sadtech.vkbot.core.service.PersonService;

public class PersonServiceImpl implements PersonService {

    public static final Logger log = Logger.getLogger(PersonServiceImpl.class);

    private PersonRepository personRepository;

    public PersonServiceImpl() {
        this.personRepository = new PersonRepositoryMap();
    }

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void add(User user) {
        Person person = new Person();
        person.setId(user.getId());
        person.setLastName(user.getLastName());
        person.setFirstName(user.getFirstName());
        personRepository.add(person);
        log.info("Пользователь добавлен в репозиторий");
    }

    @Override
    public void add(Person person) {
        personRepository.add(person);
        log.info("Пользователь добавлен в репозиторий");
    }

    @Override
    public Person get(Integer id) {
        return personRepository.get(id);
    }

    @Override
    public boolean checkPerson(Integer idPerson) {
        log.info("Проверка наличия пользователя в репозитории");
        return get(idPerson) != null;
    }
}
