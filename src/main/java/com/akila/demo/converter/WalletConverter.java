package com.akila.demo.converter;

import com.akila.demo.domain.Currency;
import com.akila.demo.domain.Wallet;
import com.akila.demo.dto.response.WalletDto;
import com.akila.demo.repository.CurrencyRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletConverter implements EntityConverter<Wallet, WalletDto> {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public WalletDto convertToDto(Wallet entity) {
        Optional<Currency> currencyOptional = currencyRepository.findById(entity.getId().getCurrencyId());
        if (currencyOptional.isEmpty()) {
            return null;
        }

        return WalletDto.builder()
                .walletCode(entity.getWalletCode())
                .name(entity.getName())
                .totalAmount(entity.getTotalAmount())
                .currencyCode(currencyOptional.get().getCode())
                .build();
    }
}
