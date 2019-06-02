package util;
import java.util.concurrent.*;

import Acc.Beans.BankAccount; 
public class DS {
	private ConcurrentHashMap map;
	public DS() {
		
	}
	
	public synchronized  ConcurrentHashMap<String,BankAccount>  getMap(){
		synchronized(this) {
			if(this.map ==null)
				this.map = new ConcurrentHashMap<String,BankAccount>(); 
		}
		return this.map;
	}
}
