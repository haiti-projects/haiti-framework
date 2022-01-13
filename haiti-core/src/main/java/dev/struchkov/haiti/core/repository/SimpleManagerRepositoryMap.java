package dev.struchkov.haiti.core.repository;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.SimpleManagerRepository;
import lombok.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleManagerRepositoryMap<T extends BasicEntity<Long>> implements SimpleManagerRepository<T, Long> {

    protected final Map<Long, T> map = new HashMap<>();
    protected long key = 0;

    @Override
    public T save(@NonNull T accessTarget) {
        accessTarget.setId(key);
        map.put(key, accessTarget);
        key++;
        return accessTarget;
    }

    @Override
    public Optional<T> findById(@NonNull Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public boolean existsById(@NonNull Long id) {
        return map.containsKey(id);
    }

    @Override
    public void deleteById(@NonNull Long id) {
        map.remove(id);
    }

    @Override
    public List<T> saveAll(@NonNull Collection<T> accessTargets) {
        return accessTargets.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllById(@NonNull Collection<Long> accessTargets) {
        accessTargets.forEach(map::remove);
    }

    @Override
    public List<T> findAllById(@NonNull Collection<Long> ids) {
        return ids.stream()
                .map(map::get)
                .collect(Collectors.toList());
    }

    @Override
    public Sheet<T> findAll(@NonNull Pagination pagination) {
        return null;
    }

}
