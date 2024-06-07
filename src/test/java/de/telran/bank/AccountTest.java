package de.telran.bank;

import de.telran.bank.account.Account;
import de.telran.bank.account.AccountBalanceStorage;
import de.telran.bank.account.AccountRepository;
import de.telran.bank.account.AccountTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("ConstantValue")
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountBalanceStorage accountBalanceStorage;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldCheckThatAccountUrlReturnsString() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account"))
                .andExpect(content().string("I'm some account"));
    }

    @Test
    void shouldReturnHelloFromHelloUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(content().string("Hello world"));
    }

    @Test
    void shouldReturnOkOnStatusRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/status"))
                .andExpect(content().string("ok"));
    }

    @Test
    void shouldCheckIfANumberIsEven() {
        // given
        int a = 4;
        // when
        boolean isEven = a % 2 == 0;
        // then
        Assertions.assertTrue(isEven);
    }

    @Test
    void shouldGetAccountBalance() throws Exception {
        // given
        Account account = new Account();
        account.setType("checkingAccount");
        account.setBalance(BigDecimal.TEN);
        account = accountRepository.save(account);
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/account/balance")
                        .queryParam("accountId", String.valueOf(account.getId()))
                        .queryParam("accountType", String.valueOf(AccountTypes.CHECKING_ACCOUNT)))
                .andExpect(content().string("The balance for accountId = " + account.getId() + " is 10 on checkingAccount"));
    }

    @Test
    void shouldGetDefaultAccountBalance() throws Exception {
        Account account = new Account();
        account.setType("savingAccount");
        account.setBalance(BigDecimal.ZERO);
        account = accountRepository.save(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/balance")
                        .queryParam("accountId", String.valueOf(account.getId()))
                        .queryParam("accountType", String.valueOf(AccountTypes.SAVING_ACCOUNT)))
                .andExpect(content().string("The balance for accountId = " + account.getId() + " is 0 on savingAccount"));
    }

    @Test
    void shouldTopUpAccountBalance() throws Exception {
        //given
        Account account = new Account();
        account.setType("savingAccount");
        account.setBalance(BigDecimal.ZERO);
        account = accountRepository.save(account);

        BigDecimal amount = new BigDecimal("10.23");
        String accountId = String.valueOf(account.getId());
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/account/balance/topup")
                        .queryParam("accountId", accountId)
                        .queryParam("amount", String.valueOf(amount))
                        .queryParam("accountType", String.valueOf(AccountTypes.SAVING_ACCOUNT)))
                .andExpect(content().string("The balance is updated"));

        mockMvc.perform(MockMvcRequestBuilders.post("/account/balance/topup")
                        .queryParam("accountId", accountId)
                        .queryParam("amount", String.valueOf(amount))
                        .queryParam("accountType", String.valueOf(AccountTypes.SAVING_ACCOUNT)))
                .andExpect(content().string("The balance is updated"));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/account/balance")
                        .queryParam("accountId", accountId)
                        .queryParam("accountType", String.valueOf(AccountTypes.SAVING_ACCOUNT)))
                .andExpect(content().string("The balance for accountId = " + accountId + " is " + amount.add(amount) + " on savingAccount"));
    }

    @Test
    void shouldCreateAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/account"))
                .andExpect(status().is(200));

    }


}
