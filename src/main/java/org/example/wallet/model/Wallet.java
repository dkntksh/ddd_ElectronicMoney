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
}
