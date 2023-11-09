package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result results = JUnitCore.runClasses(TestCardClasses.class);
		
		for(Failure fail: results.getFailures())
		{
			System.out.println(fail.toString());
		}
		
		System.out.println(results.wasSuccessful());

	}

}
