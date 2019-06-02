package operation;

import Acc.Beans.*;
import java.util.concurrent.locks.ReentrantLock;

public class MoneyTransfer {
	private ReentrantLock lock= new ReentrantLock();
	
	

public void transfer(BankAccount from,BankAccount to,Integer amount)	{

boolean transfer = false;
	try
	{
	if(lock.tryLock())
	{
	System.out.println(Thread.currentThread().getName() + " says accuire lock");
	boolean flag = from.debit(amount);
	if(flag)
	{
	to.credit(amount);
	}
	System.out.println(Thread.currentThread().getName()+ " ::   says :: now balance is " + from.getBalance());
	System.out.println(Thread.currentThread().getName()+ " ::  says :: now balance is " + to.getBalance());
	transfer = true;
	}
	else
	{
	System.out.println(Thread.currentThread().getName() + " says fail to accuire both lock Try again");
	transfer(from,to,amount);//try again
	}
	}
	catch(Exception ex)
	{
	ex.printStackTrace();
	}
	finally
	{
	if(transfer)
	{
	lock.unlock();
	}
}

}
}