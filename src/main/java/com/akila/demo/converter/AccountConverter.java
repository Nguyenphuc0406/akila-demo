package com.akila.demo.converter;

import com.akila.demo.domain.Account;
import com.akila.demo.domain.Wallet;
import com.akila.demo.dto.response.AccountDto;
import com.akila.demo.dto.response.WalletDto;
import com.akila.demo.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountConverter implements EntityConverter<Account, AccountDto> {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private EntityConverter<Wallet, WalletDto> walletConverter;

    @Override
    public AccountDto convertToDto(Account entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .walletList(walletConverter.convertToDtoList(walletRepository.findByIdAccountId(entity.getId())))
                .build();
    }
}
