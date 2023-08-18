package com.demo.challenge.dao;
import com.demo.challenge.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardRepository extends MongoRepository<CreditCard,Long> {
    //PagingAndSortingRepository
    public CreditCard findByCustomer(int id);
    public Long deleteByCustomer(int id);
}
