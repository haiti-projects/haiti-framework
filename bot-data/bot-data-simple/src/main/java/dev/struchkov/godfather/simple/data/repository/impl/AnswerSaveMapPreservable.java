package dev.struchkov.godfather.simple.data.repository.impl;

import dev.struchkov.godfather.simple.context.service.Pusher;
import dev.struchkov.godfather.simple.data.preser.AnswerSavePreservable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AnswerSaveMapPreservable<S> implements AnswerSavePreservable<S> {

    private final Map<String, Map<String, S>> saveMap = new HashMap<>();

    @Override
    public void save(String personId, String key, S save) {
        saveMap.computeIfAbsent(personId, k -> new HashMap<>()).put(key, save);
    }

    @Override
    public Optional<S> getByKey(String personId, String key) {
        if (saveMap.containsKey(personId)
                && saveMap.get(personId).containsKey(key)) {
            return Optional.of(saveMap.get(personId).get(key));
        }
        return Optional.empty();
    }

    @Override
    public Map<String, S> getAllSaveElement(String personId) {
        return saveMap.get(personId);
    }

    @Override
    public void push(String personId, Pusher<S> pusher) {
        Optional.ofNullable(pusher).ifPresent(sPusher -> sPusher.push(personId, getAllSaveElement(personId)));
    }

}
