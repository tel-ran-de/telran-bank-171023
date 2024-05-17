package de.telran.bank;

import de.telran.bank.account.AccountBalanceStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleTest {

    @Autowired
    private AccountBalanceStorage accountBalanceStorage;

    @Test
    void shouldSumTwoNumbers() {
        accountBalanceStorage.getBalance("100", accountType);
        Assertions.assertEquals(2, 1 + 1);
    }
}
