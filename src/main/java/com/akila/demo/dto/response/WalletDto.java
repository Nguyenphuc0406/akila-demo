package com.akila.demo.dto.response;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Phuc Nguyen
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {

    private UUID walletCode;

    private String name;

    private BigDecimal totalAmount;

    private String currencyCode;
}
