package com.akila.demo.repository;

import com.akila.demo.domain.Wallet;
import com.akila.demo.domain.Wallet.WalletId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Phuc Nguyen
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, WalletId> {

    /**
     *
     * @param accountId
     * @param currencyId
     * @return
     */
    @Query("select e from Wallet e where e.id.accountId = ?1 and e.id.currencyId = ?2")
    Optional<Wallet> findByAccountIdAndCurrencyId(UUID accountId, UUID currencyId);

    /**
     *
     * @param accountId
     * @return
     */
    List<Wallet> findByIdAccountId(UUID accountId);

}
