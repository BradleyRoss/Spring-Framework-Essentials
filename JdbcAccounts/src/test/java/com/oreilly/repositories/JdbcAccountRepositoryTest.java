package com.oreilly.repositories;

import com.oreilly.config.AppConfig;
import com.oreilly.entities.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

// import static org.hamcrest.CoreMatchers.is;
// import static org.hamcrest.CoreMatchers.notNullValue;
// import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertThat;
import org.hamcrest.number.BigDecimalCloseTo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
@ActiveProfiles({"prod", "test"})
public class JdbcAccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void testGetAccounts() throws Exception {
        List<Account> accounts = repository.getAccounts();
        Assert.assertThat(accounts.size(), CoreMatchers.is(3));
    }

    @Test
    public void testGetAccount() throws Exception {
        Account account = repository.getAccount(1L);
        Assert.assertThat(account.getId(), CoreMatchers.is(1L));
        Assert.assertThat(new BigDecimal("100.0"),
               CoreMatchers.is(BigDecimalCloseTo.closeTo(account.getBalance(), new BigDecimal("0.01"))));
    }

    @Test
    public void testGetNumberOfAccounts() throws Exception {
        Assert.assertThat(repository.getNumberOfAccounts(), CoreMatchers.is(3));
    }

    @Test
    public void testCreateAccount() throws Exception {
        Long id = repository.createAccount(new BigDecimal("250.00"));
        Assert.assertThat(id, CoreMatchers.is(CoreMatchers.notNullValue()));

        Account account = repository.getAccount(id);
        Assert.assertThat(account.getId(), CoreMatchers.is(id));
        Assert.assertThat(account.getBalance(), CoreMatchers.is(BigDecimalCloseTo.closeTo(new BigDecimal("250.0"),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account = repository.getAccount(1L);
        BigDecimal current = account.getBalance();
        BigDecimal amount = new BigDecimal("50.0");
        account.setBalance(current.add(amount));
        repository.updateAccount(account);

        Account again = repository.getAccount(1L);
        Assert.assertThat(again.getBalance(), CoreMatchers.is(BigDecimalCloseTo.closeTo(current.add(amount),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        for (Account account : repository.getAccounts()) {
            repository.deleteAccount(account.getId());
        }
        Assert.assertThat(repository.getNumberOfAccounts(), CoreMatchers.is(0));
    }
}
