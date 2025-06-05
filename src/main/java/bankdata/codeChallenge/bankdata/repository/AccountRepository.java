package bankdata.codeChallenge.bankdata.repository;

import bankdata.codeChallenge.bankdata.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findByAccountNumber (int accountNumber);
}
