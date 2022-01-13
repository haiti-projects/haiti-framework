package dev.struchkov.haiti.context.page;

import java.util.Set;

public interface Pagination {

    Integer getPage();

    Integer getSize();

    Set<? extends Sort>  getSorts();

}
