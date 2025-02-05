package com.akila.demo.controller;

import com.akila.demo.dto.request.TransferRequestDto;
import com.akila.demo.dto.response.AccountDto;
import com.akila.demo.dto.response.TransferResponseDto;
import com.akila.demo.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Phuc Nguyen
 */
@RestController
public class TransactionController implements TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @Override
    public ResponseEntity<TransferResponseDto> transferMoney(TransferRequestDto request) {
        return ResponseEntity.ok(transactionService.transferMoney(request));
    }

    @Override
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(transactionService.getAccountInfos());
    }
}
