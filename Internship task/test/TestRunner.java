package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result results = JUnitCore.runClasses(
				TestCardClasses.class, 
				TestUtility.class, 
				TestPlayer.class,
				TestGame.class);

		for (Failure fail : results.getFailures()) {
			System.err.println(fail.toString());
		}
		
		System.out.println("\r\nTests PASSED: " + 
				(results.getRunCount() - results.getFailureCount()) + 
				"/" + results.getRunCount());
		System.out.println("Tests FAILED: " + results.getFailureCount() +
				"/" + results.getRunCount() + "\r\n");

		if (results.wasSuccessful()) {
			System.out.println("All tests passed!");
		}
	}

}
