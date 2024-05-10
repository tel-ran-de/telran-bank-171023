package de.telran.bank.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountBalanceStorage accountBalanceStorage;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ResponseBody
    public String accountInfo() {
        return "I'm some account";
    }

    @RequestMapping(value = "/account/balance", method = RequestMethod.GET)
    @ResponseBody
    public String accountBalancer(@RequestParam("accountId") String accountId, @RequestParam("accountType") String accountType) {
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(List.of(1, 2, 3));
        int balance = accountBalanceStorage.getBalance(accountId);
        return "The balance for accountId = " + accountId + " is " + balance + " on " + accountType;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld() {
        return "Hello world";
    }
}
