package dev.struchkov.godfather.core.service.usercode;

import java.util.List;

@FunctionalInterface
public interface Insert {

    List<String> insert(Long personId);

}
