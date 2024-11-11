package com.example.banking_app.entity;


import jakarta.persistence.*;
import lombok.Data;



@Data
//@Table(name = "accounts")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountHolderName;
    private double balance;
}
