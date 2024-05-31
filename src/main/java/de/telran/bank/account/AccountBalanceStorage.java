package de.telran.bank.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountBalanceStorage {
    @Autowired
    private AccountRepository accountRepository;

    public synchronized BigDecimal getBalance(long accountId, AccountTypes accountType) {
        return accountRepository.findByIdAndType(accountId, accountType.getValue());
    }

    public synchronized void addToAccount(long accountId, AccountTypes accountType, BigDecimal amount) {
        accountRepository.addToAccount(accountId, accountType.getValue(), amount);
    }

}
