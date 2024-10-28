package com.example.banking_app.controller;


import com.example.banking_app.entity.Account;
import com.example.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;
    @GetMapping("getAll")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(),HttpStatus.OK);
    }
    @PostMapping("addAccount")
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        return new ResponseEntity<>(accountService.addAccount(account), HttpStatus.CREATED);
    }
    @GetMapping("getAccountById/{id}")
    public ResponseEntity<Optional<Account>> getAccountById(@PathVariable long id){
        return new ResponseEntity<>( accountService.getAccountById(id),HttpStatus.OK);
    }
    @PostMapping("deposit")
    public ResponseEntity<Account> deposit(@RequestParam Long id,double amount){
        return new ResponseEntity<>(accountService.deposit(id,amount),HttpStatus.OK);
    }
    @PostMapping("withdraw")
    public ResponseEntity<Account> withdraw(@RequestParam Long id,double amount){
        return new ResponseEntity<>(accountService.withdraw(id,amount),HttpStatus.OK);
    }
    @GetMapping("getByName/{accountHolderName}")
    public ResponseEntity<Account> getAccountByName(@PathVariable String accountHolderName){
        return new ResponseEntity<>(accountService.getAccountByName(accountHolderName),HttpStatus.OK);
    }
    @PutMapping("updateAccount/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id,@RequestBody Account account){
        return new ResponseEntity<>(accountService.updateAccount(id,account),HttpStatus.OK);
    }
    @DeleteMapping("deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
       accountService.deleteAccount(id);
        return new ResponseEntity<>("deleted account successfully",HttpStatus.OK);
    }
    @DeleteMapping("deleteByName/{accountHolderName}")
    public ResponseEntity<String> deleteAccountByName(@PathVariable String accountHolderName){
        accountService.deleteAccountByName(accountHolderName);
        return new ResponseEntity<>("deleted account succesfully",HttpStatus.OK);
    }
    @DeleteMapping("deleteAll")
    public ResponseEntity<String> deleteAllAccounts(){
        accountService.deleteAllAccounts();
        return new ResponseEntity<>("deleted all accounts",HttpStatus.OK);
    }
}
