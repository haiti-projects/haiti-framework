package dev.struchkov.haiti.core.repository;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.SimpleManagerRepository;
import dev.struchkov.haiti.utils.Assert;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static dev.struchkov.haiti.utils.Strings.ERR_OPERATION_NOT_SUPPORTED;

public class SimpleManagerRepositoryMap<T extends BasicEntity<Long>> implements SimpleManagerRepository<T, Long> {

    protected final Map<Long, T> map = new HashMap<>();
    protected long key = 0;

    @Override
    public T save(T accessTarget) {
        Assert.isNotNull(accessTarget);
        accessTarget.setId(key);
        map.put(key, accessTarget);
        key++;
        return accessTarget;
    }

    @Override
    public Optional<T> findById(Long id) {
        Assert.isNotNull(id);
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        Assert.isNotNull(id);
        return map.containsKey(id);
    }

    @Override
    public void deleteById(Long id) {
        Assert.isNotNull(id);
        map.remove(id);
    }

    @Override
    public List<T> saveAll(Collection<T> accessTargets) {
        Assert.isNotNull(accessTargets);
        return accessTargets.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllById(Collection<Long> accessTargets) {
        Assert.isNotNull(accessTargets);
        accessTargets.forEach(map::remove);
    }

    @Override
    public List<T> findAllById(Collection<Long> ids) {
        Assert.isNotNull(ids);
        return ids.stream()
                .map(map::get)
                .collect(Collectors.toList());
    }

    @Override
    public Sheet<T> findAll(Pagination pagination) {
        throw new IllegalStateException(ERR_OPERATION_NOT_SUPPORTED);
    }

}
