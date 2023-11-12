package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import cards.AttackCard;
import cards.BoostAttackCard;
import cards.Card;
import cards.ProtectCard;
import utility.Utility;

public class TestUtility {

	@Test
	public void testGenerateCardsIsListNull() {
		List<Card> testList = Utility.generateCards();
		assertNotNull("Utility: Generated list is null!", testList);
	}

	@Test
	public void testGenerateCardsIsListEmpty() {
		List<Card> testList = Utility.generateCards();
		assertNotNull("Utility: Generated list is null!", testList);
		assertNotEquals("Utility: Generated list is empty!", 0, testList.size());
	}

	@Test
	public void testGenerateCardsIsListFull() {
		List<Card> testList = Utility.generateCards();
		assertNotNull("Utility: Generated list is null!", testList);
		assertEquals("Utility: Generated list size is not 25!", 25, testList.size());
	}

	@Test
	public void testGenerateCardsAttackCardsIndex1() {
		List<Card> testList = Utility.generateCards();
		assertNotNull("Utility: Generated list is null!", testList);

		for (int i = 0; i < testList.size(); i++) {
			if (testList.get(i) instanceof AttackCard) {
				assertTrue("Utility: Attack Cards must have indexes in range [3-7]!",
						3 <= testList.get(i).getNumber() && testList.get(i).getNumber() <= 7);
			}
		}
	}

	@Test
	public void testGenerateCardsAttackCardsIndex2() {
		List<Card> testList = Utility.generateCards();
		assertNotNull("Utility: Generated list is null!", testList);

		int[] count_indexes = new int[5];
		for (int i = 0; i < testList.size(); i++) {
			if (testList.get(i) instanceof AttackCard) {
				count_indexes[testList.get(i).getNumber() - 3] += 1;
			}
		}
		for (int i = 0; i < count_indexes.length; i++) {
			assertEquals("Utility: The deck must contain each Attack Card [3-7] x2", 2, count_indexes[i]);
		}
	}

	@Test
	public void testGenerateCardsAttackCardsNumber() {
		List<Card> testList = Utility.generateCards();
		assertNotNull("Utility: Generated list is null!", testList);

		int counter = 0;
		for (int i = 0; i < testList.size(); i++) {
			if (testList.get(i) instanceof AttackCard) {
				counter += 1;
			}
		}
		assertEquals("Utility: There must be 10 Attack Cards in a deck!", 10, counter);
	}

	@Test
	public void testGenerateCardsBoostAttackCardsNumber() {
		List<Card> testList = Utility.generateCards();
		assertNotNull("Utility: Generated list is null!", testList);

		int counter = 0;
		for (int i = 0; i < testList.size(); i++) {
			if (testList.get(i) instanceof BoostAttackCard) {
				counter += 1;
			}
		}
		assertEquals("Utility: There must be 10 Boost Attack Cards in a deck!", 10, counter);
	}

	@Test
	public void testGenerateCardsProtectCardsNumber() {
		List<Card> testList = Utility.generateCards();
		assertNotNull("Utility: Generated list is null!", testList);

		int counter = 0;
		for (int i = 0; i < testList.size(); i++) {
			if (testList.get(i) instanceof ProtectCard) {
				counter += 1;
			}
		}
		assertEquals("Utility: There must be 5 Protect Cards in a deck!", 5, counter);
	}

}
