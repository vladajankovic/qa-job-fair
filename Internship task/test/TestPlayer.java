package test;

import org.junit.Test;

import cards.AttackCard;
import cards.BoostAttackCard;
import cards.Card;
import cards.ProtectCard;
import player.Player;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class TestPlayer {

    public static List<Card> generateCards() {
    List<Card> cards = new ArrayList<>();

    // Generate AttackCards (from 3 to 7)
    for (int i = 3; i <= 7; i++) {
        cards.add(new AttackCard(i));
        cards.add(new AttackCard(i));
    }

    // Generate 10 ProtectCards
    for (int i = 0; i < 10; i++) {
        cards.add(new ProtectCard()); // Using a higher ID range to distinguish from AttackCards
    }
    // Generate 10 BoostAttackCards
    for (int i = 0; i < 10; i++) {
        cards.add(new BoostAttackCard()); // Using a higher ID range to distinguish from other cards
    }

    return cards;
}

@Test
public void testTakeDamage(){
    Player player = new Player(10, generateCards());
    player.takeDamage(1);
    assertEquals(9, player.getHealth());
}
    
}
