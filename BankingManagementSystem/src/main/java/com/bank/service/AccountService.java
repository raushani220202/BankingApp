package com.bank.service;

import java.util.List;

import com.bank.dao.AccountDao;

public interface AccountService {
	
  AccountDao createAccount(AccountDao account);
  
  AccountDao getAccountById(Long id);
  
  AccountDao deposit(Long id,double amount);
  
  AccountDao withdraw(Long id, double amount);
  
  List<AccountDao> getAllAccounts();
  
  void deleteAccount(Long id);
}
