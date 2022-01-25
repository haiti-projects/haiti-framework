package dev.struchkov.godfather.context.service.usercode;

import java.util.List;

@FunctionalInterface
public interface Insert {

    List<String> insert(Long personId);

}
