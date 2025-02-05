package com.akila.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "wallet")
public class Wallet {

    /**
     * The id is pair of account_id and currency_id. To ensure that one account has only one wallet
     * with a currency.
     */
    @EmbeddedId
    private WalletId id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "wallet_code", unique = true, nullable = false)
    private UUID walletCode;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WalletId implements Serializable {

        private static final long serialVersionUID = -277057943220762740L;

        @Column(name = "account_id", nullable = false)
        private UUID accountId;

        @Column(name = "currency_id", nullable = false)
        private UUID currencyId;
    }
}
