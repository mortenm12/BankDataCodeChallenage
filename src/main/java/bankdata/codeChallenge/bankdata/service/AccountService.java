package bankdata.codeChallenge.bankdata.service;

import bankdata.codeChallenge.bankdata.model.Account;
import bankdata.codeChallenge.bankdata.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    public Account findByAccountNumber(int accountNumber){
        return accountRepository.findByAccountNumber(accountNumber).orElse(null);
    }

    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdraw(Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void transfer(Account accountFrom, Account accountTo, double amount) {
        withdraw(accountFrom, amount);
        deposit(accountTo, amount);
    }
}
