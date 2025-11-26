package org.example.wallet.model;

import java.util.Objects;

/**
 * チャージ金額
 */
public class ChargeAmount {

    /**
     * チャージ量
     */
    final private int amount;

    /**
     * コンストラクタ
     * 0円未満、10000円より大きい場合、例外を投げる
     * @param amount チャージする金額
     *
     */
    public ChargeAmount(int amount) throws ChargeAmountException {
        if(amount <= 0 || amount > 10000) {
            throw new ChargeAmountException("チャージ金額は1円以上10,000円以下でなければなりません: " + amount);
        }
        this.amount = amount;
    }

    public int getAmount(){
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChargeAmount)) return false;
        ChargeAmount that = (ChargeAmount) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "ChargeAmount{" +
                "amount=" + amount +
                '}';
    }
}
