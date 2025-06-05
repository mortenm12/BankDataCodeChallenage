package bankdata.codeChallenge.bankdata;

import bankdata.codeChallenge.bankdata.model.Account;
import bankdata.codeChallenge.bankdata.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountTest {

    @Autowired
    AccountService accountService;

    @Test
    void testName(){
        Account account = new Account("Test", "Tester");
        Assertions.assertEquals("Test", account.getFirstName());
        Assertions.assertEquals("Tester", account.getLastName());
    }

    @Test
    void testDeposit(){
        Account account = new Account("Test", "Tester");
        Assertions.assertEquals(0, account.getBalance());
        accountService.deposit(account, 100);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    void testWithdraw(){
        Account account = new Account("Test", "Tester");
        Assertions.assertEquals(0, account.getBalance());
        accountService.withdraw(account, 100);
        Assertions.assertEquals(-100, account.getBalance());
    }

    @Test
    void testTransfer(){
        Account account1 = new Account("Test1", "Tester");
        Assertions.assertEquals(0, account1.getBalance());
        Account account2 = new Account("Test2", "Tester");
        Assertions.assertEquals(0, account2.getBalance());
        accountService.deposit(account1, 100);
        Assertions.assertEquals(100, account1.getBalance());
        accountService.transfer(account1, account2, 100);
        Assertions.assertEquals(0, account1.getBalance());
        Assertions.assertEquals(100, account2.getBalance());
    }

}