package test;

import org.junit.Test;

import game.Game;
import player.Player;
import utility.Utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestGame {
    
	@Test
	public void testGameNoPlayers()
	{
		Game testGame = new Game(null, null);
		
		assertNotNull("Game: Constructor is null!", testGame);
		
		assertFalse("Game: If player is null, a new player must be created!", testGame.testIsPlayerOneNull());
		
		assertFalse("Game: If player is null, a new player must be created!", testGame.testIsPlayerTwoNull());
	}
	
	@Test
	public void testGameOnePlayer()
	{
		Player player = new Player(20, Utility.generateCards());
		Game testGame = new Game(player, player);
		
		assertFalse("Game: Player can't play against himself!", testGame.testArePlayersDifferent());
	}
	
	@Test
	public void testGameTwoPlayers()
	{
		Player player1 = new Player(20, Utility.generateCards());
		Player player2 = new Player(20, Utility.generateCards());
		Game testGame = new Game(player1, player2);
		
		assertFalse("Game: Players must be different!", testGame.testArePlayersDifferent());
	}
	
	@Test
	public void testGameInitGameEnded()
	{
		Player player1 = new Player(20, Utility.generateCards());
		Player player2 = new Player(20, Utility.generateCards());
		Game testGame = new Game(player1, player2);
		
		assertFalse("Game: Initial value of attribute gameEnded must be false!", testGame.getGameEnded());
	}
	
	@Test
	public void testGameEndingByHealth1()
	{
		Player player1 = new Player(20, Utility.generateCards());
		Player player2 = new Player(20, Utility.generateCards());
		Game testGame = new Game(player1, player2);
		
		String result = testGame.simulateGameEndingPlayerOneNoHealth();
		
		assertEquals("Game: Player 1's health must be 0!", 0, player1.getHealth());
		assertTrue("Game: Player 1's health is 0, gameEnded must be true!", testGame.getGameEnded());
		assertTrue("Game: Player 2 must be the winner!", result.equals("Player 1 has 0 health. Player 2 wins!"));
	}
	
	@Test
	public void testGameEndingByHealth2()
	{
		Player player1 = new Player(20, Utility.generateCards());
		Player player2 = new Player(20, Utility.generateCards());
		Game testGame = new Game(player1, player2);
		
		String result = testGame.simulateGameEndingPlayerTwoNoHealth();
		
		assertEquals("Game: Player 2's health must be 0!", 0, player2.getHealth());
		assertTrue("Game: Player 2's health is 0, gameEnded must be true!", testGame.getGameEnded());
		assertTrue("Game: Player 1 must be the winner!", result.equals("Player 2 has 0 health. Player 1 wins!"));
	}
	
	@Test
	public void testGameEndingByNoCards1()
	{
		Player player1 = new Player(20, Utility.generateCards());
		Player player2 = new Player(20, Utility.generateCards());
		Game testGame = new Game(player1, player2);
		
		String result = testGame.simulateGameEndingPlayerOneNoCards();
		
		assertTrue("Game: Player 1 must have 0 cards in both hand and deck!", player1.getDeck().isEmpty() && player1.getHand().isEmpty());
		assertTrue("Game: Player 1 has no cards left, gameEnded must be true!", testGame.getGameEnded());
		assertTrue("Game: Player 2 must be the winner!", result.equals("Player 1 has no cards in hand and deck. Player 2 wins!"));
	}
	
	@Test
	public void testGameEndingByNoCards2()
	{
		Player player1 = new Player(20, Utility.generateCards());
		Player player2 = new Player(20, Utility.generateCards());
		Game testGame = new Game(player1, player2);
		
		String result = testGame.simulateGameEndingPlayerTwoNoCards();
		
		assertTrue("Game: Player 2 must have 0 cards in both hand and deck!", player2.getDeck().isEmpty() && player2.getHand().isEmpty());
		assertTrue("Game: Player 2 has no cards left, gameEnded must be true!", testGame.getGameEnded());
		assertTrue("Game: Player 1 must be the winner!", result.equals("Player 2 has no cards in hand and deck. Player 1 wins!"));
	}
	
	public static void main(String[] args) {
		
	}
}
