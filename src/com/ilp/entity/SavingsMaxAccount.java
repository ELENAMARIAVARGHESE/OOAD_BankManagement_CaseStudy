package com.ilp.entity;

import java.util.ArrayList;

public class SavingsMaxAccount extends Product {
	private double minimumBalance;

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> serviceList,
			double minimumBalance) {
		super(productCode, productName, serviceList);
		this.setMinimumBalance(1000);
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	@Override
	public String toString() {
		return "SavingsMaxAccount [minimumBalance=" + minimumBalance + "]";
	}

}
