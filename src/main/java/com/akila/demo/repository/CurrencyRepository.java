package com.akila.demo.repository;

import com.akila.demo.domain.Currency;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Phuc Nguyen
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

    /**
     *
     * @param code
     * @return
     */
    Optional<Currency> findByCode(String code);
}
