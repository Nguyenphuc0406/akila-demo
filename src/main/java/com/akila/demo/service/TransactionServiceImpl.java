package com.akila.demo.service;

import com.akila.demo.domain.Account;
import com.akila.demo.domain.Currency;
import com.akila.demo.domain.Transaction;
import com.akila.demo.domain.Wallet;
import com.akila.demo.dto.request.TransferRequestDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final StampedLock transactionLock = new StampedLock();

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public TransferResponseDto transferMoney(TransferRequestDto request) {
        // check invalid currency code
        Optional<Currency> currencyOptional = currencyRepository.findByCode(request.getCurrencyCode());
        if (currencyOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The system don't support with currency code = " + request.getCurrencyCode());
        }

        BigDecimal amountRequest = BigDecimal.valueOf(request.getAmount());

        UUID fromAccountId = request.getFromAccountId();
        UUID toAccountId = request.getToAccountId();

        Optional<Account> originAccountOpt = accountRepository.findById(fromAccountId);
        if (originAccountOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can not found the original account with id = " + fromAccountId);
        }

        Optional<Account> destinationAccountOpt = accountRepository.findById(toAccountId);
        if (destinationAccountOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Can not found the account destination with id = " + toAccountId);
        }
        Account originAccount = originAccountOpt.get();
        Account destinationAccount = destinationAccountOpt.get();
        Currency currency = currencyOptional.get();

        // check the current wallet
        Optional<Wallet> originWalletOpt = walletRepository.findByCurrencyAndAccount(currency, originAccount);
        Optional<Wallet> destinationWalletOpt = walletRepository.findByCurrencyAndAccount(currency, destinationAccount);
        if (originWalletOpt.isEmpty() || destinationWalletOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mismatch the currency unit between two wallets");
        }

        Wallet originWallet = originWalletOpt.get();
        Wallet destinationWallet = destinationWalletOpt.get();

        if (originWallet.getTotalAmount().compareTo(BigDecimal.valueOf(request.getAmount())) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The wallet %s don't have enough money to transfer.");
        }

//        Transaction transaction = Transaction.builder()
//                .amount(amountRequest)
//                .fromAccount(originAccount)
//                .toAccount(destinationAccount)
//                .currency(originAccount.getCurrency()).build();
//
//        // save transaction info
//        transactionRepository.save(transaction);
//
//        // update origin account
//        originAccount.setTotalAmount(originAccount.getTotalAmount().subtract(amountRequest));
//        accountRepository.save(originAccount);
//
//        // update destination account
//        destinationAccount.setTotalAmount(destinationAccount.getTotalAmount().add(amountRequest));
//        accountRepository.save(destinationAccount);


        return null;
    }
}
