package de.telran.bank.account;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountBalanceStorage {

    private final Map<String, Integer> accountToBalance = new HashMap<>();

    public AccountBalanceStorage() {
        accountToBalance.put("3", 1235);
    }

    public int getBalance(String account) {
        return accountToBalance.getOrDefault(account, 0);
    }
}
