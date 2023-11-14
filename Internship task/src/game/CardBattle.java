package game;

import java.util.List;

import cards.Card;
import player.Player;
import utility.Utility;

/**
 * This class is created for making custom Card Battle games.
 * <p>
 * The Standard game (1v1 with 20 HP and 25 card deck) is implemented
 * in the static method <i>startStandardGame()</i>.
 * <p>
 * Other custom games can be implemented using static methods like:
 * <br>
 * <pre>
 * public static void customGame() { 
 *     // Create cards for player 1's deck
 *     List<Card> player1Deck = &ltcreate custom player deck&gt
 *       
 *     // Create cards for player 2's deck
 *     List<Card> player2Deck = &ltcreate custom player deck&gt
 *       
 *     Player player1 = new Player(&ltcustom health point value&gt, player1Deck);
 *     Player player2 = new Player(&ltcustom health point value&gt, player2Deck);
 *       
 *     // Create a game instance
 *     Game game = new Game(player1, player2);
 *     game.startGame();
 * }
 * </pre> 
 */

public class CardBattle {
	
	public static void startStandardGame() {
		// Create cards for player 1's deck
		List<Card> player1Deck = Utility.generateCards();

		// Create cards for player 2's deck
		List<Card> player2Deck = Utility.generateCards();

		Player player1 = new Player(20, player1Deck);
		Player player2 = new Player(20, player2Deck);

		// Create a game instance
		Game game = new Game(player1, player2);
		game.startGame();
	}
	
}
