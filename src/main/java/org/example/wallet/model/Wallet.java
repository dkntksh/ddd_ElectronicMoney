package org.example.wallet.model;

import java.util.UUID;

/**
 * ウォレット
 */
public class Wallet {
    /**
     * ウォレットのID:UUIDで定義する
     */
    private final UUID id;

    /**
     * 残高
     */
    private int balance;

    /**
     * コンストラクタ
     */
    public Wallet() {
        this.id = UUID.randomUUID();
        this.balance = 0;
    }

    public UUID getId() {
        return id;
    }

    /**
     * 残高照会
     * @return 残高
     */
    public int getBalance() {
        return balance;
    }

    /**
     * チャージ
     * @param amount チャージ金額
     */
    public void charge(ChargeAmount amount) {
        // ChargeAmount が保証するので追加チェックは不要
        this.balance += amount.getAmount();
    }

    /**
     * 支払う
     * @param amount 支払金額
     */
    public void pay(PayAmount amount) {
        if (amount.getAmount() > this.balance) {
            throw new InsufficientBalanceException(
                    "残高不足: 支払額 " + amount.getAmount() + " が残高 " + this.balance + " を超えています"
            );
        }
        this.balance -= amount.getAmount();
    }
}
