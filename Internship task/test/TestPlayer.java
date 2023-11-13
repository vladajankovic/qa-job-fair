package test;

import org.junit.Test;

import cards.AttackCard;
import cards.BoostAttackCard;
import cards.Card;
import cards.ProtectCard;
import player.Player;
import utility.Utility;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestPlayer {

	@Test
	public void testPlayerConstructor() {
		Player testPlayer = new Player(0, null);
		assertNotNull("Player: Constructor is null!", testPlayer);
	}

	@Test
	public void testPlayerInitHealth() {
		for (int i = -100; i <= 100; i++) {
			Player testPlayer = new Player(i, null);
			assertNotNull("Player: Constructor is null!", testPlayer);
			if (i < 1) {
				assertEquals("Player: Bad return value of method getHealth()!", 20, testPlayer.getHealth());
			}
			else {
				assertEquals("Player: Bad return value of method getHealth()!", i, testPlayer.getHealth());
			}
		}
	}

	@Test
	public void testPlayerInitDeck1() {
		Player testPlayer = new Player(0, null);
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertNotNull("Player: Attribute deck is null!", testPlayer.getDeck());
	}

	@Test
	public void testPlayerInitDeck2() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertNotNull("Player: Attribute deck is null!", testPlayer.getDeck());
		assertEquals("Player: Initial deck size must be 25!", 25, testPlayer.getDeck().size());
	}

	@Test
	public void testPlayerInitHand1() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertNotNull("Player: Attribute hand is null!", testPlayer.getHand());
	}

	@Test
	public void testPlayerInitHand2() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertEquals("Player: Starting hand size must be 0!", 0, testPlayer.getHand().size());
	}

	@Test
	public void testPlayerInitLastPlayedCard() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertNull("Player: Initial value of attribute lastPlayedCard must be null!", testPlayer.getLastPlayedCard());
	}

	@Test
	public void testPlayerInitAttackingStatus() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertFalse("Player: Initial value of attribute attackingStatus must be false!",
				testPlayer.getAttackingStatus());
	}

	@Test
	public void testPlayerInitDamage() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertEquals("Player: Initial value of attribute damage must be 0!", 0, testPlayer.getDamage());
	}

	@Test
	public void testPlayerInitProtectCounter() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		assertEquals("Player: Initial value of attribute protectCounter must be 0!", 0, testPlayer.getProtectCounter());
	}

	@Test
	public void testPlayerDeckShuffle() {
		Player testPlayer = new Player(0, Utility.generateCards());
		assertNotNull("Player: Constructor is null!", testPlayer);
		List<Card> unshuffledDeck = Utility.generateCards();
		int match = 0;
		for (int i = 0; i < unshuffledDeck.size(); i++) {
			if (unshuffledDeck.get(i).getNumber() == testPlayer.getDeck().get(i).getNumber()) {
				match += 1;
			}
		}
		assertNotEquals("Player: Initial deck is not shuffled!", 25, match);
	}

	@Test
	public void testTakeDamage1() {
		Player player = new Player(10, Utility.generateCards());
		player.takeDamage(1);
		assertEquals("Player: Bad 'take damage' calculation!", 9, player.getHealth());
	}

	@Test
	public void testTakeDamage2() {
		Player player = new Player(10, Utility.generateCards());
		player.takeDamage(10);
		assertEquals("Player: Bad 'take damage' calculation!", 0, player.getHealth());
	}

	@Test
	public void testTakeDamage3() {
		Player player = new Player(10, Utility.generateCards());
		player.takeDamage(15);
		assertEquals("Player: Bad 'take damage' calculation!", 0, player.getHealth());
	}

	@Test
	public void testTakeDamage4() {
		Player player = new Player(10, Utility.generateCards());
		player.takeDamage(-300);
		assertEquals("Player: Bad 'take damage' calculation!", 10, player.getHealth());
	}
	
	@Test
	public void testTakeDamage5() {
		Player player = new Player(10, Utility.generateCards());
		player.takeDamage(6);
		assertEquals("Player: Bad 'take damage' calculation!", 6, 10 - player.getHealth());
	}

	@Test
	public void testAttackStatusAttackCard() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new AttackCard(7));
		Player player = new Player(10, deck);
		assertFalse("Player: Initial attacking status must be false!", player.getAttackingStatus());

		player.drawCard();
		player.playCard(7);
		assertTrue("Player: Attacking status must be true, when Attack Card is played!", player.getAttackingStatus());
		player.resetAttackingStatus();
		assertFalse("Player: Attacking status after reset must be false!", player.getAttackingStatus());
	}

	@Test
	public void testAttackStatusBoostAttackCard() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new BoostAttackCard());
		Player player = new Player(10, deck);
		assertFalse("Player: Initial attacking status must be false!", player.getAttackingStatus());

		player.drawCard();
		player.playCard(2);
		assertFalse("Player: Attacking status must be false, when Boost Attack Card is played!",
				player.getAttackingStatus());
	}

	@Test
	public void testAttackStatusProtectCard() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new ProtectCard());
		Player player = new Player(10, deck);
		assertFalse("Player: Initial attacking status must be false!", player.getAttackingStatus());

		player.drawCard();
		player.playCard(1);
		assertFalse("Player: Attacking status must be false, when ProtectCard is played!", player.getAttackingStatus());
	}

	@Test
	public void testDamageAttackCard() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new AttackCard(7));
		Player player = new Player(10, deck);
		assertEquals("Player: Initial damage must be 0!", 0, player.getDamage());

		player.drawCard();
		player.playCard(7);
		assertEquals("Player: Damage after must be 7 after playing AttackCard(7)!", 7, player.getDamage());

		player.resetDamage();
		assertEquals("Player: Damage after reset must be 0!", 0, player.getDamage());
	}

	@Test
	public void testDamageBoostAttackCard() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new BoostAttackCard());
		Player player = new Player(10, deck);
		assertEquals("Player: Initial damage must be 0!", 0, player.getDamage());

		player.drawCard();
		player.playCard(2);
		assertEquals("Player: Damage after must be 3 after playing Boost Attack Card!", 3, player.getDamage());

		player.resetDamage();
		assertEquals("Player: Damage after reset must be 0!", 0, player.getDamage());
	}

	@Test
	public void testDamageProtectCard() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new ProtectCard());
		Player player = new Player(10, deck);
		assertEquals("Player: Initial damage must be 0!", 0, player.getDamage());

		player.drawCard();
		player.playCard(1);
		assertEquals("Player: Damage after must be 0 after playing Protect Card!", 0, player.getDamage());

		player.resetDamage();
		assertEquals("Player: Damage after reset must be 0!", 0, player.getDamage());
	}

	@Test
	public void testDamageOutput1() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new BoostAttackCard());
		deck.add(new BoostAttackCard());
		deck.add(new BoostAttackCard());
		Player player = new Player(10, deck);
		assertEquals("Player: Initial damage must be 0!", 0, player.getDamage());

		player.drawCard();
		player.playCard(2);
		assertEquals("Player: Damage after must be 3 after playing Boost Attack Card!", 3, player.getDamage());

		player.drawCard();
		player.playCard(2);
		assertEquals("Player: Damage after must be 6 after playing Boost Attack Card!", 6, player.getDamage());

		player.drawCard();
		player.playCard(2);
		assertEquals("Player: Damage after must be 9 after playing Boost Attack Card!", 9, player.getDamage());

	}

	@Test
	public void testDamageOutput2() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new AttackCard(7));
		deck.add(new BoostAttackCard());
		deck.add(new BoostAttackCard());
		Player player = new Player(10, deck);
		assertEquals("Player: Initial damage must be 0!", 0, player.getDamage());

		player.drawCard();
		player.drawCard();
		player.drawCard();

		player.playCard(2);
		assertEquals("Player: Damage after must be 3 after playing Boost Attack Card!", 3, player.getDamage());

		player.playCard(2);
		assertEquals("Player: Damage after must be 6 after playing Boost Attack Card!", 6, player.getDamage());

		player.playCard(7);
		assertEquals("Player: Damage after must be 13 after playing AttackCard(7)!", 13, player.getDamage());
	}

	@Test
	public void testDrawingCardsFullDeck() {
		Player player = new Player(20, Utility.generateCards());

		assertEquals("Player: Must have 25 cards in initial deck!", 25, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 0 cards in initial hand!", 0, player.getNumberOfCardsInHand());

		player.drawInitialCards();

		assertEquals("Player: Must have 19 cards left in deck!", 19, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 6 cards in hand!", 6, player.getNumberOfCardsInHand());

		player.drawCard();

		assertEquals("Player: Must have 18 cards left in deck!", 18, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 7 cards in hand!", 7, player.getNumberOfCardsInHand());
	}

	@Test
	public void testDrawingCardsEmptyDeck() {
		Player player = new Player(20, Utility.generateCards());

		assertEquals("Player: Must have 25 cards in initial deck!", 25, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 0 cards in initial hand!", 0, player.getNumberOfCardsInHand());

		for (int i = 0; i < 4; i++) {
			player.drawInitialCards();
		}

		assertEquals("Player: Must have 1 cards left in deck!", 1, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 24 cards in hand!", 24, player.getNumberOfCardsInHand());

		player.drawCard();

		assertEquals("Player: Must have 0 cards left in deck!", 0, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 25 cards in hand!", 25, player.getNumberOfCardsInHand());

		player.drawCard();

		assertEquals("Player: Must have 0 cards left in deck!", 0, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 25 cards in hand!", 25, player.getNumberOfCardsInHand());
	}

	@Test
	public void testPlayingCardsNoCard() {
		Player player = new Player(20, Utility.generateCards());

		try {
			player.playCard(0);
			assertTrue(true);
		} catch (NullPointerException e) {
			assertTrue("Player: A NullPointerException is thrown!", false);
		}
	}

	@Test
	public void testPlayingCardsPlayCard() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 6; i++) {
			deck.add(new BoostAttackCard());
		}
		Player player = new Player(20, deck);

		assertEquals("Player: Must have 6 cards in deck!", 6, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 0 cards in hand!", 0, player.getNumberOfCardsInHand());

		player.drawInitialCards();

		assertEquals("Player: Must have 0 cards in deck!", 0, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 6 cards in hand!", 6, player.getNumberOfCardsInHand());

		player.playCard(2);

		assertEquals("Player: Must have 0 cards in deck!", 0, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 5 cards in hand!", 5, player.getNumberOfCardsInHand());

		player.playCard(2);
		player.playCard(2);
		player.playCard(2);

		assertEquals("Player: Must have 0 cards in deck!", 0, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 2 cards in hand!", 2, player.getNumberOfCardsInHand());
	}

	@Test
	public void testPlayProtectCard() {
		List<Card> deck = new ArrayList<Card>();
		deck.add(new ProtectCard());
		Player player = new Player(20, deck);

		assertEquals("Player: Must have 1 cards in deck!", 1, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 0 cards in hand!", 0, player.getNumberOfCardsInHand());

		player.drawCard();

		assertEquals("Player: Must have 0 cards in deck!", 0, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 1 cards in hand!", 1, player.getNumberOfCardsInHand());

		assertEquals("Player: Protect Counter must be 0!", 0, player.getProtectCounter());

		player.playCard(1);

		assertEquals("Player: Must have 0 cards in deck!", 0, player.getNumberOfCardsInDeck());
		assertEquals("Player: Must have 0 cards in hand!", 0, player.getNumberOfCardsInHand());

		assertEquals("Player: Protect Counter must be 1!", 1, player.getProtectCounter());
	}

	@Test
	public void testProtectCardPlayAndEffect() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 6; i++) {
			deck.add(new ProtectCard());
		}
		Player player = new Player(10, deck);

		player.drawInitialCards();

		assertEquals("Player: Protect Counter must be 0!", 0, player.getProtectCounter());

		player.playCard(1);
		player.playCard(1);
		player.playCard(1);

		assertEquals("Player: Protect Counter must be 3!", 3, player.getProtectCounter());

		player.useProtectCounter();
		player.useProtectCounter();

		assertEquals("Player: Protect Counter must be 1!", 1, player.getProtectCounter());

		player.playCard(1);
		player.playCard(1);
		player.playCard(1);

		assertEquals("Player: Protect Counter must be 4!", 4, player.getProtectCounter());
	}
	
	@Test
	public void testProtectionWithProtectCounter() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			deck.add(new AttackCard(7));
		}
		deck.add(new ProtectCard());
		Player player = new Player(10, deck);

		player.drawInitialCards();
		
		player.playCard(1);

		assertTrue("Player: Player must have Protect Counters!", player.getProtectCounter() > 0);
		assertEquals("Player: Protect Counter must be 1!", 1, player.getProtectCounter());
		
		if (player.getProtectCounter() > 0) {
			player.takeDamage(0);
		}
		else {
			player.takeDamage(5);
		}
		assertEquals("Player: Player could have blocked the attack!", 10, player.getHealth());
	}
	
	@Test
	public void testProtectionWithNoProtectCounter() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 6; i++) {
			deck.add(new AttackCard(7));
		}
		Player player = new Player(10, deck);

		player.drawInitialCards();

		assertFalse("Player: Player must have no Protect Counters!", player.getProtectCounter() > 0);
		assertEquals("Player: Protect Counter must be 0!", 0, player.getProtectCounter());
		
		if (player.getProtectCounter() > 0) {
			player.takeDamage(0);
		}
		else {
			player.takeDamage(5);
		}
		assertEquals("Player: Player could not have blocked the attack!", 5, player.getHealth());
	}

	@Test
	public void testProtectionWithProtectCardInHand() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			deck.add(new AttackCard(7));
		}
		deck.add(new ProtectCard());
		Player player = new Player(10, deck);

		player.drawInitialCards();

		assertTrue("Player: There must be one Protect Card in hand!", player.checkForProtectionWithProtectCard());
		
		if (player.checkForProtectionWithProtectCard()) {
			player.takeDamage(0);
		}
		else {
			player.takeDamage(5);
		}
		assertEquals("Player: Player could have blocked the attack!", 10, player.getHealth());
	}

	@Test
	public void testProtectionWithNoProtectCardInHand() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 6; i++) {
			deck.add(new AttackCard(7));
		}
		Player player = new Player(10, deck);

		player.drawInitialCards();

		assertFalse("Player: There must not be any Protect Card in hand!", player.checkForProtectionWithProtectCard());
		
		if (player.checkForProtectionWithProtectCard()) {
			player.takeDamage(0);
		}
		else {
			player.takeDamage(5);
		}
		assertEquals("Player: Player could not have blocked the attack!", 5, player.getHealth());
	}

	@Test
	public void testProtectionWithAttackCardEqualDamage() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 6; i++) {
			deck.add(new AttackCard(3 + i));
		}
		Player player = new Player(10, deck);

		player.drawInitialCards();

		assertTrue("Player: There must be one Attack Card (5) in hand that can BLOCK this attack for 5 damage!",
				player.checkForProtectionWithAttackCard(5));
		
		if (player.checkForProtectionWithAttackCard(5)) {
			player.takeDamage(0);
		}
		else {
			player.takeDamage(5);
		}
		assertEquals("Player: Player could have blocked the attack!", 10, player.getHealth());
	}

	@Test
	public void testProtectionWithAttackCardNotEqualDamage() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 6; i++) {
			deck.add(new AttackCard(3 + i));
		}
		Player player = new Player(20, deck);

		player.drawInitialCards();

		assertFalse("Player: There must not be any Attack Card in hand that can Block this attack for 10 damage!",
				player.checkForProtectionWithAttackCard(10));
		
		if (player.checkForProtectionWithAttackCard(10)) {
			player.takeDamage(0);
		}
		else {
			player.takeDamage(10);
		}
		assertEquals("Player: Player could not have blocked the attack!", 10, player.getHealth());
	}

	@Test
	public void testFindNumberInHand() {
		List<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 6; i++) {
			deck.add(new AttackCard(i));
		}

		Player player = new Player(10, deck);

		player.drawInitialCards();

		for (int i = 0; i < 6; i++) {
			assertTrue("Player: Error, expected true!", player.findNumberInHand(i));
		}

		assertFalse("Player: Error, expected false!", player.findNumberInHand(-1));
		assertFalse("Player: Error, expected false!", player.findNumberInHand(6));
	}

}
