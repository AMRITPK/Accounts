package util;

import operation.Bank;

public class Factory {
	private static Bank bank=new Bank();


	
	private Factory() {
		
	}

	public static Bank  getBank(String bankName) {
	
		return bank;
		
	}

}
