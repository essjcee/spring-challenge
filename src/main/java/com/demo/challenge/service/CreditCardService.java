package com.demo.challenge.service;

import com.demo.challenge.dao.CreditCardRepository;
import com.demo.challenge.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;

    public List<CreditCard> allCards(){
        return creditCardRepository.findAll();
    }

    public Optional<CreditCard> getCard(int id){
        return Optional.ofNullable(creditCardRepository.findByCustomer(id));
    }

    public void addCard(CreditCard card){
        creditCardRepository.insert(card);
    }

    public void updateCreditCard(CreditCard card){
        int id = card.getCustomer();
        CreditCard cardToUpdate = creditCardRepository.findByCustomer(id);
        cardToUpdate.setFirst(card.getFirst());
        cardToUpdate.setLast(card.getLast());
        cardToUpdate.setDob(card.getDob());
        cardToUpdate.setJob(card.getJob());
        cardToUpdate.setGender(card.getGender());
        creditCardRepository.save(cardToUpdate);
    }

    public void deleteCreditCard(int id){
        creditCardRepository.deleteByCustomer(id);
    }
}
