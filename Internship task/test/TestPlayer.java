package test;

import org.junit.Test;

import cards.Card;
import player.Player;
import utility.Utility;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

public class TestPlayer {
	
	@Test
	public void testPlayerConstructor()
	{
		Player testPlayer = new Player(0, null);
		assertNotNull("Player: Constructor is null!", testPlayer);
	}
	
	@Test
	public void testPlayerInitHealth()
	{
		for(int i = -100; i <= 100; i++)
		{
			Player testPlayer = new Player(i, null);
			assertNotNull("Player: Constructor is null!", testPlayer);
			assertEquals("Player: Bad return value of method getHealth()!", 
					i, testPlayer.getHealth());
		}
	}
	
	@Test
	public void testPlayerInitDeck1()
	{
		Player testPlayer = new Player(0, null);
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertNull("Player: Bad return value of method getDeck()!", 
				testPlayer.getDeck());
	}
	
	@Test
	public void testPlayerInitDeck2()
	{
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertNotNull("Player: Bad return value of method getDeck()!", 
				testPlayer.getDeck());
		assertEquals("Player: Initial deck size is not 25!", 
				25, testPlayer.getDeck().size());
	}
	
	@Test
	public void testPlayerInitHand1()
	{
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertNotNull("Player: Attribute hand is null!", testPlayer.getHand());
	}
	
	@Test
	public void testPlayerInitHand2()
	{
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertEquals("Player: Starting hand size is not 0!", 0, testPlayer.getHand().size());
	}
	
	@Test
	public void testPlayerInitLastPlayedCard()
	{
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertNull("Player: Initial value of attribute lastPlayedCard is not null!", 
				testPlayer.getLastPlayedCard());
	}
	
	@Test
	public void testPlayerInitAttackingStatus()
	{
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertFalse("Player: Initial value of attribute attackingStatus is not false!", 
				testPlayer.getAttackingStatus());
	}
	
	@Test
	public void testPlayerInitDamage() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertEquals("Player: Initial value of attribute damage is not 0!", 
				0, testPlayer.getDamage());
	}
	
	@Test
	public void testPlayerDeckShuffle() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		List<Card> unshuffledDeck = Utility.generateCards();
		int match = 0;
		for(int i = 0; i < unshuffledDeck.size(); i++)
		{
			if (unshuffledDeck.get(i).getNumber() == testPlayer.getDeck().get(i).getNumber())
			{
				match += 1;
			}
		}
		assertNotEquals("Player: Initial deck is not shuffled!", 25, match);
	}
	
	@Test
	public void testTakeDamage(){
	    new Utility();
	    Player player = new Player(10, Utility.generateCards());
	    player.takeDamage(1);
	    assertEquals(9, player.getHealth());
	}
    
}
