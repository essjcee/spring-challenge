package com.demo.challenge.dao;

import com.demo.challenge.model.CategorySpend;
import com.demo.challenge.model.LowHigh;
import com.demo.challenge.model.StateSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
@Repository
public class TransactionReportsRepository implements TransactionReports {

    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public List<StateSales> getTransactionTotalsByState() {
       GroupOperation groupByStateTransactions = group("state").sum("amt").as("sales");
       MatchOperation allStates = match(new Criteria("state").exists(true));
       ProjectionOperation includes = project("sales").and("state").previousOperation();
       SortOperation sortBySalesDesc = sort(Sort.by(Sort.Direction.DESC,"sales"));
       Aggregation stateAggregation  = newAggregation(allStates,groupByStateTransactions,sortBySalesDesc,includes);
       AggregationResults<StateSales> groupResults = mongoTemplate.aggregate(stateAggregation,"transactions",StateSales.class);
       List<StateSales> result = groupResults.getMappedResults();
       return result;
    }

    @Override
    public List<CategorySpend> getCategorySpendByState(String state) {
        GroupOperation groupByStateTransactions = group("category").sum("amt").as("spend");
        MatchOperation stateIs = match(new Criteria("state").is(state));
        ProjectionOperation includes = project("spend").and("category").previousOperation();
        SortOperation sortBySpendDesc = sort(Sort.by(Sort.Direction.DESC,"spend"));
        Aggregation stateAggregation  = newAggregation(stateIs,groupByStateTransactions,sortBySpendDesc,includes);
        AggregationResults<CategorySpend> groupResults = mongoTemplate.aggregate(stateAggregation,"transactions", CategorySpend.class);
        List<CategorySpend> result = groupResults.getMappedResults();
        return result;
    }

    public List<LowHigh> getLowHighTransactionTotals(double amount){
        List<LowHigh> lowHigh = new ArrayList<>();
        GroupOperation groupByHighLow = group().sum("amt").as("transTotal");
        MatchOperation lowAmounts = match(new Criteria("amt").lte(amount));
        Aggregation lowAggregation  = newAggregation(lowAmounts,groupByHighLow);
        AggregationResults<LowHigh> groupResults = mongoTemplate.aggregate(lowAggregation,"transactions", LowHigh.class);
        LowHigh result = groupResults.getUniqueMappedResult();
        LowHigh lh =  new LowHigh();
        lh.setLowHigh("Low Amounts");
        lh.setTransTotal(result.getTransTotal());
        lowHigh.add(lh);
        MatchOperation highAmounts = match( new Criteria("amt").gt(amount));
        Aggregation highAggregation  = newAggregation(highAmounts,groupByHighLow);
        groupResults = mongoTemplate.aggregate(highAggregation,"transactions", LowHigh.class);
        result = groupResults.getUniqueMappedResult();
        lh = new LowHigh();
        lh.setLowHigh("High Amounts");
        lh.setTransTotal(result.getTransTotal());
        lowHigh.add(lh);
        return lowHigh;
    }

    /*
        GroupOperation groupByCountrySumSales = group("country").sum("sales").as("total_sales");
        MatchOperation allCountries = match(new Criteria("country").exists(true));
        ProjectionOperation includes = project("total_sales").and("country").previousOperation();
        SortOperation sortBySalesDesc = sort(Sort.by(Sort.Direction.DESC,"total_sales"));
        Aggregation aggregation = newAggregation(allCountries,groupByCountrySumSales,sortBySalesDesc,includes);
        AggregationResults<CountrySales> groupResults = mongoTemplate.aggregate(aggregation, "orders", CountrySales.class);
        List<CountrySales> result = groupResults.getMappedResults();
        return result;
     */
}
