package org.sadtech.haiti.core.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.sadtech.haiti.context.page.Sheet;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class SheetImpl<T> implements Sheet<T> {

    private final int number;
    private final int size;
    private final long totalElement;
    private final int totalPage;
    private final List<T> content;

    @Override
    public boolean hasContent() {
        return content != null && !content.isEmpty();
    }

    @Override
    public <U> Sheet<U> map(Function<? super T, ? extends U> function) {
        return new SheetImpl<>(
                this.number,
                this.size,
                this.totalElement,
                this.totalPage,
                this.content.stream().map(function).collect(Collectors.toList())
        );
    }


}
