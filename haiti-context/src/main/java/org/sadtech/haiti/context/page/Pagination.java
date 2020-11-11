package org.sadtech.haiti.context.page;

import org.sadtech.haiti.context.enums.TypeSort;

public interface Pagination {

    int getPage();

    int getSize();

    TypeSort getTypeSort();

    String getFieldSort();

}
