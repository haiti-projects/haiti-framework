package dev.struchkov.godfather.main.domain.keyboard;

import java.util.List;

public interface KeyBoard {

    List<KeyBoardLine> getLines();

    String getType();

}
