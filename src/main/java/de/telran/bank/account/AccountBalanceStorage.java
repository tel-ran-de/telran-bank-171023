package de.telran.bank.account;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountBalanceStorage {

    private final Map<String, Integer> accountToBalance = new HashMap<>();

    public AccountBalanceStorage() {
        accountToBalance.put("3", 1235);
    }

    public int getBalance(String account, String accountType) {
        return accountToBalance.getOrDefault(account, 0);
    }

    public void addToAccount(String account, String accountType, BigDecimal amount) {

    }
}
