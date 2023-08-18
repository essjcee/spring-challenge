package com.demo.challenge.controller;

import com.demo.challenge.model.CreditCard;
import com.mongodb.MongoClientException;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoWriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.demo.challenge.service.CreditCardService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value="/cards")
public class CreditCardController {
    Logger LOGGER = LoggerFactory.getLogger(CreditCardController.class);
    @Autowired
    CreditCardService creditCardService;

    @GetMapping
    public List<CreditCard> getAllCards(){
        return creditCardService.allCards().subList(0,10);
    }
    @GetMapping("/{id}")
    public Optional<CreditCard> getCreditCard(@PathVariable int id){
        return creditCardService.getCard(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void  addCard(@RequestBody CreditCard card) {
        try {
            creditCardService.addCard(card);
        }
        catch(MongoWriteException ex){
            LOGGER.warn("**** MongoDB Exception likely a duplicate key " + ex.getMessage());
        }
        catch(MongoClientException ex){
            LOGGER.warn("**** MongoDB Client Exception " + ex.getMessage());
        }
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCrediCard(@RequestBody CreditCard card) {
        creditCardService.updateCreditCard(card);
    }
    @DeleteMapping("/{customer_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreditCustomer(@PathVariable int customer_id){
        creditCardService.deleteCreditCard(customer_id);
    }

}
