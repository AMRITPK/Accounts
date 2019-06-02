import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Acc.Beans.BankAccount;
import junit.framework.TestCase;

public class Test extends TestCase {
	  
	public static void main (String args[]) {
	  BankAccount bankAccount = new BankAccount();
	  ExecutorService executorService = Executors.newFixedThreadPool(10);
	  
	  for( int i=0;i<100;++i) {
		  bankAccount.debit(100); 
		   bankAccount.credit(100); 
	  }

	  executorService.shutdown(); 
	  System.out.println("Final Balance: " + bankAccount.getBalance()); 
}
}
