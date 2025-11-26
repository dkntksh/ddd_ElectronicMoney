package org.example.wallet.model;

/**
 * 残高不足チェック時の例外
 */
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
