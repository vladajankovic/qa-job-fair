package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result results = JUnitCore.runClasses(
				TestCardClasses.class, 
				TestUtility.class, 
				TestPlayer.class);

		for (Failure fail : results.getFailures()) {
			System.out.println(fail.toString());
		}

		if (results.wasSuccessful()) {
			System.out.println("All tests passed!");
		}
	}

}
