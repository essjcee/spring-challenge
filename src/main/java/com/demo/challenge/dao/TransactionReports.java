package com.demo.challenge.dao;

import com.demo.challenge.model.CategorySpend;
import com.demo.challenge.model.StateSales;

import java.util.List;
public interface TransactionReports {

    List<StateSales> getTransactionTotalsByState();
    List<CategorySpend> getCategorySpendByState(String state);

}

