package de.telran.bank;

import de.telran.bank.account.AccountBalanceStorage;
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

@SuppressWarnings("ConstantValue")
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountBalanceStorage accountBalanceStorage;

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
    void shouldCheckBalanceInStorage() {
        Assertions.assertEquals(accountBalanceStorage.getBalance("3", AccountTypes.CHECKING_ACCOUNT), BigDecimal.valueOf(0));
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
        mockMvc.perform(MockMvcRequestBuilders.get("/account/balance")
                        .queryParam("accountId","3")
                        .queryParam("accountType", String.valueOf(AccountTypes.CHECKING_ACCOUNT)))
                .andExpect(content().string("The balance for accountId = 3 is 0 on checkingAccount"));
    }
    @Test
    void shouldGetDefaultAccountBalance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/balance")
                        .queryParam("accountId","1")
                        .queryParam("accountType",String.valueOf(AccountTypes.SAVING_ACCOUNT)))
                .andExpect(content().string("The balance for accountId = 1 is 0 on savingAccount"));
    }

    @Test
    void shouldTopUpAccountBalance() throws Exception {
        //given
        BigDecimal amount = new BigDecimal("10.23");
        String accountId = "1";
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/account/balance/topup")
                        .queryParam("accountId",accountId)
                        .queryParam("amount", String.valueOf(amount))
                        .queryParam("accountType",String.valueOf(AccountTypes.SAVING_ACCOUNT)))
                .andExpect(content().string("The balance is updated"));

        mockMvc.perform(MockMvcRequestBuilders.post("/account/balance/topup")
                        .queryParam("accountId",accountId)
                        .queryParam("amount", String.valueOf(amount))
                        .queryParam("accountType",String.valueOf(AccountTypes.SAVING_ACCOUNT)))
                .andExpect(content().string("The balance is updated"));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/account/balance")
                        .queryParam("accountId",accountId)
                        .queryParam("accountType",String.valueOf(AccountTypes.SAVING_ACCOUNT)))
                .andExpect(content().string("The balance for accountId = 1 is " +  amount.add(amount) + " on savingAccount"));
    }

}
