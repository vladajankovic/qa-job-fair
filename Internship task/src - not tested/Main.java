import java.util.ArrayList;
import java.util.List;

import cards.*;
import game.Game;
import player.Player;


public class Main {


public static List<Card> generateCards() {
    List<Card> cards = new ArrayList<>();

    // Generate AttackCards (from 3 to 7)
    for (int i = 3; i <= 7; i++) {
        cards.add(new AttackCard(i));
        cards.add(new AttackCard(i));
    }

    // Generate 10 ProtectCards
    for (int i = 0; i < 5; i++) {
        cards.add(new ProtectCard()); // Using a higher ID range to distinguish from AttackCards
    }
    // Generate 10 BoostAttackCards
    for (int i = 0; i < 10; i++) {
        cards.add(new BoostAttackCard()); // Using a higher ID range to distinguish from other cards
    }

    return cards;
}

public static void main(String[] args) {
        // Create cards for player 1's deck
        List<Card> player1Deck = generateCards();
   
        // Create cards for player 2's deck
       List<Card> player2Deck = generateCards();

        Player player1 = new Player(20,player1Deck);
        Player player2 = new Player(20,player2Deck);
        // Create a game instance

        
        Game game = new Game(player1, player2);
        game.startGame();

    }
}