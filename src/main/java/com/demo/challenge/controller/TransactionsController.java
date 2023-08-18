package com.demo.challenge.controller;

import com.demo.challenge.model.CategorySpend;
import com.demo.challenge.model.LowHigh;
import com.demo.challenge.model.StateSales;
import com.demo.challenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value="/transactions")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/statetransactions")
    public List<StateSales> getStateTransactionTotals(){
        return transactionService.getStateSalesTotals();
    }
    @GetMapping("/statetransactions/{state}")
    public List<CategorySpend> getStateSpendByCategory(@PathVariable String state){
        return transactionService.getCategorySpendByState(state);
    }
    @GetMapping("/statetransactions/highlow/{amount}")
    public List<LowHigh> getLowHighTransactionTotals(@PathVariable double amount){
        return transactionService.getLowHighTransactionTotals(amount);
    }


}
