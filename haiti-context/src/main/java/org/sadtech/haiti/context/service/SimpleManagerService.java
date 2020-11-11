package org.sadtech.haiti.context.service;

import org.sadtech.haiti.context.service.simple.MultipleService;
import org.sadtech.haiti.context.service.simple.PagingService;
import org.sadtech.haiti.context.service.simple.SimpleService;

public interface SimpleManagerService<T, K> extends SimpleService<T, K>, MultipleService<T, K>, PagingService<T> {

}
