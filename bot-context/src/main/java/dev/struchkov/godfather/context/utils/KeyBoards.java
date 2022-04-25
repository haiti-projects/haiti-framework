package dev.struchkov.godfather.context.utils;

import dev.struchkov.godfather.context.domain.keyboard.button.SimpleButton;
import dev.struchkov.godfather.context.domain.keyboard.simple.SimpleKeyBoard;
import dev.struchkov.godfather.context.domain.keyboard.simple.SimpleKeyBoardLine;

import java.util.Arrays;
import java.util.List;

/**
 * Используется для быстрого создания клавиаутр {@link SimpleKeyBoard}.
 *
 * @author upagge [08/07/2019]
 */
public class KeyBoards {

    public static final SimpleButton YES_BUTTON = SimpleButton.of("Да", "{\"button\": \"yes\"}");
    public static final SimpleButton NO_BUTTON = SimpleButton.of("Нет", "{\"button\": \"no\"}");

    private KeyBoards() {
        throw new IllegalStateException();
    }

    /**
     * Возвращает клавиатуру формата 1х2, с кнопками "Да | Нет"
     *
     * @return {@link SimpleKeyBoard}
     */
    public static SimpleKeyBoard keyBoardYesNo() {
        return SimpleKeyBoard.simpleBuilder().line(
                SimpleKeyBoardLine.simpleBuilder().button(YES_BUTTON).button(NO_BUTTON).simpleBuild()
        ).simpleBuild();
    }

    /**
     * Возвращает клавиатуру формата 1хN, где N - это количество элементов в переданном списке
     *
     * @param labelButtons Список названий для кнопок
     * @return {@link SimpleKeyBoard}
     */
    public static SimpleKeyBoard verticalMenuString(List<String> labelButtons) {
        final SimpleKeyBoard.SimpleKeyBoardBuilder keyBoard = SimpleKeyBoard.simpleBuilder();
        for (String labelButton : labelButtons) {
            final SimpleButton simpleButton = SimpleButton.of(labelButton, "{\"button\": \"" + labelButton + "\"}");
            keyBoard.line(SimpleKeyBoardLine.simpleBuilder().button(simpleButton).simpleBuild());
        }
        return keyBoard.simpleBuild();
    }

    /**
     * Возвращает клавиатуру формата 1хN, где N - это количество элементов в переданном списке
     *
     * @param labelButton Список названий для кнопок
     * @return {@link SimpleKeyBoard}
     */
    public static SimpleKeyBoard verticalMenuString(String... labelButton) {
        return verticalMenuString(Arrays.asList(labelButton));
    }

    /**
     * Возвращает клавиатуру формата 2х(N/2), где N - это количество элементов в переданном списке
     *
     * @param labelButton Список названий для кнопок
     * @return {@link SimpleKeyBoard}
     */
    public static SimpleKeyBoard verticalDuoMenuString(String... labelButton) {
        return verticalDuoMenuString(Arrays.asList(labelButton));
    }

    /**
     * Возвращает клавиатуру формата 2х(N/2), где N - это количество элементов в переданном списке
     *
     * @param labelButton Список названий для кнопок
     * @return {@link SimpleKeyBoard}
     */
    public static SimpleKeyBoard verticalDuoMenuString(List<String> labelButton) {
        final SimpleKeyBoard.SimpleKeyBoardBuilder keyBoard = SimpleKeyBoard.simpleBuilder();
        boolean flag = true;
        SimpleKeyBoardLine.SimpleKeyBoardLineBuilder keyBoardLine = SimpleKeyBoardLine.simpleBuilder();
        for (int i = 0; i <= labelButton.size() - 1; i++) {
            String label = labelButton.get(i);
            keyBoardLine.button(SimpleButton.of(label));
            if (flag) {
                if (i == labelButton.size() - 1) {
                    keyBoard.line(keyBoardLine.simpleBuild());
                } else {
                    flag = false;
                }
            } else {
                keyBoard.line(keyBoardLine.simpleBuild());
                keyBoardLine = SimpleKeyBoardLine.simpleBuilder();
                flag = true;
            }
        }
        return keyBoard.simpleBuild();
    }

    /**
     * Возвращает клавиатуру формата 1xN сформированную из списка кнопок, где N - количество кнопок в списке
     *
     * @param simpleButtons Список кнопок
     * @return {@link SimpleKeyBoard}
     */
    public static SimpleKeyBoard verticalMenuButton(List<SimpleButton> simpleButtons) {
        final SimpleKeyBoard.SimpleKeyBoardBuilder keyBoard = SimpleKeyBoard.simpleBuilder();
        for (SimpleButton simpleButton : simpleButtons) {
            keyBoard.line(SimpleKeyBoardLine.simpleBuilder().button(simpleButton).simpleBuild());
        }
        return keyBoard.simpleBuild();
    }

    /**
     * Возвращает клавиатуру из одной кнопки
     *
     * @param simpleButton Кнопка
     * @return {@link SimpleKeyBoard}
     */
    public static SimpleKeyBoard singleton(SimpleButton simpleButton) {
        final SimpleKeyBoardLine line = SimpleKeyBoardLine.simpleBuilder().button(simpleButton).simpleBuild();
        return SimpleKeyBoard.simpleBuilder().line(line).simpleBuild();
    }

}
