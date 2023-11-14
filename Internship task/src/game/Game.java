package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import player.*;
import utility.Utility;

public class Game {
	private Player player1;
	private Player player2;
	private Scanner scanner;
	private boolean gameEnded;

	public Game(Player player1, Player player2) {
		if (player1 == null) {
			player1 = new Player(20, Utility.generateCards());
		}
		this.player1 = player1;
		if (player2 == null || player1 == player2) {
			player2 = new Player(20, Utility.generateCards());
		}
		this.player2 = player2;
		this.scanner = new Scanner(System.in);
		gameEnded = false;
	}

	public boolean getGameEnded() {
		return gameEnded;
	}

	private boolean isPlayerWithoutOptionsToPlay(Player player) {
		return isHandEmpty(player) && isDeckEmpty(player);
	}

	private boolean hasHealth(Player player) {
		return player.getHealth() != 0;
	}

	private boolean isHandEmpty(Player player) {
		return player.getHand().isEmpty();
	}

	private boolean isDeckEmpty(Player player) {
		return player.getDeck().isEmpty();
	}

	private void printPlayerStats(Player player) {
		System.out.println("---------------------------");
		System.out.println("Health: " + player.getHealth());
		System.out.println("Protect Counters: " + player.getProtectCounter());
		System.out.println("Damage output: " + player.getDamage());
		System.out.println("Cards in Deck: " + player.getDeck().size());
		System.out.println("Cards in Hand: " + player.getHand().size());
	}

	public void startGame() {
		// Draw initial cards for both players
		player1.drawInitialCards();
		player2.drawInitialCards();

		// Game loop
		while (!getGameEnded()) {
			// Player 1's turn
			System.out.println("#################################");
			System.out.println("#################################");
			System.out.println("Player 1's Turn");
			if (isPlayerWithoutOptionsToPlay(player1)) {
				System.out.println("You lost all your cards... \r\n Player 2 wins!");
				gameEnded = true;
				break;
			}

			playTurn(player1, player2);

			if (!hasHealth(player1)) {
				System.out.println("Player 2 wins!");
				gameEnded = true;
				break;
			}

			// Player 2's turn
			System.out.println("#################################");
			System.out.println("#################################");
			System.out.println("Player 2's Turn");
			if (isPlayerWithoutOptionsToPlay(player2)) {
				System.out.println("You lost all your cards... \r\n Player 1 wins!");
				gameEnded = true;
				break;
			}

			playTurn(player2, player1);

			if (!hasHealth(player2)) {
				System.out.println("Player 1 wins!");
				gameEnded = true;
				break;
			}
		}
	}

	private void playTurn(Player currentPlayer, Player opponentPlayer) {
		currentPlayer.drawCard();
		currentPlayer.resetAttackingStatus();
		currentPlayer.resetDamage();

		if (opponentPlayer.getAttackingStatus()) {
			printPlayerStats(currentPlayer);
			currentPlayer.printHand();
			currentPlayerUnderAttack(currentPlayer, opponentPlayer);
			if (isHandEmpty(currentPlayer)) {
				System.out.println("No cards left in hand after defending. Turn end.\r\n");
				return;
			}
			if (!hasHealth(currentPlayer)) {
				return;
			}
		}

		while (true) {
			printPlayerStats(currentPlayer);
			currentPlayer.printHand();
			System.out.println("Enter the number of the card you want to play (or enter 'end' to end your turn):");

			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("end")) {
				System.out.println("Turn end.\n");
				break;
			}
			try {
				int cardNumber = Integer.parseInt(input);
				currentPlayer.playCard(cardNumber);
				// check if player can play anything else
				if (currentPlayer.getAttackingStatus()) {
					System.out.println("Attack card played. Turn end.\n");
					break;
				}
				if (isHandEmpty(currentPlayer)) {
					System.out.println("No cards left in hand. Turn end.\n");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid card number or 'end'.");
			}
		}
	}

	private void currentPlayerUnderAttack(Player currentPlayer, Player opponentPlayer) {

		System.out.println("WOW your opponent's attacking your health points with " + opponentPlayer.getDamage()
				+ " damage!!! \r\n");

		if (currentPlayer.getProtectCounter() > 0 || currentPlayer.checkForProtectionWithProtectCard()
				|| currentPlayer.checkForProtectionWithAttackCard(opponentPlayer.getDamage())) {
			tryToDefend(currentPlayer, opponentPlayer);
		}
		else {
			// player must take damage from attack
			currentPlayer.takeDamage(opponentPlayer.getDamage());
			System.out.println("Ohhh you've taken damage... Health: " + currentPlayer.getHealth() + "\r\n");
		}
	}

