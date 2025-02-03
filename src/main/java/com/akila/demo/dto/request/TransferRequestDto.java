package com.akila.demo.dto.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Phuc Nguyen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto {
    private UUID fromAccountId;

    private UUID toAccountId;

    private double amount;

    private String currencyCode;

}
