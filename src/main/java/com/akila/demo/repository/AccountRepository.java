package com.akila.demo.repository;

import com.akila.demo.domain.Account;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Phuc Nguyen
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    /**
     *
     * @param accountId
     * @return
     */
    @Query("select w.currency.code from Account a inner join Wallet w on a.id = w.account.id where a.id =?1")
    List<String> findAllSupportedCurrency(UUID accountId);
}