	private void tryToDefend(Player currentPlayer, Player opponentPlayer) {

		// player has a way to deflect the attack
		System.out.println("Avoid the attack or take the damage... \r\n" + "Select one option below:\n");

		List<String> options = new ArrayList<String>();
		int i = 0;
		System.out.println("" + (i++) + ": Take damage\r\n");
		options.add("take");
		if (currentPlayer.getProtectCounter() > 0) {
			System.out.println("" + (i++) + ": Use Protect Counter\r\n");
			options.add("counter");
		}
		if (currentPlayer.checkForProtectionWithProtectCard()) {
			System.out.println("" + (i++) + ": Use Protect Card in hand\r\n");
			options.add("protect");
		}
		if (currentPlayer.checkForProtectionWithAttackCard(opponentPlayer.getDamage())) {
			System.out.println("" + (i++) + ": Use Attack Card (" + opponentPlayer.getDamage() + ") in hand\r\n");
			options.add("attack");
		}

		String decision = getOption(i, options);

		if (decision.equalsIgnoreCase("take")) {
			// player wants to take damage
			currentPlayer.takeDamage(opponentPlayer.getDamage());
			System.out.println("Ohhh you've taken damage... Health: " + currentPlayer.getHealth() + "\r\n");
		}
		if (decision.equalsIgnoreCase("counter")) {
			// player doesn't take damage this turn - uses protect counter
			currentPlayer.useProtectCounter();
			System.out.println("Protection Counter used to BLOCK incoming attack!\r\n");
		}
		if (decision.equalsIgnoreCase("protect")) {
			// player doesn't take damage this turn - uses protect card
			currentPlayer.playProtectCardInDefence();
		}
		if (decision.equalsIgnoreCase("attack")) {
			// player doesn't take damage this turn - uses special ability of attacking card
			currentPlayer.playAttackCardInDefense(opponentPlayer.getDamage());
		}

	}

	private String getOption(int n, List<String> options) {
		String input;
		boolean stop = false;

		do {
			input = scanner.nextLine();
			for (int i = 0; i < n; i++) {
				if (input.equalsIgnoreCase(Integer.valueOf(i).toString())) {
					input = options.get(i);
					stop = true;
					break;
				}
			}
			if (!stop) {
				System.out.println("Invalid input. Please enter a valid number for option index.");
			}
		} while (!stop);

		return input;
	}

	public boolean testIsPlayerOneNull() {
		return player1 == null;
	}

	public boolean testIsPlayerTwoNull() {
		return player1 == null;
	}

	public boolean testArePlayersDifferent() {
		return player1 == player2;
	}

	public int testPlayerOneHealth() {
		return player1.getHealth();
	}

	public int testPlayerTwoHealth() {
		return player2.getHealth();
	}

	public int testPlayerOneDeckSize() {
		return player1.getDeck().size();
	}

	public int testPlayerTwoDeckSize() {
		return player2.getDeck().size();
	}

	public String simulateGameEndingPlayerOneNoHealth() {
		String result = "";
		player1.drawInitialCards();
		player2.drawInitialCards();

		// Game loop
		while (!getGameEnded()) {
			// Player 1's turn
			if (isPlayerWithoutOptionsToPlay(player1)) {
				result = "Player 1 has no cards in hand and deck. Player 2 wins!";
				gameEnded = true;
				break;
			}

			// Last turn, Player 2 has played Boost Attack Cards and Attack Card to reduce
			// Player 1's health to 0
			// Player 1 is attacked by Player 2 with damage greater than Player 1's health
			// Player 1 has no way to stop this attack, and takes the damage
			player1.takeDamage(player1.getHealth());

			// playTurn(player1, player2);

			if (!hasHealth(player1)) {
				result = "Player 1 has 0 health. Player 2 wins!";
				gameEnded = true;
				break;
			}

			// Player 2's turn
			if (isPlayerWithoutOptionsToPlay(player2)) {
				result = "Player 2 has no cards in hand and deck. Player 1 wins!";
				gameEnded = true;
				break;
			}

			// Player 2 plays Boost Attack Cards and Attack Card
			// playTurn(player2, player1);

			if (!hasHealth(player2)) {
				result = "Player 2 has 0 health. Player 1 wins!";
				gameEnded = true;
				break;
			}
		}
		return result;
	}

