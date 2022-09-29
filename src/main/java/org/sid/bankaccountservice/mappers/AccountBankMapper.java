package org.sid.bankaccountservice.mappers;

import lombok.SneakyThrows;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;

import java.lang.annotation.Inherited;



import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component @Service
public class AccountBankMapper {
    @SneakyThrows
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
    BankAccountResponseDTO bankAccountResponseDTO=new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
    @SneakyThrows
    public BankAccount oneBankAccount(BankAccountRequestDTO bankAccountResponseDTO) {
        BankAccount bankAccount=new BankAccount();

        BeanUtils.copyProperties(bankAccountResponseDTO,bankAccount);
        return bankAccount;

    }
}
