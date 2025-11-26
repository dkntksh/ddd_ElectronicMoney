package com.example.wallet.model;

import org.example.wallet.model.*;
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


    /**
     * 支払いの正常系
     */
    @Test
    void pay_decreasesBalance_whenSufficientBalance() {
        Wallet wallet = new Wallet();
        wallet.charge(new ChargeAmount(5000)); // 残高 5000

        PayAmount payment = new PayAmount(3000);
        wallet.pay(payment);

        assertEquals(2000, wallet.getBalance());
    }

    /**
     * 残高により失敗するケース
     */
    @Test
    void pay_throwsException_whenInsufficientBalance() {
        // 残高 0 の Wallet
        Wallet emptyWallet = new Wallet();
        PayAmount payment = new PayAmount(1000);

        Exception ex1 = assertThrows(
                InsufficientBalanceException.class,
                () -> emptyWallet.pay(payment)
        );
        assertEquals("残高不足: 支払額 1000 が残高 0 を超えています", ex1.getMessage());

        // 残高 < 支払額 の Wallet
        Wallet wallet = new Wallet();
        wallet.charge(new ChargeAmount(2000)); // 残高 2000
        PayAmount overPayment = new PayAmount(3000);

        Exception ex2 = assertThrows(
                InsufficientBalanceException.class,
                () -> wallet.pay(overPayment)
        );
        assertEquals("残高不足: 支払額 3000 が残高 2000 を超えています", ex2.getMessage());
    }
}
