package com.akila.demo.dto.response;

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
public class TransferResponseDto {

    private String message;
}
