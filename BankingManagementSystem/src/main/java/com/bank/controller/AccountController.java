package com.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dao.AccountDao;
import com.bank.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	// Add Account rest api
	@PostMapping
	public ResponseEntity<AccountDao> addAccount(@RequestBody AccountDao accountDao){
		System.out.println(accountDao);
		return new ResponseEntity<>(accountService.createAccount(accountDao),
				HttpStatus.CREATED);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<AccountDao> getAccountById(@PathVariable Long id){
		
		AccountDao accountDao = accountService.getAccountById(id);
		
		return ResponseEntity.ok(accountDao);
		
	}
	
	//Deposit
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDao> deposit(@PathVariable Long id,@RequestBody Map<String, Double> request){
		
		Double amount = request.get("amount");
		AccountDao accountDao= accountService.deposit(id, amount);
		
		return ResponseEntity.ok(accountDao);
	}
	
	//withdraw
	@PutMapping("/{id}/withdraw")
   public ResponseEntity<AccountDao> withdraw(@PathVariable Long id, @RequestBody Map<String , Double>request){
	 
	 Double amount = request.get("amount"); 
	 AccountDao accountDao = accountService.withdraw(id, amount);
	 
	 return ResponseEntity.ok(accountDao);
   }
	
	@GetMapping
	public ResponseEntity<List<AccountDao>>getAllAccounts(){
		
		List<AccountDao> accountDao = accountService.getAllAccounts();
		
		return ResponseEntity.ok(accountDao);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteAccount(@PathVariable Long id){
		
		accountService.deleteAccount(id);
		
		return ResponseEntity.ok("Account Deleted Successfully!!");
	}
		
}
