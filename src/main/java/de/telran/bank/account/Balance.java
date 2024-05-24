package de.telran.bank.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Balance {

    private Map<AccountTypes, BigDecimal> accountTypeToBalance = new HashMap<AccountTypes, BigDecimal>();

    public Balance() {
        accountTypeToBalance.put(AccountTypes.CHECKING_ACCOUNT, BigDecimal.valueOf(0));
        accountTypeToBalance.put(AccountTypes.SAVING_ACCOUNT, BigDecimal.valueOf(0));
        accountTypeToBalance.put(AccountTypes.RETIREMENT_ACCOUNT, BigDecimal.valueOf(0));
    }

    public Map<AccountTypes, BigDecimal> getAccountTypeToBalance() {
        return accountTypeToBalance;
    }
}
