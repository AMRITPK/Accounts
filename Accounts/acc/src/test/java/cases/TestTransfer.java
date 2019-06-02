package cases;

import org.junit.Test;

import junit.framework.TestCase;
import operation.Bank;
import util.Factory;

import static org.junit.Assert.assertEquals;
public class TestTransfer extends TestCase  {
   @Test
   public void testAdd() {
      //String str= "Junit is working fine";
      
      Bank bank=Factory.getBank("SBI");
		 
	// System.out.println(accnum+"  "+amount);
	
	//Bank.createCustomer(json.get("name").toString());
      String accnum = bank.createAccount("Amrit1");
	 String res=bank.addMoney("1000", Double.parseDouble("100.0"));
	 String accnum2 = bank.createAccount("Amrit3");
	 
	 
	 String status=bank.transfer(accnum, accnum2, "10");
	 assertTrue(status=="success");
	 
	 String bal=bank.getBAblance(accnum2);
	 assertTrue(Double.parseDouble(bal)>0);
	 
	 

   }

}