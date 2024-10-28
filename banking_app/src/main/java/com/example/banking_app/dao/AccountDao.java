package com.example.banking_app.dao;

import com.example.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account,Long> {
     Account findByAccountHolderName(String accountHolderName);

   void deleteByAccountHolderName(String accountHolderName);
}
