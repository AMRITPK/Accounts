package operation;

import Acc.Beans.*;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MoneyTransfer {
	private ReentrantReadWriteLock lock= new ReentrantReadWriteLock();

	public double getBalance(BankAccount to)	{

		boolean gotLock = false;
		try
		{
			if(lock.readLock().tryLock())
			{
				System.out.println(Thread.currentThread().getName() + " says accuire lock to read bal");
				return to.getBalance();

			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(gotLock)
			{
				lock.writeLock().unlock();
			}
		}
		return -1;

	}

	public void addMoney(BankAccount to,Integer amount)	{

		boolean transfer = false;
		try
		{
			if(lock.writeLock().tryLock())
			{
				System.out.println(Thread.currentThread().getName() + " says accuire lock to add money"+amount.toString());
				to.credit(amount);

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
				lock.writeLock().unlock();
			}
		}

	}
	public void transfer(BankAccount from,BankAccount to,Integer amount)	{

		boolean transfer = false;
		try
		{
			if(lock.writeLock().tryLock())
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
				lock.writeLock().unlock();
			}
		}

	}
}