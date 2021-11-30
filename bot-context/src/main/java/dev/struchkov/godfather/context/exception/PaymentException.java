package dev.struchkov.godfather.context.exception;

/**
 * Ошибка оплаты.
 *
 * @author upagge [08/07/2019]
 */
public class PaymentException extends AppBotException {

    public PaymentException(String message) {
        super(message);
    }

}
