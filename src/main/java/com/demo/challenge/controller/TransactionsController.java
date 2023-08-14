package com.demo.challenge.controller;

import com.demo.challenge.model.CategorySpend;
import com.demo.challenge.model.StateSales;
import com.demo.challenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/transactions")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/statetransactions")
    public List<StateSales> getStateTransactionTotals(){
        return transactionService.getStateSalesTotals();
    }
    @GetMapping("statetransactions/{state}")
    public List<CategorySpend> getStateSpendByCategory(@PathVariable String state){
        return transactionService.getCategorySpendByState(state);
    }
}
