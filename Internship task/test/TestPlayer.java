package test;

import org.junit.Test;
import player.Player;
import utility.Utility;
import static org.junit.Assert.assertEquals;

public class TestPlayer {

@Test
public void testTakeDamage(){
    new Utility();
    Player player = new Player(10, Utility.generateCards());
    player.takeDamage(1);
    assertEquals(9, player.getHealth());
}
    
}
