package test;

import org.junit.Test;

import cards.BoostAttackCard;
import cards.Card;
import cards.ProtectCard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestCardClasses {
	
	@Test
	public void testProtectCardConstructor()
	{
		Card protectCardTest = new ProtectCard();
		assertNotNull("ProtectCard: Constructor returned null!", protectCardTest);
	}
	
	@Test
	public void testProtectCardIndex()
	{
		Card protectCardTest = new ProtectCard();
		assertNotNull("ProtectCard: Constructor returned null!", protectCardTest);
		
		int cardIndex = protectCardTest.getNumber();
		assertEquals("ProtectCard: Card indexes is not 1!", 1, cardIndex);
	}
	
	@Test
	public void testProtectCardDescription()
	{
		Card protectCardTest = new ProtectCard();
		assertNotNull("ProtectCard: Constructor returned null!", protectCardTest);
		
		String description = protectCardTest.description();
		String testDescription = "Protect card";
		
		assertTrue("ProtectCard: Description should be 'Protect card', but is '" + description + "'!", 
				description.equals(testDescription));
	}
	
	@Test
	public void testTwoProtectCards()
	{
		Card protectCard1 = new ProtectCard();
		Card protectCard2 = new ProtectCard();
		assertNotNull(protectCard1);
		assertNotNull(protectCard2);
		assertNotEquals(protectCard1, protectCard2);
		
		assertEquals("ProtectCard: Card indexes is not 1!",
				1, protectCard1.getNumber());
		
		assertEquals("ProtectCard: Card indexes is not 1!",
				1, protectCard2.getNumber());
		
		assertEquals("ProtectCard: Card indexes are different!",
				protectCard1.getNumber(), protectCard2.getNumber());
		
		String description1 = protectCard1.description();
		String description2 = protectCard2.description();
		String testDescription = "Protect card";
		
		assertTrue("ProtectCard: Description should be 'Protect card', but is '" + description1 + "'!", 
				description1.equals(testDescription));
		assertTrue("ProtectCard: Description should be 'Protect card', but is '" + description2 + "'!", 
				description2.equals(testDescription));
		assertTrue("ProtectCard: Descriptions are different!", 
				description1.equals(description2));
	}

	@Test
	public void testBoostAttackCardConstructor()
	{
		Card testBoostAttackCard = new BoostAttackCard();
		assertNotNull("BoostAttackCard: Constructor returned null!", testBoostAttackCard);
	}

	@Test
	public void testBoostAttackCardIndex()
	{
		Card testBoostAttackCard = new BoostAttackCard();
		assertNotNull("BoostAttackCard: Constructor returned null!", testBoostAttackCard);
		
		int cardIndex = testBoostAttackCard.getNumber();
		assertEquals("BoostAttackCard: Card indexes is not 2!", 2, cardIndex);
	}
	
	@Test
	public void testBoostAttackCardDescription()
	{
		Card testBoostAttackCard = new BoostAttackCard();
		assertNotNull("BoostAttackCard: Constructor returned null!", testBoostAttackCard);
		
		String description = testBoostAttackCard.description();
		String testDescription = "Boost card";
		assertTrue("BoostAttackCard: Description should be 'Boost card', but is '" + description + "'!", 
				description.equals(testDescription));
	}
	
	@Test
	public void testBoostAttackCardBoost()
	{
		Card testBoostAttackCard = new BoostAttackCard();
		assertNotNull("BoostAttackCard: Constructor returned null!", testBoostAttackCard);
		
		int boost = ((BoostAttackCard)testBoostAttackCard).getBoost();
		assertEquals("BoostAttackCard: Card boost is not 3!", 3, boost);
	}
	
	@Test
	public void testTwoBoostAttackCards()
	{
		Card boostAttackCard1 = new BoostAttackCard();
		Card boostAttackCard2 = new BoostAttackCard();
		assertNotNull("BoostAttackCard: Constructor returned null!", boostAttackCard1);
		assertNotNull("BoostAttackCard: Constructor returned null!", boostAttackCard2);
		assertNotEquals(boostAttackCard1, boostAttackCard2);
		
		assertEquals("BoostAttackCard: Card indexes is not 2!", 2, boostAttackCard1.getNumber());
		assertEquals("BoostAttackCard: Card indexes is not 2!", 2, boostAttackCard2.getNumber());
		assertEquals("BoostAttackCard: Card indexes are different!", 
				boostAttackCard1.getNumber(), boostAttackCard2.getNumber());
		
		String description1 = boostAttackCard1.description();
		String description2 = boostAttackCard2.description();
		String testDescription = "Boost card";
		
		assertTrue("BoostAttackCard: Description should be 'Boost card', but is '" + description1 + "'!", 
				description1.equals(testDescription));
		assertTrue("BoostAttackCard: Description should be 'Boost card', but is '" + description2 + "'!", 
				description2.equals(testDescription));
		assertTrue("BoostAttackCard: Descriptions are different!", 
				description1.equals(description2));
		
		assertEquals("BoostAttackCard: Card boost is not 3!", 
				3, ((BoostAttackCard)boostAttackCard1).getBoost());
		assertEquals("BoostAttackCard: Card boost is not 3!", 
				3, ((BoostAttackCard)boostAttackCard2).getBoost());
		assertEquals("BoostAttackCard: Card boosts are different!", 
				((BoostAttackCard)boostAttackCard1).getBoost(), 
				((BoostAttackCard)boostAttackCard2).getBoost());
		
	}
	
}
