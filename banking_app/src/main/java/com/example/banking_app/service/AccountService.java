package com.example.banking_app.service;

import java.util.List;
import java.util.Optional;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_app.dao.AccountDao;
import com.example.banking_app.entity.Account;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;
    public Account addAccount(Account account) {
        return accountDao.save(account);
    }

    public Optional<Account> getAccountById(long id) {

        return accountDao.findById(id);
    }

    public Account deposit(Long id, double amount) {
        Account account=accountDao
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));
        double total= account.getBalance()+amount;
        account.setBalance(total);
        return accountDao.save(account);
    }

    public Account withdraw(Long id, double amount) {
        Account account=accountDao
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient Amount");
        }
        double total= account.getBalance()-amount;
        account.setBalance(total);
        return accountDao.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountDao.findAll();
    }

    public void deleteAccount(Long id) {

        accountDao.deleteById(id);
    }

    public void deleteAllAccounts() {

        accountDao.deleteAll();
    }

    public Account getAccountByName(String accountHolderName) {
        return accountDao.findByAccountHolderName(accountHolderName);
    }
    @Transactional//Custom DELETE or UPDATE operations (like deleteByName()) require an active transaction because they modify the database.
    public void deleteAccountByName(String accountHolderName) {
        accountDao.deleteByAccountHolderName(accountHolderName);
    }

    public Account updateAccount(Long id, Account account) {
        Account account1=accountDao
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account not found"));
        account1.setAccountHolderName(account.getAccountHolderName());
        account1.setBalance(account.getBalance());
        return accountDao.save(account1);
    }
}
