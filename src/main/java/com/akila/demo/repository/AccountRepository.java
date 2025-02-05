package com.akila.demo.repository;

import com.akila.demo.domain.Account;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Phuc Nguyen
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

}
