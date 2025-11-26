package com.example.wallet.model;

import org.example.wallet.model.ChargeAmount;
import org.example.wallet.model.ChargeAmountException;
import org.example.wallet.model.Wallet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ウォレットのテスト
 */
public class WalletTest {

    @Test
    /**
     * 5000円チャージして、残高が5000円であること
     */
    void charge_increasesBalance() {
        Wallet wallet = new Wallet();
        ChargeAmount amount = new ChargeAmount(5000);

        wallet.charge(amount);

        assertEquals(5000, wallet.getBalance());
    }

    @Test
    /**
     * 0円以下だと例外が投げられること
     */
    void chargeAmount_zeroOrNegative_throwsException() {
        Exception exception = assertThrows(
                ChargeAmountException.class,
                () -> new ChargeAmount(0)
        );

        assertEquals("チャージ金額は1円以上10,000円以下でなければなりません: 0",
                exception.getMessage());

        // 負の値も同様にチェック
        Exception negativeException = assertThrows(
                ChargeAmountException.class,
                () -> new ChargeAmount(-1)
        );

        assertEquals("チャージ金額は1円以上10,000円以下でなければなりません: -1",
                negativeException.getMessage());
    }

    @Test
    /**
     * 1万円を超えると例外を投げる
     */
    void chargeAmount_exceedsMaximum_throwsException() {
        Exception exception = assertThrows(
                ChargeAmountException.class,
                () -> new ChargeAmount(10001)
        );

        assertEquals("チャージ金額は1円以上10,000円以下でなければなりません: 10001",
                exception.getMessage());
    }
}
