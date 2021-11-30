package dev.struchkov.godfather.context.utils;

import dev.struchkov.godfather.context.domain.keyboard.ButtonColor;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoard;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardLine;
import dev.struchkov.godfather.context.domain.keyboard.button.KeyBoardButtonText;

import java.util.Arrays;
import java.util.List;

/**
 * Используется для быстрого создания клавиаутр {@link KeyBoard}.
 *
 * @author upagge [08/07/2019]
 */
public class KeyBoards {

    private KeyBoards() {
        throw new IllegalStateException();
    }

    /**
     * Возвращает клавиатуру формата 1х2, с кнопками "Да | Нет"
     *
     * @return {@link KeyBoard}
     */
    public static KeyBoard keyBoardYesNo() {
        KeyBoardButton yesButton = KeyBoardButtonText.builder().color(ButtonColor.POSITIVE).label("Да").payload("{\"button\": \"yes\"}").build();
        KeyBoardButton noButton = KeyBoardButtonText.builder().color(ButtonColor.NEGATIVE).label("Нет").payload("{\"button\": \"no\"}").build();
        KeyBoardLine keyBoardLine = KeyBoardLine.builder().buttonKeyBoard(yesButton).buttonKeyBoard(noButton).build();
        return KeyBoard.builder().lineKeyBoard(keyBoardLine).oneTime(true).build();
    }

    /**
     * Возвращает клавиатуру формата 1хN, где N - это количество элементов в переданном списке
     *
     * @param labelButtons Список названий для кнопок
     * @return {@link KeyBoard}
     */
    public static KeyBoard verticalMenuString(List<String> labelButtons) {
        KeyBoard.KeyBoardBuilder keyBoard = KeyBoard.builder().oneTime(true);
        for (String labelButton : labelButtons) {
            KeyBoardButton keyBoardButton = KeyBoardButtonText.builder().label(labelButton).payload("{\"button\": \"" + labelButton + "\"}").build();
            keyBoard.lineKeyBoard(KeyBoardLine.builder().buttonKeyBoard(keyBoardButton).build());
        }
        return keyBoard.build();
    }

    /**
     * Возвращает клавиатуру формата 1хN, где N - это количество элементов в переданном списке
     *
     * @param labelButton Список названий для кнопок
     * @return {@link KeyBoard}
     */
    public static KeyBoard verticalMenuString(String... labelButton) {
        return verticalMenuString(Arrays.asList(labelButton));
    }

    /**
     * Возвращает клавиатуру формата 2х(N/2), где N - это количество элементов в переданном списке
     *
     * @param labelButton Список названий для кнопок
     * @return {@link KeyBoard}
     */
    public static KeyBoard verticalDuoMenuString(String... labelButton) {
        return verticalDuoMenuString(Arrays.asList(labelButton));
    }

    /**
     * Возвращает клавиатуру формата 2х(N/2), где N - это количество элементов в переданном списке
     *
     * @param labelButton Список названий для кнопок
     * @return {@link KeyBoard}
     */
    public static KeyBoard verticalDuoMenuString(List<String> labelButton) {
        KeyBoard.KeyBoardBuilder keyBoard = KeyBoard.builder().oneTime(true);
        boolean flag = true;
        KeyBoardLine.KeyBoardLineBuilder keyBoardLine = KeyBoardLine.builder();
        for (int i = 0; i <= labelButton.size() - 1; i++) {
            String label = labelButton.get(i);
            if (flag) {
                keyBoardLine.buttonKeyBoard(KeyBoardButtonText.of(label));
                if (i == labelButton.size() - 1) {
                    keyBoard.lineKeyBoard(keyBoardLine.build());
                } else {
                    flag = false;
                }
            } else {
                keyBoardLine.buttonKeyBoard(KeyBoardButtonText.of(label));
                keyBoard.lineKeyBoard(keyBoardLine.build());
                keyBoardLine = KeyBoardLine.builder();
                flag = true;
            }
        }
        return keyBoard.build();
    }

    /**
     * Возвращает клавиатуру формата 1xN сформированную из списка кнопок, где N - количество кнопок в списке
     *
     * @param keyBoardButtons Список кнопок
     * @return {@link KeyBoard}
     */
    public static KeyBoard verticalMenuButton(List<KeyBoardButton> keyBoardButtons) {
        KeyBoard.KeyBoardBuilder keyBoard = KeyBoard.builder().oneTime(true);
        for (KeyBoardButton keyBoardButton : keyBoardButtons) {
            keyBoard.lineKeyBoard(KeyBoardLine.builder().buttonKeyBoard(keyBoardButton).build());
        }
        return keyBoard.build();
    }

    /**
     * Возвращает клавиатуру из одной кнопки
     *
     * @param keyBoardButton Кнопка
     * @return {@link KeyBoard}
     */
    public static KeyBoard singelton(KeyBoardButton keyBoardButton) {
        KeyBoardLine line = KeyBoardLine.builder().buttonKeyBoard(keyBoardButton).build();
        return KeyBoard.builder().lineKeyBoard(line).build();
    }
}
