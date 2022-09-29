package org.sid.bankaccountservice.services;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.mappers.AccountBankMapper;

public interface AccountService  {

   BankAccountResponseDTO addAccount(BankAccountRequestDTO accountRequestDTO);
}
