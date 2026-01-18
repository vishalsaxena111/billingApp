package com.example.BillingApp.Controllers;

import com.example.BillingApp.DTO.BillingRequestDTO;
import com.example.BillingApp.Models.BillingDashBoard;
import com.example.BillingApp.Services.BillingDashBoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping()
public class BillingDashBoardController {

    @Autowired
    private BillingDashBoardService service;

    @GetMapping(path = "findAll")
    public ResponseEntity<List<BillingDashBoard>> getAllBillingDashBoardDetails(){

        List<BillingDashBoard> b = service.getAllBillingDashBoardDetails();

        return ResponseEntity.ok(b);

    }

    @PostMapping(path = "createBilling")
    public ResponseEntity<BillingDashBoard> createBilling (HttpServletRequest request, @RequestBody BillingRequestDTO requestObj){

        request.getAttribute("USER_ID");
        request.getAttribute("ROLE");

        BillingDashBoard billingDashBoard = service.createBilling(requestObj);
        return ResponseEntity.ok(billingDashBoard);
    }

    @GetMapping("/billing/history")
    public String billingHistory(Model model) {
        model.addAttribute("billingList",
                service.getAllBillingDashBoardDetails());
        return "billing-history";

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalTrips",
                service.getAllBillingDashBoardDetails().size());
        return "dashboard";
    }

    @GetMapping("/billing/new")
    public String createBillingPage() {
        return "create-billing";
    }

    @PostMapping("/billing/save")
    public String saveBilling(BillingRequestDTO request) {
        service.createBilling(request);
        return "redirect:/billing/history";
    }
}
