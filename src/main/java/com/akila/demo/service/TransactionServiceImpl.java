package com.akila.demo.service;

import com.akila.demo.constant.MessageConstant;
import com.akila.demo.converter.EntityConverter;
import com.akila.demo.domain.Account;
import com.akila.demo.domain.Currency;
import com.akila.demo.domain.Transaction;
import com.akila.demo.domain.Wallet;
import com.akila.demo.dto.request.TransferRequestDto;
import com.akila.demo.dto.response.AccountDto;
import com.akila.demo.dto.response.TransferResponseDto;
import com.akila.demo.repository.AccountRepository;
import com.akila.demo.repository.CurrencyRepository;
import com.akila.demo.repository.TransactionRepository;
import com.akila.demo.repository.WalletRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.locks.StampedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Phuc Nguyen
 */
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final StampedLock transactionLock = new StampedLock();

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private EntityConverter<Account, AccountDto> accountConverter;

    @Override
    public TransferResponseDto transferMoney(TransferRequestDto request) {
        log.info(
                "Received request: Transfer {} ({}) from origin-account: {} to destination-account: {}",
                request.getAmount(), request.getCurrencyCode(), request.getFromAccountId(),
                request.getToAccountId());

        log.info("Validating request data...");
        String errorMsg;
        // validate currency code
        Optional<Currency> currencyOptional = currencyRepository
                .findByCode(request.getCurrencyCode());
        if (currencyOptional.isEmpty()) {
            errorMsg = String
                    .format(MessageConstant.MSG_INVALID_CURRENCY, request.getCurrencyCode());
            log.error(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }

        BigDecimal amountRequest = BigDecimal.valueOf(request.getAmount());
        UUID fromAccountId = request.getFromAccountId();
        UUID toAccountId = request.getToAccountId();

        // validate account_id
        Optional<Account> originAccountOpt = accountRepository.findById(fromAccountId);
        if (originAccountOpt.isEmpty()) {
            errorMsg = String.format(MessageConstant.MSG_NOT_FOUND_ORG_ACCOUNT, fromAccountId);
            log.error(errorMsg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMsg);
        }

        Optional<Account> destinationAccountOpt = accountRepository.findById(toAccountId);
        if (destinationAccountOpt.isEmpty()) {
            errorMsg = String.format(MessageConstant.MSG_NOT_FOUND_DES_ACCOUNT, toAccountId);
            log.error(errorMsg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(MessageConstant.MSG_NOT_FOUND_DES_ACCOUNT, toAccountId));
        }

        Account originAccount = originAccountOpt.get();
        Account destinationAccount = destinationAccountOpt.get();
        Currency currency = currencyOptional.get();

        // check the currency of two wallets
        Optional<Wallet> originWalletOpt = walletRepository
                .findByAccountIdAndCurrencyId(fromAccountId, currency.getId());
        Optional<Wallet> destinationWalletOpt = walletRepository
                .findByAccountIdAndCurrencyId(toAccountId, currency.getId());
        if (originWalletOpt.isEmpty() || destinationWalletOpt.isEmpty()) {
            errorMsg = MessageConstant.MSG_MISMATCH_CURRENCY;
            log.error(errorMsg);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }

        Wallet originWallet = originWalletOpt.get();
        Wallet destinationWallet = destinationWalletOpt.get();

        // check the remaining money of origin wallet
        if (originWallet.getTotalAmount().compareTo(BigDecimal.valueOf(request.getAmount())) < 0) {
            errorMsg = String.format(MessageConstant.MSG_NOT_ENOUGH_MONEY, originWallet.getName());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMsg);
        }

        log.info("Validated request data.");

        // Ensures that only one thread can perform transfers at a time.
        // This will ensure thread safety while maintaining decent performance for concurrent transactions.
        long stampLock = transactionLock.writeLock();

        try {
            handleAmountTransfer(amountRequest, originAccount, destinationAccount, originWallet,
                    destinationWallet, currency);
        } catch (Exception e) {
            log.error("Transfer error.");
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    MessageConstant.MSG_TRANSFER_PROCESS_ERROR);
        } finally {
            transactionLock.unlockWrite(stampLock);
        }

        log.info(String.format(MessageConstant.MSG_TRANSFER_SUCCESS, request.getAmount(),
                currency.getCode(), originAccount.getUsername(),
                destinationAccount.getUsername()));

        return TransferResponseDto.builder()
                .message(String.format(MessageConstant.MSG_TRANSFER_SUCCESS, request.getAmount(),
                        currency.getCode(), originAccount.getUsername(),
                        destinationAccount.getUsername())).build();
    }

    private void handleAmountTransfer(BigDecimal amountRequest, Account originAccount,
            Account destinationAccount, Wallet originWallet, Wallet destinationWallet,
            Currency currency) {
        Transaction transaction = Transaction.builder()
                .amount(amountRequest)
                .fromAccount(originAccount)
                .toAccount(destinationAccount)
                .currency(currency).build();

        // save transaction info
        transactionRepository.save(transaction);
        log.info("Saved transaction information");

        // update origin wallet
        originWallet.setTotalAmount(originWallet.getTotalAmount().subtract(amountRequest));
        walletRepository.save(originWallet);
        log.info("Updated origin wallet for account: {}, current amount: {}",
                originAccount.getId(), originWallet.getTotalAmount());

        // update destination wallet
        destinationWallet.setTotalAmount(destinationWallet.getTotalAmount().add(amountRequest));
        walletRepository.save(destinationWallet);
        log.info("Updated destination wallet for account: {}, current amount: {}",
                destinationAccount.getId(), destinationWallet.getTotalAmount());
    }

    @Override
    public List<AccountDto> getAccountInfos() {
        log.info("Get all account information");
        List<AccountDto> result;

        long stampLock = transactionLock.readLock();
        try {
            result = accountConverter.convertToDtoList(accountRepository.findAll());
        } finally {
            transactionLock.unlockRead(stampLock);
        }

        return result;
    }
}
