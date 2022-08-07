package dev.struchkov.godfather.quarkus.core.unit.func;

import dev.struchkov.godfather.main.domain.content.Message;
import io.smallrye.mutiny.Uni;

@FunctionalInterface
public interface CheckData<C extends Message> {

    Uni<Boolean> checked(C content);

}
