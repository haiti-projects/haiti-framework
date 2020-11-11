package org.sadtech.haiti.database.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.sadtech.haiti.context.enums.TypeSort;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.core.page.SheetImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Converter {

    public static Pageable pagination(@NonNull Pagination pagination) {
        if (pagination.getTypeSort() != null) {
            return PageRequest.of(
                    pagination.getPage(),
                    pagination.getSize(),
                    TypeSort.ASC.equals(pagination.getTypeSort()) ? Sort.Direction.ASC : Sort.Direction.DESC,
                    pagination.getFieldSort()
            );
        }
        return PageRequest.of(pagination.getPage(), pagination.getSize());
    }

    public static <T> Sheet<T> page(@NonNull Page<T> page) {
        return SheetImpl.<T>builder()
                .totalPage(page.getTotalPages())
                .totalElement(page.getTotalElements())
                .size(page.getSize())
                .number(page.getNumber())
                .content(page.getContent())
                .build();
    }

}
