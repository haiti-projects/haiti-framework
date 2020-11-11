package org.sadtech.haiti.context.page;

import java.util.List;
import java.util.function.Function;

public interface Sheet<T> {

    int getNumber();

    int getSize();

    long getTotalElement();

    int getTotalPage();

    List<T> getContent();

    boolean hasContent();

    <U> Sheet<U> map(Function<? super T, ? extends U> converter);

}
