package bankdata.codeChallenge.bankdata.controller;

import bankdata.codeChallenge.bankdata.model.Account;
import bankdata.codeChallenge.bankdata.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Account")
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;

    //I didn't get around to use response instead og account or double as the return of the requests, but that would be more elegant and helps with handling errors

    @PostMapping("/create")
    public Account createAccount(@RequestParam String firstName, @RequestParam String lastName) {
        Account account = new Account(firstName, lastName);
        accountService.saveAccount(account);
        return account;
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable("id") String id) {
        int accountNumber = Integer.parseInt(id);
        return accountService.findByAccountNumber(accountNumber);
    }

    @GetMapping("/{id}/balance")
    public double getBalance(@PathVariable("id") String id) {
        int accountNumber = Integer.parseInt(id);
        return accountService.findByAccountNumber(accountNumber).getBalance();
    }

    @PutMapping("/{id}/deposit")
    public double deposit(@PathVariable("id") String id, @RequestParam String amount) {
        double amountDouble = Double.parseDouble(amount);
        int accountNumber = Integer.parseInt(id);
        Account account = accountService.findByAccountNumber(accountNumber);
        accountService.deposit(account, amountDouble);
        return account.getBalance();
    }

    @PutMapping("/transfer")
    public void transfer(@RequestParam String idFrom, @RequestParam String idTo, @RequestParam String amount) {
        double amountDouble = Double.parseDouble(amount);
        int accountNumberFrom = Integer.parseInt(idFrom);
        int accountNumberTo = Integer.parseInt(idTo);
        Account accountFrom = accountService.findByAccountNumber(accountNumberFrom);
        Account accountTo = accountService.findByAccountNumber(accountNumberTo);
        accountService.transfer(accountFrom, accountTo, amountDouble);
    }
}
