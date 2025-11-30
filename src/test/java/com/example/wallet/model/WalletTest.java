package com.example.wallet.model;

import org.example.wallet.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    @Test
    void testInitialBalance() {
        Wallet wallet = new Wallet();
        assertNull(wallet.getBalance(), "初期残高は0円なのでnull");
    }

    @Test
    void testCharge() {
        Wallet wallet = new Wallet();
        ChargeAmount charge = new ChargeAmount(5000);
        wallet.charge(charge);

        assertEquals(5000, wallet.getBalance().getAmount());
    }

    @Test
    void testPayNormal() {
        Wallet wallet = new Wallet();
        wallet.charge(new ChargeAmount(5000));
        PayAmount pay = new PayAmount(3000);
        wallet.pay(pay);

        assertEquals(2000, wallet.getBalance().getAmount());
    }

    @Test
    void testPayInsufficientBalance() {
        Wallet wallet = new Wallet();
        wallet.charge(new ChargeAmount(2000));
        PayAmount pay = new PayAmount(3000);

        assertThrows(IllegalArgumentException.class, () -> wallet.pay(pay));
    }

    @Test
    void testChargeAmountLimit() {
        assertThrows(ChargeAmountException.class, () -> new ChargeAmount(10_001));
        assertThrows(ChargeAmountException.class, () -> new ChargeAmount(0));
    }

    @Test
    void testPayAmountPositive() {
        assertThrows(InsufficientBalanceException.class, () -> new PayAmount(0));
        assertThrows(InsufficientBalanceException.class, () -> new PayAmount(-10));
    }
}
