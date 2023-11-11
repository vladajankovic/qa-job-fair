package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cards.*;
import utility.Utility;

public class Player {

	private int health;
	private List<Card> hand;
	private List<Card> deck;
	private Card lastPlayedCard;
	private static int initialNumberOfCards = 6;
	private static int initialHealthValue = 20;
	private boolean attackingStatus;
	private int damage;
	private int protectCounter;

	public Player(int health, List<Card> deck) {
		if (health > 0) {
			this.health = health;
		} else {
			this.health = initialHealthValue;
		}
		if (deck == null) {
			this.deck = Utility.generateCards();
		} else {
			this.deck = deck;
		}
		this.hand = new ArrayList<>();
		lastPlayedCard = null;
		attackingStatus = false;
		damage = 0;
		protectCounter = 0;
		shuffleDeck();
	}

	public int getProtectCounter() {
		return protectCounter;
	}

	public void useProtectCounter() {
		if (protectCounter > 0) {
			protectCounter -= 1;
		}
	}

	public void takeDamage(int amountOfDamage) {
		if (amountOfDamage > 0) {
			if (health > amountOfDamage) {
				health -= amountOfDamage;
			} else {
				health = 0;
			}
		}
	}

	public boolean getAttackingStatus() {
		return attackingStatus;
	}

	public void resetAttackingStatus() {
		attackingStatus = false;
	}

	public int getDamage() {
		return damage;
	}

	public void resetDamage() {
		damage = 0;
	}

	public int getHealth() {
		return health;
	}

	public List<Card> getHand() {
		return hand;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public Card getLastPlayedCard() {
		return lastPlayedCard;
	}

	public int getInitialNumberOfCards() {
		return initialNumberOfCards;
	}

	private void singleDeckShuffle() {
		int deckSize = deck.size();
		Random rand = new Random();
		for (int i = deckSize; i > 0; i--) {
			int randomIndex = rand.nextInt(Integer.MAX_VALUE) % i;
			Card temp = deck.get(i - 1);
			deck.set(i - 1, deck.get(randomIndex));
			deck.set(randomIndex, temp);
		}
	}

	public void shuffleDeck() {
		for (int i = 0; i < 3; i++) {
			singleDeckShuffle();
		}
	}

	public void populateDeck(List<Card> cardList) {
		deck.addAll(cardList);
	}

	public int getNumberOfCardsInDeck() {
		return deck.size();
	}

	public int getNumberOfCardsInHand() {
		return hand.size();
	}

	public void drawCard() {
		if (!deck.isEmpty()) {
			Card drawnCard = deck.remove(deck.size() - 1);
			hand.add(drawnCard);
		}
	}

	public void drawInitialCards() {
		for (int i = 0; i < initialNumberOfCards; i++) {
			drawCard();
		}
	}

	public void playCard(int cardNumber) {
		Card cardToPlay = null;
		for (Card card : hand) {
			if (card.getNumber() == cardNumber) {
				cardToPlay = card;
				break;
			}
		}

		if (cardToPlay == null) {
			System.out.println("No card in hand with the index '" + cardNumber + "'.\n"
					+ "Please enter a valid card number or 'end'.\n");
			return;
		}

		hand.remove(cardToPlay);
		cardToPlay.effect();

		lastPlayedCard = cardToPlay;

		if (cardToPlay instanceof AttackCard) {
			attackingStatus = true;
			damage += cardToPlay.getNumber();
			System.out.println("Attacking opponent with " + damage + " damage!");
		}
		if (cardToPlay instanceof BoostAttackCard) {
			damage += ((BoostAttackCard) cardToPlay).getBoost();
		}
		if (cardToPlay instanceof ProtectCard) {
			protectCounter += 1;
			System.out.println("Player receives one Protect Counter!\r\n"
					+ "Protect Counters: " + getProtectCounter() + "\r\n");
		}

	}

	public void playProtectCardInDefence() {
		Card cardToPlay = null;
		for (Card card : hand) {
			if (card instanceof ProtectCard) {
				cardToPlay = card;
				break;
			}
		}
		
		if (cardToPlay != null) {
			lastPlayedCard = cardToPlay;
			hand.remove(cardToPlay);
			cardToPlay.effect();
			System.out.println("You have avoided the opponents attack!");
		}
		else {
			System.out.println("You don't have this card in your hand...\r\n");
		}
	}

	public void playAttackCardInDefense(int cardNumber) {
		Card cardToPlay = null;
		for (Card card : hand) {
			if (card.getNumber() == cardNumber) {
				cardToPlay = card;
				break;
			}
		}

		if (cardToPlay != null) {
			lastPlayedCard = cardToPlay;
			hand.remove(cardToPlay);
			cardToPlay.effect();
			System.out.println(String.format(
					"You've defended yourself! You've been attacked for %d damage and you've used special ability of Attacking card %d to deflect the attack\r\n",
					cardNumber, cardNumber));
		} else {
			System.out.println("You don't have this card in your hand...\r\n");
		}
	}

	public boolean checkForProtectionWithAttackCard(int damage) {
		for (Card card : hand) {
			if (card instanceof AttackCard && card.getNumber() == damage) {
				return true;
			}
		}
		return false;
	}

	public boolean checkForProtectionWithProtectCard() {
		for (Card card : hand) {
			if (card instanceof ProtectCard) {
				return true;
			}
		}
		return false;
	}

	public boolean findNumberInHand(int number) {
		for (Card card : hand) {
			if (card.getNumber() == number) {
				return true;
			}
		}
		System.out.println(String.format("There is no card in hand with number %d\r\n", number));
		return false;
	}

	public void printHand() {
		System.out.println("Player's Hand:");
		for (Card card : hand) {
			System.out.println(card.getNumber() + "(" + card.description() + ")\r");
			// You can add additional details about the card if needed
		}
		System.out.println();
	}
}
