package com.akila.demo.constant;

/**
 * @author Phuc Nguyen
 */
public final class MessageConstant {

    private MessageConstant() {
    }

    // Error message
    public static final String MSG_INVALID_CURRENCY = "The system don't support with currency code %s";
    public static final String MSG_NOT_FOUND_ORG_ACCOUNT = "Can not found the original account with id: %s";
    public static final String MSG_NOT_FOUND_DES_ACCOUNT = "Can not found the account destination with id: %s";

    public static final String MSG_MISMATCH_CURRENCY = "Mismatch the currency unit between two wallets";
    public static final String MSG_NOT_ENOUGH_MONEY = "The wallet %s don't have enough money to transfer";
    public static final String MSG_TRANSFER_PROCESS_ERROR = "Transfer process error";

    // Success message
    public static final String MSG_TRANSFER_SUCCESS = "Transfer successful %s (%s) from %s to %s";


}
