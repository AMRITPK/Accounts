package operation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import Acc.Beans.BankAccount;
import Acc.Beans.Customer;
public class AccountTransfer {
	private ReentrantLock lock= new ReentrantLock();
	public void transfer(BankAccount from,BankAccount to,Integer amount)
	{
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
				System.out.println(Thread.currentThread().getName()+ " - " + from.getCustomer().getName() + " - balance is " + from.getBalance());
				System.out.println(Thread.currentThread().getName()+ " - " + to.getCustomer().getName()  + " - balance is " + to.getBalance());
				transfer = true;
			}
			else
			{
				System.out.println(Thread.currentThread().getName() + " says fail to accuire lock");
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
	public static void main(String[] args) {


		ExecutorService service = Executors.newFixedThreadPool(3);
		final BankAccount from = new BankAccount(new Customer(),"10001");
		from.setBalance(20000);
		from.getCustomer().setName("Amrit");
		final BankAccount to = new BankAccount(new Customer(),"10002");
		to.setCustomer(new Customer());
		to.getCustomer().setName("Kumar");
		final AccountTransfer transfer = new AccountTransfer();
		Runnable a = new Runnable(){
			public void run()
			{
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



				transfer.transfer(from, to, 200);
				System.out.println(Thread.currentThread().getName() +" says :: Transfer successfull from "+from.getCustomer().getName()+" to "
						+to.getCustomer().getName());
			}
		};

		Runnable b = new Runnable(){

			public void run()
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				transfer.transfer(to, from, 1000);
				System.out.println(Thread.currentThread().getName() +" says :: Transfer successfull from "+to.getCustomer().getName()+" to "
						+from.getCustomer().getName());
			}
		};
		for(int i=0;i<4;i++)
		{
			service.submit(a);
			service.submit(b);
		}
	}
}
