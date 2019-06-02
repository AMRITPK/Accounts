package cases;

import org.junit.Test;

import junit.framework.TestCase;
import operation.Bank;
import util.Factory;

import static org.junit.Assert.assertEquals;
public class TestAcc extends TestCase  {
   @Test
   public void testAdd() {
      //String str= "Junit is working fine";
      
      Bank bank=Factory.getBank("SBI");
	 String accnum = bank.createAccount("Amrit");
	 assertEquals("1000",accnum);
   }

}