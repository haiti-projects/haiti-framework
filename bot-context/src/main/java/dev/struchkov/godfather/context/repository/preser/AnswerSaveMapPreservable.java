package dev.struchkov.godfather.context.repository.preser;

import dev.struchkov.godfather.context.service.save.Pusher;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AnswerSaveMapPreservable<S> implements AnswerSavePreservable<S> {

    private final Map<Long, Map<String, S>> saveMap = new HashMap<>();

    @Override
    public void save(Long personId, String key, S save) {
        saveMap.computeIfAbsent(personId, k -> new HashMap<>()).put(key, save);
    }

    @Override
    public Optional<S> getByKey(Long personId, String key) {
        if (saveMap.containsKey(personId)
                && saveMap.get(personId).containsKey(key)) {
            return Optional.of(saveMap.get(personId).get(key));
        }
        return Optional.empty();
    }

    @Override
    public Map<String, S> getAllSaveElement(Long personId) {
        return saveMap.get(personId);
    }

    @Override
    public void push(Long personId, Pusher<S> pusher) {
        Optional.ofNullable(pusher).ifPresent(sPusher -> sPusher.push(getAllSaveElement(personId)));
    }

}
