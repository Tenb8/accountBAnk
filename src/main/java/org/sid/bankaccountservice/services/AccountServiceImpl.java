package org.sid.bankaccountservice.services;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountBankMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    private  BankAccountRepository bankAccountRepository ;
    @Autowired
    public AccountBankMapper accountBankMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO accountRequestDTO) {

        BankAccount account=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .creatAt(new Date())
                .balance(accountRequestDTO.getBalance())
                .type(accountRequestDTO.getType())
                .currency(accountRequestDTO.getCurrency())
                .build();
        BankAccount saveBankAccount=bankAccountRepository.save(account);
        return    accountBankMapper.fromBankAccount(saveBankAccount);

    }


}
