package de.telran.bank;

import de.telran.bank.account.AccountBalanceStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
        Assertions.assertEquals(accountBalanceStorage.getBalance("3"), 1235);
    }

    @Test
    void shouldCheckIfANumberIsEven() {
        // given
        int a = 4;
        // when
        boolean isEven = a % 2 == 0;
        // then
//        if (isEven) {
//            System.out.println("Yes");
//        } else {
////            System.out.println("No");
//            throw new RuntimeException("No");
//        }
        Assertions.assertTrue(isEven);
    }
}
