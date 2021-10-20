package dev.struchkov.haiti.context.service;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.service.simple.MultipleService;
import dev.struchkov.haiti.context.service.simple.PagingService;
import dev.struchkov.haiti.context.service.simple.SimpleService;

public interface SimpleManagerService<T extends BasicEntity<K>, K> extends SimpleService<T, K>, MultipleService<T, K>, PagingService<T> {

}
