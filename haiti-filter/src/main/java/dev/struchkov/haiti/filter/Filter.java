package dev.struchkov.haiti.filter;

import java.util.function.Consumer;

public interface Filter {

    Filter and(FilterQuery filterQuery);

    Filter and(Consumer<FilterQuery> query);

    Filter or(FilterQuery filterQuery);

    Filter or(Consumer<FilterQuery> query);

    Filter not(FilterQuery filterQuery);

    Filter not(Consumer<FilterQuery> query);

    <Q> Q build();

}
