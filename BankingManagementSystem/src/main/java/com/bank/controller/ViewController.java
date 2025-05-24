package com.bank.controller;

import com.bank.dao.AccountDao;
import com.bank.entity.Account;
import com.bank.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private AccountService accountService;

    // ✅ Display all accounts
    @GetMapping("/accounts")
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "account";  // templates/account.html
    }

    // ✅ Show Create Account Form
    @GetMapping("/accounts/new")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("account", new AccountDao());  // Use AccountDao or Account
        return "newAccount";  // templates/newAccount.html
    }

    // ✅ Handle Create Account Form Submission
    @PostMapping("/accounts")
    public String createAccount(@ModelAttribute("account") AccountDao account) {
        accountService.createAccount(account);
        return "redirect:/view/accounts";
    }

    // ✅ View Account Details
    @GetMapping("/accounts/{id}")
    public String getAccountDetails(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getAccountById(id));
        return "accountDetails";  // templates/accountDetails.html
    }

    // ✅ Show Deposit Form
    @GetMapping("/accounts/{id}/deposit")
    public String showDepositForm(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getAccountById(id));
        return "deposit";  // templates/deposit.html
    }

    // ✅ Handle Deposit Submission
    @PostMapping("/accounts/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestParam double amount) {
        accountService.deposit(id, amount);
        return "redirect:/view/accounts";
    }

    // ✅ Show Withdraw Form
    @GetMapping("/accounts/{id}/withdraw")
    public String showWithdrawForm(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getAccountById(id));
        return "withdraw";  // templates/withdraw.html
    }

    // ✅ Handle Withdraw Submission
    @PostMapping("/accounts/{id}/withdraw")
    public String withdraw(@PathVariable Long id, @RequestParam double amount) {
        accountService.withdraw(id, amount);
        return "redirect:/view/accounts";
    }

    // ✅ Delete Account
    @GetMapping("/accounts/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/view/accounts";
    }
}
