package Acc.Beans;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	private double accnum;
	private double balance=0;
	private Customer customer;
	public BankAccount(Customer cust,String accnum) {
		this.customer=cust;
		this.accnum=Double.parseDouble(accnum);
	}
	public Customer getCustomer(){
		return customer;

	}
	public void setCustomer(Customer cust){
		this.customer=cust;

	}

	private ReentrantLock implicitLock= new ReentrantLock();
	public void credit(double amount){
		balance = balance + amount; 
	}
	public void debit(double amount){ 
		balance = balance - amount; 
	}
	public double getBalance(){
		return balance;
	}

	public ReentrantLock getImplicitLock() {
		return implicitLock;
	}

	public void setBalance(Integer blance) {
		this.balance = blance;
	}


	public void credit(Integer amount)
	{
		balance = balance +amount;
		System.out.println(Thread.currentThread().getName() + " :: " + this.customer.getName() + " - "+ amount + " Credit success" );

	}

	public boolean debit(Integer amount)
	{
		if(amount > balance)
		{
			System.out.println(Thread.currentThread().getName() + " :: " +customer.getName() + " - "+ amount + " low bal" );
			return false;
		}
		balance = balance -amount;
		System.out.println(Thread.currentThread().getName() + " :: " +customer.getName() + " - "+ amount + " Debit success" );
		return true;
	}




}
