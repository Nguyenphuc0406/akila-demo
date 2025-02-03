package com.akila.demo.repository;

import com.akila.demo.domain.Account;
import com.akila.demo.domain.Currency;
import com.akila.demo.domain.Wallet;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Phuc Nguyen
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findByCurrencyAndAccount(Currency currency, Account account);

}
