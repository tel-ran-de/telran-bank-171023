package de.telran.bank.account;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountBalanceStorage {

    private final Map<String, Balance> accountToBalance = new HashMap<>();


    public AccountBalanceStorage() {
        accountToBalance.put("1", new Balance());
        accountToBalance.put("3", new Balance());
    }


    public synchronized BigDecimal getBalance(String account, AccountTypes accountType) {
        return accountToBalance.get(account).getAccountTypeToBalance().getOrDefault(accountType, BigDecimal.valueOf(0));
    }

    public synchronized void addToAccount(String account, AccountTypes accountType, BigDecimal amount) {
        BigDecimal temp = accountToBalance.get(account).getAccountTypeToBalance().get(accountType);
        accountToBalance.get(account).getAccountTypeToBalance().put(accountType, temp.add(amount));
    }
}
