package dev.struchkov.haiti.filter;

public interface Filter {

    Filter and(FilterQuery filterQuery);

    Filter or(FilterQuery filterQuery);

    Filter not(FilterQuery filterQuery);

    <Q> Q build();

}
