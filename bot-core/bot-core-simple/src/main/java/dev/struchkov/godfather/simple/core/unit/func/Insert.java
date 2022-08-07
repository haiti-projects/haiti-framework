package dev.struchkov.godfather.simple.core.unit.func;

import java.util.List;

@FunctionalInterface
public interface Insert {

    List<String> insert(Long personId);

}
