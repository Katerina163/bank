package com.github.Katerina163.bank.controller;

import com.github.Katerina163.bank.dto.request.ClientDto;
import com.github.Katerina163.bank.dto.request.LoanDto;
import com.github.Katerina163.bank.dto.response.ClientsDto;
import com.github.Katerina163.bank.service.ClientService;
import com.github.Katerina163.bank.service.LoanOfferService;
import com.github.Katerina163.bank.validation.LoginGroup;
import com.github.Katerina163.bank.validation.RegisterGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;
    private final LoanOfferService loanOfferService;

    public ClientController(ClientService simpleClientService,
                            LoanOfferService simpleloanOfferService) {
        service = simpleClientService;
        loanOfferService = simpleloanOfferService;
    }

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "client/register";
    }

    @PostMapping("/register")
    public String register(@Validated(RegisterGroup.class) ClientDto client, Model model) {
        var savedClient = service.save(client);
        if (savedClient.isEmpty()) {
            model.addAttribute("message",
                    "Клиент с почтой " + client.getEmail() + " уже существует");
            return "/client/register";
        }
        return "redirect:/client/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "client/login";
    }

    @PostMapping("/login")
    public String loginUser(@Validated(LoginGroup.class) ClientDto client,
                            Model model, HttpServletRequest request) {
        var loginClient = service.findByEmailAndPassword(client.getEmail(), client.getPassword());
        if (loginClient.isEmpty()) {
            model.addAttribute("message", "Неверно введен логин или пароль");
            return "/client/login";
        }
        request.getSession().setAttribute("client", loginClient.get().getEmail());
        request.getSession().setAttribute("clients", "Клиент");
        return "redirect:/main";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, HttpSession session) {
        String clientEmail = (String) session.getAttribute("client");
        var client = service.findByEmail(clientEmail).get();
        model.addAttribute("clien", client);
        return "client/profile";
    }

    @GetMapping("/profile/modify/{id}")
    public String getModifyPage(@PathVariable UUID id, Model model) {
        model.addAttribute("clien", service.findById(id).get());
        return "client/modify";
    }

    @PostMapping("/profile/modify")
    public String modifying(ClientsDto client) {
        service.modify(client);
        return "redirect:/client/profile";
    }

    @GetMapping("/credits")
    public String getCreditPage(Model model, HttpSession session) {
        String clientEmail = (String) session.getAttribute("client");
        var credits = loanOfferService.findCreditsByEmail(clientEmail);
        if (credits.isEmpty()) {
            model.addAttribute("message", "У вас нет кредитов");
        } else {
            model.addAttribute("credits", credits);
        }
        return "client/credits";
    }

    @GetMapping("/credit")
    public String getLoanPage(Model model, Long price) {
        model.addAttribute("price", price);
        var credits = loanOfferService.findCreditsByPrice(price);
        model.addAttribute("credits", credits);
        return "/client/loanoffers";
    }

    @PostMapping("/credit")
    public String getLoan(@Valid LoanDto dto, HttpSession session) {
        String clientEmail = (String) session.getAttribute("client");
        var client = service.findByEmail(clientEmail).get();
        service.addLoan(client.getId(), dto.getId(), dto.getPrice());
        return "redirect:/client/credits";
    }

    @GetMapping("/credit/graphic/{id}")
    public String getPaymentsPage(@PathVariable UUID id, Model model) {
        var payments = loanOfferService.findPaymentsByLoanOfferId(id);
        model.addAttribute("payments", payments);
        return "/client/graphic";
    }
}
