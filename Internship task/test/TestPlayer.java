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

    public static List<Card> generateCardsTest() {
    List<Card> cards = new ArrayList<>();

    for (int i = 3; i <= 7; i++) {
        cards.add(new AttackCard(i));
        cards.add(new AttackCard(i));
    }

    for (int i = 0; i < 10; i++) {
        cards.add(new ProtectCard());
    }
    for (int i = 0; i < 10; i++) {
        cards.add(new BoostAttackCard());
    }

    return cards;
}

@Test
public void testTakeDamage(){
    Player player = new Player(10, generateCardsTest());
    player.takeDamage(1);
    assertEquals(9, player.getHealth());
}
    
}
