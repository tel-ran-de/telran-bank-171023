package de.telran.bank.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    @Query(value = "select balance from account where id = ?1 and type = ?2 limit 1", nativeQuery = true)
    BigDecimal findByIdAndType(long id, String type);

    @Modifying
    @Query(value = "update  account set balance = balance + ?3 where id = ?1 and type = ?2", nativeQuery = true)
    void addToAccount(long id, String type, BigDecimal amount);
}
