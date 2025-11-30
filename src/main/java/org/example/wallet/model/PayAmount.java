package org.example.wallet.model;

import java.util.Objects;

/**
 * 支払い金額
 */
public class PayAmount {

    private final Money amount;

    public PayAmount(long amount) {
        if (amount <= 0) {
            throw new InsufficientBalanceException("支払額は正の数である必要があります");
        }
        this.amount = new Money(amount);
    }

    public Money getMoney() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayAmount)) return false;
        PayAmount that = (PayAmount) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    @Override
    public String toString() {
        return "PayAmount{" + "amount=" + amount + '}';
    }
}