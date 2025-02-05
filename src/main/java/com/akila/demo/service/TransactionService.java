package com.akila.demo.service;

import com.akila.demo.dto.request.TransferRequestDto;
import com.akila.demo.dto.response.AccountDto;
import com.akila.demo.dto.response.TransferResponseDto;
import java.util.List;

/**
 * @author Phuc Nguyen
 */
public interface TransactionService {

    /**
     *
     * @param request
     * @return
     */
    TransferResponseDto transferMoney(TransferRequestDto request);

    /**
     * Get all account information. Using for quick testing
     *
     * @return
     */
    List<AccountDto> getAccountInfos();
}
