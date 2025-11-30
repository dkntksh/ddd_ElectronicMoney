package org.example.wallet.model;

import java.util.Objects;

/**
 * お金
 */
public final class Money {

    private final long amount;

    /**
     *
     * @param amount 1円以上の金額
     */
    public Money(long amount) {
        if (amount < 1) {  // 1円未満は不可
            throw new IllegalArgumentException("金額は1円以上である必要があります");
        }
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount + "円";
    }
}