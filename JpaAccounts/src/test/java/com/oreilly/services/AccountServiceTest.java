package com.oreilly.services;

import com.oreilly.config.AppConfig;
import com.oreilly.repositories.AccountRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Profile;
// import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AccountServiceTest {
    @Autowired
    private AccountService service;
    
    @Autowired
    protected AccountRepository repository;

    @Test
    public void testDeposit() throws Exception {
    	Assert.assertNotNull("service is null at start", service);
    	initialize();
        BigDecimal start = service.getBalance(1L);
        Assert.assertNotNull("Unable to retrieve account", start);
        BigDecimal amount = new BigDecimal("50.0");
        service.deposit(1L, amount);
        BigDecimal finish = start.add(amount);

        assertThat(finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testWithdraw() throws Exception {
    	Assert.assertNotNull("service is null at start", service);
    	initialize();
        BigDecimal start = service.getBalance(1L);
        Assert.assertNotNull("Unable to get balance", start);
        BigDecimal amount = new BigDecimal("50.0");
        service.withdraw(1L, amount);
        BigDecimal finish = start.subtract(amount);

        assertThat(finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testTransfer() throws Exception {
    	initialize();
        BigDecimal acct1start = service.getBalance(1L);
        Assert.assertNotNull("Unable to get account 1", acct1start);
        BigDecimal acct2start = service.getBalance(2L);
        Assert.assertNotNull("Unable to get account 2", acct2start);

        BigDecimal amount = new BigDecimal("50.0");
        service.transfer(1L, 2L, amount);

        BigDecimal acct1finish = acct1start.subtract(amount);
        BigDecimal acct2finish = acct2start.add(amount);

        assertThat(acct1finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
        assertThat(acct2finish, is(closeTo(service.getBalance(2L),
                new BigDecimal("0.01"))));
    }
    
    public void initialize() {
  	
   	
   }
}