package dev.struchkov.haiti.context.page;

import java.util.List;
import java.util.function.Function;

public interface Sheet<Entity> {

    int getNumber();

    int getSize();

    long getTotalElement();

    int getTotalPage();

    List<Entity> getContent();

    boolean hasContent();

    <NewEntity> Sheet<NewEntity> map(Function<? super Entity, ? extends NewEntity> converter);

}
