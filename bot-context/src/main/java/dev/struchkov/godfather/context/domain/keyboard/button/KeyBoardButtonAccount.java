package dev.struchkov.godfather.context.domain.keyboard.button;

import dev.struchkov.godfather.context.domain.keyboard.ButtonType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import dev.struchkov.godfather.context.utils.Description;

/**
 * Кнопка клавиатуры для оплаты счета.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class KeyBoardButtonAccount extends KeyBoardButton {

    @Description("Сумма к оплате")
    private Integer amount;

    @Description("Идентификатор счета")
    private Integer accountId;

    @Description("Описание")
    private String description;

    @Builder
    private KeyBoardButtonAccount(String payload, Integer amount, Integer accountId, String description) {
        super(payload, ButtonType.ACCOUNT);
        this.amount = amount;
        this.accountId = accountId;
        this.description = description;
    }

}
