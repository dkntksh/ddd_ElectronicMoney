package org.example.wallet.model;

/**
 * チャージ時に発生する例外
 */
public class ChargeAmountException extends RuntimeException {

    public ChargeAmountException(String message) {
        super(message);
    }
}
