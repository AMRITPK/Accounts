package runner;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import cases.TestAcc;
import cases.TestAddMoney;
import cases.TestGetBal;
import cases.TestTransfer;

public class Runner1 extends Runner  {
	   public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(TestAcc.class,TestAddMoney.class,TestGetBal.class,TestTransfer.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	         //System.out.println("im here");
	      }
	      System.out.println(result.wasSuccessful());
	   }

	@Override
	public Description getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run(RunNotifier arg0) {
	
	}
}