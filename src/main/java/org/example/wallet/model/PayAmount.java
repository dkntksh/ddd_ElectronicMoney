package org.example.wallet.model;

/**
 * 支払い金額
 */
public class PayAmount {

    private final int amount;

    public PayAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
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
        return Integer.hashCode(amount);
    }

    @Override
    public String toString() {
        return "PayAmount{" + "amount=" + amount + '}';
    }
}