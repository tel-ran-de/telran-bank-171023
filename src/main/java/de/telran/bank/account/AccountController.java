package de.telran.bank.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountBalanceStorage accountBalanceStorage;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ResponseBody
    public String accountInfo() {
        return "I'm some account";
    }

    @RequestMapping(value = "/account/balance", method = RequestMethod.GET)
    @ResponseBody
    public String accountBalancer(@RequestParam("accountId") long accountId, @RequestParam("accountType") AccountTypes accountType) {
        BigDecimal balance = accountBalanceStorage.getBalance(accountId, accountType);
        return "The balance for accountId = " + accountId + " is " + balance + " on " + accountType.getValue();
    }

    @RequestMapping(value = "/account/balance/topup", method = RequestMethod.POST)
    @ResponseBody
    public String topupAccountBalance(@RequestParam("accountId") long accountId,
                                      @RequestParam("accountType") AccountTypes accountType,
                                      @RequestParam("amount") BigDecimal amount) {
        accountBalanceStorage.addToAccount(accountId, accountType, amount);
        return "The balance is updated";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld() {
        return "Hello world";
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ResponseBody
    public String status() {
        return "ok";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    @ResponseBody
    public String createAccount() {
        Account account = accountRepository.save(new Account());
        return String.valueOf(account.getId());
    }


}
