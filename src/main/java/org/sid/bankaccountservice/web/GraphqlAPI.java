package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountBankMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.services.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller

public class GraphqlAPI {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountBankMapper accountBankMapper;

    public GraphqlAPI(BankAccountRepository bankAccountRepository, AccountService accountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
    }

    @QueryMapping
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount findAccountById(@Argument String id){
 return       bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("not found")));

    }
    @MutationMapping
    public BankAccountResponseDTO addBankAccount( @Argument BankAccountRequestDTO account){
     return accountService.addAccount((account));

    }
    @QueryMapping
    public  Boolean deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        return true;
    }
    @MutationMapping
    public BankAccount updateAccount(@Argument String id, @Argument BankAccount bankAccount)
    {
     BankAccount account=bankAccountRepository
             .findById((id)).orElseThrow(()->new RuntimeException(String.format("not found")));
   if (account.getType()!=null) account.setType(bankAccount.getType());
        if (account.getCurrency()!=null)  account.setCurrency(bankAccount.getCurrency());
        if (account.getBalance()!=null)  account.setBalance(bankAccount.getBalance());
        if (account.getCreatAt()!=null) account.setCreatAt(new Date());
   return bankAccountRepository.save(account);




    }
}
