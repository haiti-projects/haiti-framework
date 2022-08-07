package dev.struchkov.godfather.quarkus.core.unit.func;

import io.smallrye.mutiny.Uni;

import java.util.List;

@FunctionalInterface
public interface Insert {

    Uni<List<String>> insert(Long personId);

}
