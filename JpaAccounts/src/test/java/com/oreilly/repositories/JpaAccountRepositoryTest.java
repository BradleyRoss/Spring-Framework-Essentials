package com.oreilly.repositories;

import com.oreilly.config.AppConfig;
import com.oreilly.entities.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import org.junit.Assert;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class JpaAccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void testGetAccounts() throws Exception {
        List<Account> accounts = repository.getAccounts();
        Assert.assertNotNull("accounts is null", accounts);
        Assert.assertThat(accounts.size(), is(3));
    }

    @Test
    public void testGetAccount() throws Exception {
    	initialize();
    	Assert.assertNotNull("repository is null", repository);
        Account account = repository.getAccount(1L);
        Assert.assertNotNull("account is null", account);
        Assert.assertThat("Numbers are close enough", new BigDecimal("100.0"),
                is(closeTo(account.getBalance(), new BigDecimal("0.01"))));
    }

    @Test
    public void testGetNumberOfAccounts() throws Exception {
    	initialize();
    	System.out.println("testGetNumberOfAccounts: starting"); 
        Assert.assertThat(repository.getNumberOfAccounts(), is(3));
    }

    @Test
    public void testCreateAccount() throws Exception {
    	initialize();
        Long id = repository.createAccount(new BigDecimal("250.00"));
        Assert.assertThat("id value is not null", id, is(notNullValue()));
        Account account = repository.getAccount(id);
        Assert.assertThat(account.getId(), is(id));
        Assert.assertThat("value is close", account.getBalance(), is(closeTo(new BigDecimal("250.0"),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testUpdateAccount() throws Exception {
    	initialize();
        Account account = repository.getAccount(1L);
        BigDecimal current = account.getBalance();
        BigDecimal amount = new BigDecimal("50.0");
        account.setBalance(current.add(amount));
        repository.updateAccount(account);

        Account again = repository.getAccount(1L);
        Assert.assertThat(again.getBalance(), is(closeTo(current.add(amount),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        for (Account account : repository.getAccounts()) {
            repository.deleteAccount(account.getId());
        }
        Assert.assertThat(repository.getNumberOfAccounts(), is(0));
    }
    
    public void initialize() {
 	
   	
   }
}