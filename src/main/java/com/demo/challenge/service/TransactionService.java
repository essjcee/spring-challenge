package com.demo.challenge.service;

import com.demo.challenge.dao.TransactionReports;
import com.demo.challenge.model.CategorySpend;
import com.demo.challenge.model.LowHigh;
import com.demo.challenge.model.StateSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionReports repository;

    public List<StateSales> getStateSalesTotals(){
        return repository.getTransactionTotalsByState();
    }

    public List<CategorySpend> getCategorySpendByState(String state){
        return repository.getCategorySpendByState(state);
    }

    public List<LowHigh> getLowHighTransactionTotals(double amount){
        return repository.getLowHighTransactionTotals(amount);
    }
}
