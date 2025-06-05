package bankdata.codeChallenge.bankdata.controller;

import bankdata.codeChallenge.bankdata.model.Account;
import bankdata.codeChallenge.bankdata.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Account")
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam String firstName, @RequestParam String lastName) {
        Account account = new Account(firstName, lastName);
        accountService.saveAccount(account);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") String id) {
        int accountNumber = Integer.parseInt(id);
        return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable("id") String id) {
        int accountNumber = Integer.parseInt(id);
        return ResponseEntity.ok(accountService.findByAccountNumber(accountNumber).getBalance());
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Double> deposit(@PathVariable("id") String id, @RequestParam String amount) {
        double amountDouble = Double.parseDouble(amount);
        int accountNumber = Integer.parseInt(id);
        Account account = accountService.findByAccountNumber(accountNumber);
        accountService.deposit(account, amountDouble);
        return ResponseEntity.ok(account.getBalance());
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String idFrom, @RequestParam String idTo, @RequestParam String amount) {
        double amountDouble = Double.parseDouble(amount);
        int accountNumberFrom = Integer.parseInt(idFrom);
        int accountNumberTo = Integer.parseInt(idTo);
        Account accountFrom = accountService.findByAccountNumber(accountNumberFrom);
        Account accountTo = accountService.findByAccountNumber(accountNumberTo);
        accountService.transfer(accountFrom, accountTo, amountDouble);
        return ResponseEntity.ok("");
    }
}
