package dev.struchkov.haiti.context.service;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.service.simple.MultipleService;
import dev.struchkov.haiti.context.service.simple.PagingService;
import dev.struchkov.haiti.context.service.simple.SimpleService;

public interface SimpleManagerService<Entity extends BasicEntity<Key>, Key>
        extends SimpleService<Entity, Key>, MultipleService<Entity, Key>, PagingService<Entity> {

}
