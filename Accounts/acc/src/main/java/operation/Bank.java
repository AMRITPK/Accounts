package operation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import Acc.Beans.BankAccount;
import Acc.Beans.Customer;
import util.UniqueNumbers;
import util.DS;

public class Bank {


	private static DS ds = new DS();


	//private ReentrantLock lock= new ReentrantLock();

	public static Customer createCustomer(String s) {
		// TODO Auto-generated method stub
		return new Customer(s, "");

	}

	public String createAccount(String string) {
		// TODO Auto-generated method stub
		Customer cust = createCustomer(string);
		String accnum = UniqueNumbers.getNext();
		BankAccount acc = new BankAccount(cust, accnum);
		ConcurrentHashMap < String, BankAccount > map = this.ds.getMap();
		map.put(accnum + "", acc);
		System.out.println(accnum);
		return accnum + "";


	}
	public String getCurrentTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}

	public String transfer(String fromString, String toString, String amountString) {
		double amount = Double.parseDouble(amountString);
		BankAccount from = this.ds.getMap().get(fromString);
		BankAccount to = this.ds.getMap().get(toString);
		String status = "";
		if (from != null && to != null) {

			ReentrantLock lockfrom = from.getImplicitLock();
			ReentrantLock lockto = to.getImplicitLock();



			try {
				System.out.println(Thread.currentThread().getName() + " before try" + getCurrentTimeStamp());
				if (lockfrom.tryLock(2, TimeUnit.SECONDS) && lockto.tryLock(2, TimeUnit.SECONDS)) {
					System.out.println(Thread.currentThread().getName() + " says accuire lock " + getCurrentTimeStamp());

					if (from.getBalance() > amount) {
						from.debit(amount);

						to.credit(amount);
						TimeUnit.SECONDS.sleep(1);
						System.out.println(Thread.currentThread().getName() + " - " + from.getCustomer().getName() + " - balance is " + from.getBalance());
						System.out.println(Thread.currentThread().getName() + " - " + to.getCustomer().getName() + " - balance is " + to.getBalance());

						status = "success";
					} else {
						status = "No Balance";
					}
				} else {
					System.out.println(Thread.currentThread().getName() + " says fail to accuire lock" + getCurrentTimeStamp());
					status = "try later";
					//transfer(fromString,toString,amountString);//try again
				}
			} catch (Exception ex) {
				if (lockfrom.isHeldByCurrentThread())
					lockfrom.unlock();
				if (lockto.isHeldByCurrentThread())
					lockto.unlock();
				ex.printStackTrace();
				status = "failure";
			} finally {

				if (lockfrom.isHeldByCurrentThread())
					lockfrom.unlock();
				if (lockto.isHeldByCurrentThread())
					lockto.unlock();

			}

		} else {
			System.out.println("not able to find account");
			status = "invalid account/s";
		}
		return status;
	}
	public String addMoney(String accnum, double amount) {
		// TODO Auto-generated method stub


		BankAccount acc = this.ds.getMap().get(accnum);
		ReentrantLock lockfrom = null;
		String status = "";
		if (acc != null) {

			System.out.println("balancenow=" + acc.getBalance());
			try {
				System.out.println(Thread.currentThread().getName() + " before try" + getCurrentTimeStamp());
				lockfrom = acc.getImplicitLock();
				if (lockfrom.tryLock(2, TimeUnit.SECONDS)) {
					System.out.println(Thread.currentThread().getName() + " says accuire lock " + getCurrentTimeStamp());
					acc.credit(amount);
					System.out.println("updated balancenow=" + acc.getBalance());
					status = acc.getBalance() + "";
				}
			} catch (Exception ex) {
				if (lockfrom.isHeldByCurrentThread())
					lockfrom.unlock();
				ex.printStackTrace();
				status = "failure";
			} finally {

				if (lockfrom.isHeldByCurrentThread())
					lockfrom.unlock();


			}
		} else {
			System.out.println("not able to find");
			status = "invalid account";
		}



		return status;


	}
	public String withdrawMoney(String accnum, double amount) {
		// TODO Auto-generated method stub



		BankAccount acc = this.ds.getMap().get(accnum);
		ReentrantLock lockfrom = null;
		String status = "";
		if (acc != null) {
			lockfrom = acc.getImplicitLock();
			System.out.println("balancenow=" + acc.getBalance());
			try {

				System.out.println("balancenow=" + acc.getBalance());
				if (acc.getBalance() > amount) {
					acc.debit(amount);
					System.out.println("updated balancenow=" + acc.getBalance());
					status = acc.getBalance() + "";
				} else {
					status = "lowBalance";
				}
			} catch (Exception ex) {
				if (lockfrom.isHeldByCurrentThread())
					lockfrom.unlock();
				ex.printStackTrace();
				status = "failure";
			} finally {

				if (lockfrom.isHeldByCurrentThread())
					lockfrom.unlock();


			}
		} else {
			System.out.println("not able to find");
			status = "invalid account";
		}



		return status;


	}
	public String getBAblance(String accnum) {
		// TODO Auto-generated method stub


		BankAccount acc = this.ds.getMap().get(accnum);
		if (acc != null) {



			double bal = acc.getBalance();
			if (bal != -1) {

				System.out.println("updated balancenow=" + acc.getBalance());
				return bal + "";
			} else {
				return "lowBalance";
			}

		} else {
			System.out.println("not able to find");
		}

		return "error";


	}

}