package com.bank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bank.dao.AccountDao;
import com.bank.entity.Account;
import com.bank.mapper.AccountMapper;
import com.bank.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	private AccountRepository accountRepository;
	
	@Override
	public AccountDao createAccount(AccountDao accountDao) {
		
		Account account = AccountMapper.mapToAccount(accountDao);
		Account savedAccount =accountRepository.save(account);
		return AccountMapper.mapToAccountDao(savedAccount);
	}

	@Override
	public AccountDao getAccountById(Long id) {
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not Exist"));
		
		return AccountMapper.mapToAccountDao(account);
	}

	@Override
	public AccountDao deposit(Long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not Exist"));
		
		double totalBalance= account.getBalance() + amount;
		account.setBalance(totalBalance);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDao(savedAccount);
	}

	@Override
	public AccountDao withdraw(Long id, double amount) {
		
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not Exist"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		double totalBalance =account.getBalance() - amount;
		account.setBalance(totalBalance);
	    Account savedAccount =	accountRepository.save(account);
		
		return AccountMapper.mapToAccountDao(savedAccount);
	}

	@Override
	public List<AccountDao> getAllAccounts() {
		
		return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDao(account)).
		collect(Collectors.toList());
	
	}

	@Override
	public void deleteAccount(Long id) {
		
	Account account = accountRepository.findById(id).orElseThrow(()->
	new RuntimeException("Account does not Exist"));
	
	accountRepository.delete(account);
	}

}
