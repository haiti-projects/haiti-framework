package dev.struchkov.haiti.context.service.simple;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.domain.ExistsContainer;

import java.util.Collection;
import java.util.List;

public interface MultipleService<Entity extends BasicEntity<Key>, Key> {

    List<Entity> createAll(Collection<Entity> entities);

    List<Entity> updateAll(Collection<Entity> entities);

    void deleteAllById(Collection<Key> ids);

    ExistsContainer<Entity, Key> existsById(Collection<Key> ids);

}
