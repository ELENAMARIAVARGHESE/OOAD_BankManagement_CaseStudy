package com.ilp.entity;

public class Account {
	private String accountNo;
	private String accountType;
	private double balance;
	private Product product;

	public Account(String accountNo, String accountType, double balance, Product product) {
		this.setAccountNo(accountNo);
		this.setAccountType(accountType);
		this.setBalance(balance);
		this.setProduct(product);
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountType=" + accountType + ", balance=" + balance
				+ ", product=" + product + "]";
	}

}
