package com.akila.demo.controller;

import com.akila.demo.dto.request.TransferRequestDto;
import com.akila.demo.dto.response.TransferResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Phuc Nguyen
 */
@RequestMapping(TransactionResource.API_RESOURCE)
public interface TransactionResource {

    String API_RESOURCE = "/api/v1";
    String TRANSFER_RESOURCE = "/transfer";

    /**
     *
     *
     * @param request
     * @return
     */
    @PostMapping(TRANSFER_RESOURCE)
    ResponseEntity<TransferResponseDto> transferMoney(@RequestBody TransferRequestDto request);
}
