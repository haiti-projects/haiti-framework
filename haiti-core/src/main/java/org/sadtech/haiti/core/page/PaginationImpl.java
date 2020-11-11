package org.sadtech.haiti.core.page;

import lombok.Getter;
import org.sadtech.haiti.context.enums.TypeSort;
import org.sadtech.haiti.context.page.Pagination;

@Getter
public class PaginationImpl implements Pagination {

    private final int page;
    private final int size;
    private String fieldSort;
    private TypeSort typeSort;

    private PaginationImpl(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PaginationImpl(int page, int size, String fieldSort, TypeSort typeSort) {
        this.page = page;
        this.size = size;
        this.fieldSort = fieldSort;
        this.typeSort = typeSort;
    }

    public static Pagination of(int page, int size) {
        return new PaginationImpl(page, size);
    }

    public static Pagination of(int page, int size, String fieldSort, TypeSort typeSort) {
        return new PaginationImpl(page, size, fieldSort, typeSort);
    }

}
