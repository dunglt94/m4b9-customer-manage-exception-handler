package com.example.customermanagementjpa.controller;


import com.example.customermanagementjpa.exception.DuplicateEmailException;
import com.example.customermanagementjpa.model.Customer;
import com.example.customermanagementjpa.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("inputs-not-acceptable");
    }

    @GetMapping
    public String showCustomers(ModelMap model) {
        Iterable<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "index";
    }

    @GetMapping("/create")
    public String showCreatForm(ModelMap model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping("/save")
    public String createCustomer(@ModelAttribute("customer") Customer customer) throws DuplicateEmailException {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/view")
    public String showDetails(@PathVariable Long id, ModelMap model) {
        model.addAttribute("customer", customerService.findById(id));
        return "view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, ModelMap model) {
        model.addAttribute("customer", customerService.findById(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateCustomer(Customer customer, RedirectAttributes redirectAttributes) throws DuplicateEmailException {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Customer updated successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.remove(id);
        redirectAttributes.addFlashAttribute("success", "Customer deleted successfully!");
        return "redirect:/customers";
    }
}
