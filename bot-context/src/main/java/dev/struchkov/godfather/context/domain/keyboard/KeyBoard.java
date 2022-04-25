package dev.struchkov.godfather.context.domain.keyboard;

import java.util.List;

public interface KeyBoard {

    List<KeyBoardLine> getLines();

    String getType();

}
