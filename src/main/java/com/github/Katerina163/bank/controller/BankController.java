package com.github.Katerina163.bank.controller;

import com.github.Katerina163.bank.dto.request.BankDto;
import com.github.Katerina163.bank.dto.request.CreditDto;
import com.github.Katerina163.bank.service.BankService;
import com.github.Katerina163.bank.service.CreditService;
import com.github.Katerina163.bank.validation.CreateGroup;
import com.github.Katerina163.bank.validation.ModifyGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/bank")
public class BankController {
    private final BankService service;
    private final CreditService creditService;

    public BankController(BankService simpleBankService, CreditService simpleCreditService) {
        service = simpleBankService;
        creditService = simpleCreditService;
    }

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "bank/register";
    }

    @PostMapping("/register")
    public String register(@Valid BankDto bank, Model model) {
        var savedBank = service.save(bank);
        if (savedBank.isEmpty()) {
            model.addAttribute("message",
                    "Банк с названием " + bank.getName() + " уже существует");
            return "/bank/register";
        }
        return "redirect:/bank/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "bank/login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid BankDto bank,
                            Model model, HttpServletRequest request) {
        var loginBank = service.findByNameAndPassword(bank.getName(), bank.getPassword());
        if (loginBank.isEmpty()) {
            model.addAttribute("message", "Неверно введено имя или пароль");
            return "/bank/login";
        }
        request.getSession().setAttribute("bank", loginBank.get().getName());
        request.getSession().setAttribute("clients", "Банк");
        return "redirect:/main";
    }

    @GetMapping("/clients")
    public String getClientsListPage(Model model, HttpSession session) {
        var clients = service.findClientsByName((String) session.getAttribute("bank"));
        if (clients.isEmpty()) {
            model.addAttribute("message", "У вас нет клиентов");
        } else {
            model.addAttribute("clients", clients);
        }
        return "bank/clients";
    }

    @GetMapping("/credits")
    public String getCreditsListPage(Model model, HttpSession session) {
        var credits = service.findCreditsByName((String) session.getAttribute("bank"));
        if (credits.isEmpty()) {
            model.addAttribute("message", "У вас нет кредитов");
        } else {
            model.addAttribute("credits", credits);
        }
        return "bank/credits";
    }

    @GetMapping("/credit/modify/{id}")
    public String getCreditsModifyPage(@PathVariable UUID id, Model model) {
        var credit = creditService.findById(id).get();
        model.addAttribute("credit", credit);
        return "bank/modify_credit";
    }

    @PostMapping("/credit/modify")
    public String creditsModify(@Validated(ModifyGroup.class) CreditDto credit) {
        creditService.modify(credit);
        return "redirect:/bank/credits";
    }

    @GetMapping("/credit/save")
    public String getCreditSavePage() {
        return "bank/credit";
    }

    @PostMapping("/credit/save")
    public String creditSave(@Validated(CreateGroup.class) CreditDto credit, HttpSession session) {
        service.addCredit((String) session.getAttribute("bank"), credit);
        return "redirect:/bank/credits";
    }
}
