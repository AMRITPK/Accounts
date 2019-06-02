package Acc.Beans;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	  
	  private double balance=0;
	  private Customer customer;
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

	}
