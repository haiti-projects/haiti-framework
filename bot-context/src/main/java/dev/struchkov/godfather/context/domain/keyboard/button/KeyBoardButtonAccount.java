package dev.struchkov.godfather.context.domain.keyboard.button;

import dev.struchkov.godfather.context.domain.keyboard.ButtonType;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Кнопка клавиатуры для оплаты счета.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class KeyBoardButtonAccount extends KeyBoardButton {

    /**
     * Сумма к оплате.
     */
    private Integer amount;

    /**
     * Идентификатор счета.
     */
    private Integer accountId;

    /**
     * Описание.
     */
    private String description;

    @Builder
    private KeyBoardButtonAccount(String payload, Integer amount, Integer accountId, String description) {
        super(payload, ButtonType.ACCOUNT);
        this.amount = amount;
        this.accountId = accountId;
        this.description = description;
    }

}
