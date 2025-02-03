package com.akila.demo.service;

import com.akila.demo.dto.request.TransferRequestDto;
import com.akila.demo.dto.response.TransferResponseDto;

public interface TransactionService {

    /**
     *
     * @param request
     * @return
     */
    TransferResponseDto transferMoney(TransferRequestDto request);
}
