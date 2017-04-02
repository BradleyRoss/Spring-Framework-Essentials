package com.oreilly.repositories;

import com.oreilly.entities.Account;

import java.math.BigDecimal;
import java.util.List;
/**
 * Class used by service bean to access the repository bean.
 * 
 *
 */
public interface AccountRepository {
    List<Account> getAccounts();

    Account getAccount(Long id);

    int getNumberOfAccounts();

    Long createAccount(BigDecimal initialBalance);

    int deleteAccount(Long id);

    void updateAccount(Account account);
}
