package dev.struchkov.godfather.quarkus.data.repository.impl;

import dev.struchkov.godfather.quarkus.context.service.Pusher;
import dev.struchkov.godfather.quarkus.data.preser.AnswerSavePreservable;
import io.smallrye.mutiny.Uni;

import java.util.HashMap;
import java.util.Map;

import static dev.struchkov.haiti.utils.Checker.checkNotEmpty;
import static dev.struchkov.haiti.utils.Checker.checkNotNull;

public class AnswerSaveMapPreservable<S> implements AnswerSavePreservable<S> {

    private final Map<String, Map<String, S>> saveMap = new HashMap<>();

    @Override
    public Uni<Void> save(String personId, String key, S save) {
        saveMap.computeIfAbsent(personId, k -> new HashMap<>()).put(key, save);
        return Uni.createFrom().voidItem();
    }

    @Override
    public Uni<S> getByKey(String personId, String key) {
        if (saveMap.containsKey(personId)
                && saveMap.get(personId).containsKey(key)) {
            return Uni.createFrom().item(saveMap.get(personId).get(key));
        }
        return Uni.createFrom().nullItem();
    }

    @Override
    public Uni<Map<String, S>> getAllSaveElement(String personId) {
        return Uni.createFrom().item(saveMap.get(personId));
    }

    @Override
    public Uni<Void> push(String personId, Pusher<S> pusher) {
        if (checkNotNull(pusher)) {
            return getAllSaveElement(personId).onItem()
                    .transformToUni(
                            allItems -> {
                                if (checkNotEmpty(allItems)) {
                                    return pusher.push(personId, allItems);
                                }
                                return Uni.createFrom().nullItem();
                            }
                    );
        }
        return Uni.createFrom().voidItem();
    }

}
