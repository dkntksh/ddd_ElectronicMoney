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
    private long balanceAmount; // 内部では long で管理（0円可能）

    /**
     * コンストラクタ
     */
    public Wallet() {
        this.id = UUID.randomUUID();
        this.balanceAmount = 0; // 0円スタート可能
    }

    public UUID getId() {
        return id;
    }

    /**
     * 残高照会
     * @return 残高
     */
    public Money getBalance() {
        return balanceAmount == 0 ? null : new Money(balanceAmount);
    }

    /**
     * チャージ
     * @param amount チャージ金額
     */
    public void charge(ChargeAmount amount) {
        this.balanceAmount += amount.getMoney().getAmount(); // Money の amount を加算
    }

    /**
     * 支払う
     * @param amount 支払金額
     */
    public void pay(PayAmount amount) {
        if (amount.getMoney().getAmount() > balanceAmount) {
            throw new IllegalArgumentException("残高不足です");
        }
        this.balanceAmount -= amount.getMoney().getAmount();
    }

}
