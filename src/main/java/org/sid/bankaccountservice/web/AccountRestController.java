package org.sid.bankaccountservice.web;

import lombok.extern.slf4j.Slf4j;
import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAccountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountBankMapper;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class AccountRestController {
    private BankAccountRepository  bankAccountRepository;
@Autowired
private AccountBankMapper accountBankMapper;

    private AccountService accountService;
    public AccountRestController(BankAccountRepository bankAccountRepository,AccountService accountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
    }
    @GetMapping("/bankAccounts")
public List<BankAccount> bankAccounts(){

        return bankAccountRepository.findAll();
}
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException( String.format("Account %s no found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){

     return     accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
public  BankAccount update(@PathVariable String id, @RequestBody BankAccount account){
        BankAccount bankAccount=bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException( String.format("Account % no found",id)));
   if(account.getBalance()!=null)     bankAccount.setBalance(account.getBalance());
    if(account.getCurrency()!=null)     bankAccount.setCurrency(account.getCurrency());
    if(account.getCreatAt()!=null)   bankAccount.setCreatAt(new Date());
    if(account.getType()!=null)   bankAccount.setType(account.getType());
    return  bankAccountRepository.save((bankAccount));

}
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccounts(@PathVariable String id){

        bankAccountRepository.deleteById(id);
    }
}