	public String simulateGameEndingPlayerTwoNoHealth() {
		String result = "";
		player1.drawInitialCards();
		player2.drawInitialCards();

		// Game loop
		while (!getGameEnded()) {
			// Player 1's turn
			if (isPlayerWithoutOptionsToPlay(player1)) {
				result = "Player 1 has no cards in hand and deck. Player 2 wins!";
				gameEnded = true;
				break;
			}

			// Player 1 plays Boost Attack Cards and Attack Card to reduce
			// Player 2's health to 0
			// playTurn(player1, player2);

			if (!hasHealth(player1)) {
				result = "Player 1 has 0 health. Player 2 wins!";
				gameEnded = true;
				break;
			}

			// Player 2's turn
			if (isPlayerWithoutOptionsToPlay(player2)) {
				result = "Player 2 has no cards in hand and deck. Player 1 wins!";
				gameEnded = true;
				break;
			}

			// Player 2 is attacked by Player 1 with damage greater than Player 2's health
			// Player 2 has no way to stop this attack, and takes the damage
			player2.takeDamage(player2.getHealth());

			// playTurn(player2, player1);

			if (!hasHealth(player2)) {
				result = "Player 2 has 0 health. Player 1 wins!";
				gameEnded = true;
				break;
			}
		}
		return result;
	}

	public String simulateGameEndingPlayerOneNoCards() {
		String result = "";
		player1.drawInitialCards();
		player2.drawInitialCards();

		// Game loop
		while (!getGameEnded()) {
			// Player 1's turn
			if (isPlayerWithoutOptionsToPlay(player1)) {
				result = "Player 1 has no cards in hand and deck. Player 2 wins!";
				gameEnded = true;
				break;
			}

			// Player 1 has no cards left in his deck
			// Player 1 has played his last card from his hand
			// Player 1 is unable to defeat his opponent
			// On his next turn Player 1 losses the game because
			// he has no cards left to play
			player1.getDeck().clear();
			player1.getHand().clear();
			// playTurn(player1, player2);

			if (!hasHealth(player1)) {
				result = "Player 1 has 0 health. Player 2 wins!";
				gameEnded = true;
				break;
			}

			// Player 2's turn
			if (isPlayerWithoutOptionsToPlay(player2)) {
				result = "Player 2 has no cards in hand and deck. Player 1 wins!";
				gameEnded = true;
				break;
			}

			// Player 2 plays his turn
			// playTurn(player2, player1);

			if (!hasHealth(player2)) {
				result = "Player 2 has 0 health. Player 1 wins!";
				gameEnded = true;
				break;
			}
		}
		return result;
	}

	public String simulateGameEndingPlayerTwoNoCards() {
		String result = "";
		player1.drawInitialCards();
		player2.drawInitialCards();

		// Game loop
		while (!getGameEnded()) {
			// Player 1's turn
			if (isPlayerWithoutOptionsToPlay(player1)) {
				result = "Player 1 has no cards in hand and deck. Player 2 wins!";
				gameEnded = true;
				break;
			}

			// Player 1 plays his turn
			// playTurn(player1, player2);

			if (!hasHealth(player1)) {
				result = "Player 1 has 0 health. Player 2 wins!";
				gameEnded = true;
				break;
			}

			// Player 2's turn
			if (isPlayerWithoutOptionsToPlay(player2)) {
				result = "Player 2 has no cards in hand and deck. Player 1 wins!";
				gameEnded = true;
				break;
			}

			// Player 2 has no cards left in his deck
			// Player 2 has played his last card from his hand
			// Player 2 is unable to defeat his opponent
			// On his next turn Player 2 losses the game because
			// he has no cards left to play
			player2.getDeck().clear();
			player2.getHand().clear();
			// playTurn(player2, player1);

			if (!hasHealth(player2)) {
				result = "Player 2 has 0 health. Player 1 wins!";
				gameEnded = true;
				break;
			}
		}
		return result;
	}

}
