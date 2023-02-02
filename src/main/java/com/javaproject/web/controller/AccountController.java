package com.javaproject.web.controller;

import com.javaproject.web.entity.Account;
import com.javaproject.web.service.AccountService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class AccountController {

    private AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public String listAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }

    @GetMapping("/accounts/add")
    public String createAccountForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "create_account";
    }

    @PostMapping("/accounts")
    public String saveAccount(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_account";
        }
            accountService.saveAccount(account);
            return "redirect:/accounts";
    }

    @GetMapping("/accounts/edit/{id}")
    public String editAccount(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getAccountById(id));
        return "edit_account";
    }

    @PostMapping("/accounts/{id}")
    public String updateAccount(@Valid @ModelAttribute("account") Account account,
                                BindingResult bindingResult,
                                Model model,
                                @PathVariable Long id) {

        if (bindingResult.hasErrors()) {
            return "edit_account";
        }

        Account existingAccount = accountService.getAccountById(id);
        existingAccount.setIban(account.getIban());
        existingAccount.setTip(account.getTip());
        existingAccount.setValuta(account.getValuta());
        existingAccount.setKlijentId(account.getKlijentId());
        existingAccount.setDatumOtvaranja(account.getDatumOtvaranja());
        existingAccount.setDatumZatvaranja(account.getDatumZatvaranja());

        accountService.editAccount(existingAccount);
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/accounts";
    }
}

