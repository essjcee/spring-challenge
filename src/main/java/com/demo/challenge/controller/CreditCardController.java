package com.demo.challenge.controller;

import com.demo.challenge.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.demo.challenge.service.CreditCardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/cards")
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @GetMapping
    public List<CreditCard> getAllCards(){
        return creditCardService.allCards();
    }
    @GetMapping("/{id}")
    public Optional<CreditCard> getCreditCard(@PathVariable int id){
        return creditCardService.getCard(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void  addCard(@RequestBody CreditCard card) {
        creditCardService.addCard(card);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCrediCard(@RequestBody CreditCard card) {
        creditCardService.updateCreditCard(card);
    }
    @DeleteMapping("/{customer}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreditCustomer(@PathVariable int customer){
        creditCardService.deleteCreditCard(customer);
    }

}
