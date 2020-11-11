package org.sadtech.haiti.database.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.database.util.Converter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationJpa {

    public static <T, K> T save(JpaRepository<T, K> jpaRepository, T entity) {
        return jpaRepository.save(entity);
    }

    public static <T, K> Optional<T> findById(JpaRepository<T, K> jpaRepository, K primaryKey) {
        return jpaRepository.findById(primaryKey);
    }

    public static <K, T> boolean existsById(JpaRepository<T, K> jpaRepository, K primaryKey) {
        return jpaRepository.existsById(primaryKey);
    }

    public static <K, T> void deleteById(JpaRepository<T, K> jpaRepository, K primaryKey) {
        jpaRepository.deleteById(primaryKey);
    }

    public static <T, K> Sheet<T> findAll(JpaRepository<T, K> jpaRepository, Pagination pagination) {
        return Converter.page(
                jpaRepository.findAll(
                        Converter.pagination(pagination)
                )
        );
    }

    public static <T, K> List<T> saveAll(JpaRepository<T, K> jpaRepository, Collection<T> entities) {
        return jpaRepository.saveAll(entities);
    }

    public static <K, T> void deleteAllById(JpaRepository<T, K> jpaRepository, Collection<K> primaryKeys) {
        primaryKeys.forEach(jpaRepository::deleteById);
    }

    public static <T, K> Optional<T> findFirst(JpaRepositoryImplementation<T, K> jpaRepository, Specification<T> specification) {
        return jpaRepository.findOne(specification);
    }

    public static <K, T> boolean exists(JpaRepositoryImplementation<T, K> jpaRepository, Specification<T> specification) {
        return jpaRepository.count(specification) > 0;
    }

    public static <T, K> List<T> findAll(JpaRepositoryImplementation<T, K> jpaRepository, Specification<T> specification) {
        return jpaRepository.findAll(specification);
    }

    public static <T, K> Sheet<T> findAll(JpaRepositoryImplementation<T, K> jpaRepository, Specification<T> specification, Pagination pagination) {
        return Converter.page(
                jpaRepository.findAll(specification, Converter.pagination(pagination))
        );
    }

    public static <T, K> long count(JpaRepositoryImplementation<T, K> jpaRepository, Specification<T> specification) {
        return jpaRepository.count(specification);
    }

}
