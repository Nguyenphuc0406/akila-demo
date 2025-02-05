/*
package com.akila.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.akila.demo.domain.Account;
import com.akila.demo.domain.Currency;
import com.akila.demo.domain.Wallet;
import com.akila.demo.domain.Wallet.WalletId;
import com.akila.demo.dto.request.TransferRequestDto;
import com.akila.demo.dto.response.TransferResponseDto;
import com.akila.demo.repository.AccountRepository;
import com.akila.demo.repository.CurrencyRepository;
import com.akila.demo.repository.WalletRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest()
@ActiveProfiles("test")
class TransactionIntegTest {
    private static final UUID ACCOUNT_1_ID = UUID
            .fromString("37166790-4470-4484-bfbb-66364e0ff807");
    private static final String ACCOUNT_1_NAME = "a.nguyen";
    private static final UUID ACCOUNT_2_ID = UUID
            .fromString("47166790-4470-4484-bfbb-66364e0ff807");
    private static final String ACCOUNT_2_NAME = "b.nguyen";

    private static final UUID CURRENCY_1_ID = UUID
            .fromString("ff7057c2-cda9-4f6b-b94f-227b259a94d3");
    private static final String CURRENCY_1_CODE = "VND";
    private static final UUID CURRENCY_2_ID = UUID
            .fromString("cf7057c2-cda9-4f6b-b94f-227b259a94d3");
    private static final String CURRENCY_2_CODE = "USD";

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void init() {
        Account account_1 = Account.builder()
                .id(ACCOUNT_1_ID)
                .username(ACCOUNT_1_NAME)
                .email(Mockito.anyString())
                .dateOfBirth(Mockito.any()).build();

        Account account_2 = Account.builder()
                .id(ACCOUNT_2_ID)
                .username(ACCOUNT_2_NAME)
                .email(Mockito.anyString())
                .dateOfBirth(Mockito.any()).build();
        // save all accounts
        accountRepository.saveAll(List.of(account_1, account_2));

        // VND
        Currency currency_1 = Currency.builder()
                .id(CURRENCY_1_ID).code(CURRENCY_1_CODE).name(Mockito.anyString()).build();
        // USD
        Currency currency_2 = Currency.builder()
                .id(CURRENCY_2_ID).code(CURRENCY_2_CODE).name(Mockito.anyString()).build();
        // save all currencies
        currencyRepository.saveAll(List.of(currency_1, currency_2));

        // Account 1-VND
        Wallet wallet_1 = Wallet.builder()
                .totalAmount(BigDecimal.valueOf(500000.0))
                .walletCode(UUID.randomUUID())
                .id(new WalletId(ACCOUNT_1_ID, CURRENCY_1_ID)).name(Mockito.anyString())
                .build();
        // Account 2-VND
        Wallet wallet_2 = Wallet.builder()
                .totalAmount(BigDecimal.valueOf(400000.0))
                .walletCode(UUID.randomUUID())
                .id(new WalletId(ACCOUNT_2_ID, CURRENCY_1_ID)).name(Mockito.anyString())
                .build();
        // Account 2-USD
        Wallet wallet_3 = Wallet.builder()
                .totalAmount(BigDecimal.valueOf(400000.0))
                .walletCode(UUID.randomUUID())
                .id(new WalletId(ACCOUNT_2_ID, CURRENCY_2_ID)).name(Mockito.anyString())
                .build();
        // save all wallets
        walletRepository.saveAll(List.of(wallet_1, wallet_2, wallet_3));
    }

    @Test
    void test_transfer_invalid_currency() {
        String invalidCurrency = "DDD";

        TransferRequestDto request = TransferRequestDto.builder()
                .currencyCode(invalidCurrency)
                .fromAccountId(ACCOUNT_1_ID)
                .toAccountId(ACCOUNT_2_ID)
                .amount(50000).build();

        Assertions.assertThrows(ResponseStatusException.class, () -> {
                    transactionService.transferMoney(request);
                }, "The system don't support with currency code " + invalidCurrency
        );
    }

    @Test
    void test_transfer_invalid_account() {
        UUID invalidAccountId = UUID.fromString("22266790-4470-4484-bfbb-66364e0ff807");

        TransferRequestDto request = TransferRequestDto.builder()
                .currencyCode(CURRENCY_1_CODE)
                .fromAccountId(invalidAccountId)
                .toAccountId(ACCOUNT_2_ID)
                .amount(50000).build();

        Assertions.assertThrows(ResponseStatusException.class, () -> {
                    transactionService.transferMoney(request);
                }, "Can not found the original account with id: " + invalidAccountId
        );
    }

    @Test
    void test_transfer_mismatch_currency() {
        TransferRequestDto request = TransferRequestDto.builder()
                .currencyCode(CURRENCY_2_CODE)
                .fromAccountId(ACCOUNT_1_ID)
                .toAccountId(ACCOUNT_2_ID)
                .amount(50000).build();

        Assertions.assertThrows(ResponseStatusException.class, () -> {
                    transactionService.transferMoney(request);
                }, "Mismatch the currency unit between two wallets"
        );
    }

    @Test
    void test_transfer_success() {
        TransferRequestDto request = TransferRequestDto.builder()
                .currencyCode(CURRENCY_1_CODE)
                .fromAccountId(ACCOUNT_1_ID)
                .toAccountId(ACCOUNT_2_ID)
                .amount(50000).build();

        TransferResponseDto response = transactionService.transferMoney(request);
        assertNotNull(response.getMessage());
    }
}
*/
