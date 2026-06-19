package com.example.billingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bills")
public class BillingController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerClient customerClient;

    @GetMapping
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        bill.setStatus("PENDING");
        return billRepository.save(bill);
    }

    @GetMapping("/customer/{customerId}")
    public Map<String, Object> getBillsByCustomer(@PathVariable Long customerId) {
        Customer customer = customerClient.getCustomerById(customerId);
        List<Bill> bills = billRepository.findByCustomerId(customerId);
        Map<String, Object> response = new HashMap<>();
        response.put("customer", customer);
        response.put("bills", bills);
        return response;
    }

    @PutMapping("/{id}/pay")
    public Bill payBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        if (bill != null) {
            bill.setStatus("PAID");
            return billRepository.save(bill);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteBill(@PathVariable Long id) {
        billRepository.deleteById(id);
        return "Bill deleted successfully";
    }
}
