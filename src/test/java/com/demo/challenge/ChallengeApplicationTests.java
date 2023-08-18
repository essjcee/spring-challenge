package com.demo.challenge;

import com.demo.challenge.dao.CreditCardRepository;
import com.demo.challenge.model.CreditCard;
import com.demo.challenge.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChallengeApplicationTests {

	/*@Mock
	CreditCardRepository repo;

	@InjectMocks
	CreditCardService service;

	List<CreditCard> collection ;
	CreditCard card1, card2, card3;
	@BeforeEach
	public void firstBeforeEach() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeEach
	public void secondBeforeEach(){
		card1 = new CreditCard();
		card1.setGender("M");
		card1.setJob("Developer");
		card1.setLast("Jones");
		card1.setFirst("Oliver");
		card1.setDob((int) new Date().getTime());
		card1.setCustomer(1);

		card1 = new CreditCard();
		card1.setGender("F");
		card1.setJob("Developer");
		card1.setLast("Anderson");
		card1.setFirst("Gillian");
		card1.setDob((int) new Date().getTime());
		card1.setCustomer(2);

		card1 = new CreditCard();
		card1.setGender("F");
		card1.setJob("Developer");
		card1.setLast("Anderson");
		card1.setFirst("Ann");
		card1.setDob((int) new Date().getTime());
		card1.setCustomer(2);

		collection = Arrays.asList(card1, card2, card3);
	}

	@Test
	public void getAllCards(){
		when(repo.findAll()).thenReturn(collection);
		assertEquals(3,service.allCards().size());
		assertEquals(collection , service.allCards());
	}

	@Test
	public void addCard(){
		when(repo.save(card1)).thenReturn(null);
		service.addCard(card1);
		assertEquals(4,collection.size()+1);
	}*/

}
