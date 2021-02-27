package com.finalproject.BankApplication.controller;

import com.finalproject.BankApplication.model.Assessment;
import com.finalproject.BankApplication.model.AssessmentStatus;
import com.finalproject.BankApplication.model.Customer;
import com.finalproject.BankApplication.service.AssessmentService;
import com.finalproject.BankApplication.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class AssessmentController {

    AssessmentService assessmentService;
    CustomerService customerService;


    @GetMapping("/checkStatusRequest")
    public String checkStatus() {
        return "findApplicationStatus";
    }

    @PostMapping("/checkStatusRequest")
    public String getStatus(@RequestParam("reference") String reference){
        int refId = Integer.parseInt(reference.substring(1)) - 12346789;
        char type = reference.charAt(0);
        return "redirect:/"+type+"/"+refId;
    }

    @GetMapping(value= "/{type}/{refId}")
    public String statusFound(@PathVariable("refId") int refId, @PathVariable("type") String type, Model model){
        Assessment assessment = assessmentService.findById(refId);
        model.addAttribute("assessment",assessment);
        if (type.equals("A")){
            return "foundAccountStatus";}
        else{
            return "foundLoanStatus";}
    }

    @GetMapping("/admin/tellerDashboard")
    public String tellerConsole(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findUserByEmail(auth.getName());
        Map<String, Integer> statistics = assessmentService.statistics();
        model.addAttribute("userName", "Welcome " + customer.getFirstName());
        model.addAttribute("adminMessage","Have a productive day!");
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        return "admin/tellerDashboard";}

    // ticket console

    @GetMapping("admin/ticket-console/opened")
    public String openedTicketConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findOpen();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleTicketOpened";
    }

    @GetMapping("admin/ticket-console/pending")
    public String pendingTicketConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findPending();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleTicketPending";
    }

    @GetMapping("admin/ticket-console/progress")
    public String progressTicketConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findWIP();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleTicketProgress";}

    @GetMapping("admin/ticket-console/completed")
    public String completedTicketConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findDone();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleTicketCompleted";}

    // account console

    @GetMapping("admin/account-console")
    public String accountConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findAccountRequest();
        model.addAttribute("assessments",assessmentList);

        return "admin/consoleAccount";
    }

    @GetMapping("admin/account-console/opened")
    public String openedAccountConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findAccountRequestOpen();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountOpened";
    }

    @GetMapping("admin/account-console/pending")
    public String pendingAccountConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findAccountRequestPending();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountPending";
    }

    @GetMapping("admin/account-console/progress")
    public String progressAccountConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findAccountRequestWIP();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountProgress";
    }

    @GetMapping("admin/account-console/completed")
    public String completedAccountConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findAccountRequestDone();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleAccountCompleted";
    }

    // loan console

    @GetMapping("admin/loan-console")
    public String loanConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findLoanRequest();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoan";
    }

    @GetMapping("admin/loan-console/opened")
    public String openedLoanConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findLoanRequestOpen();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoanOpened";
    }

    @GetMapping("admin/loan-console/pending")
    public String pendingLoanConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findLoanRequestPending();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoanPending";
    }

    @GetMapping("admin/loan-console/progress")
    public String progressLoanConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findLoanRequestWIP();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoanProgress";
    }

    @GetMapping("admin/loan-console/completed")
    public String completedLoanConsole(Model model){
        Map<String, Integer> statistics = assessmentService.statistics();
        statistics.forEach((key,value) -> {
            model.addAttribute(key,value);
        });
        List<Assessment> assessmentList = assessmentService.findLoanRequestDone();
        model.addAttribute("assessments",assessmentList);
        return "admin/consoleLoanCompleted";
    }

    @GetMapping(value={"admin/account-console/{action}/{id}",
                        "admin/ticket-console/{action}/{id}",
                        "admin/loan-console/{action}/{id}"})
    public String details(  @PathVariable String action,
                            @PathVariable int id, Model model){
        Assessment assessment = new Assessment();
        assessment = assessmentService.findById(id);
        if( action== "start" &&
                assessment.getStatus() == AssessmentStatus.PENDING){
                    assessmentService.start(id);
            };
        model.addAttribute("assessment",assessment);
        return "redirect:admin/assessment-details";
        }

}


