package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class ManageAccounts {

	public static void manageAccounts(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Account> tempAccountList = null;
		String accountChoice = null;
		int accountServiceChoice;
		Account tempAccount = null;
		char manageAccountChoice = 'y';
		System.out.println("Enter customer code: ");
		String customerId = scanner.next();
		if (customerId.equalsIgnoreCase(customer.getCustomerCode())) {
			System.out.println(customer.getCustomerName() + " has the following accounts");
			tempAccountList = customer.getAccountList();
			for (Account account : tempAccountList) {
				System.out.println(account.getAccountNo() + ". " + account.getAccountType());
			}
			System.out.println("Enter the account number: ");
			accountChoice = scanner.next();
			for (Account account : tempAccountList) {
				if (account.getAccountNo().equalsIgnoreCase(accountChoice)) {
					tempAccount = account;
					break;
				}
			}
			do {
				System.out.println("1. Deposit\t2. Withdraw\t3. Display Balance");
				accountServiceChoice = scanner.nextInt();
				switch (accountServiceChoice) {
				case 1:
					depositInAccount(tempAccount);
					break;
				case 2:
					withdrawFromAccount(tempAccount);
					break;
				case 3:
					displayBalance(tempAccount);
					break;
				default:
					System.out.println("Invalid Choice!!");
				}
				System.out.println("Do you want to continue transactions in this account? ");
				manageAccountChoice = scanner.next().charAt(0);
			} while (manageAccountChoice == 'y');
		}
	}

	private static void displayBalance(Account account) {
		System.out.println("The account " + account.getAccountNo() + " has balance amount of ₹" + account.getBalance());
	}

	private static void depositInAccount(Account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount to be deposited: ");
		double deposit = scanner.nextDouble();
		if (account.getProduct() instanceof LoanAccount) {
			System.out.println("How do you want to deposit\n1.Cash Deposit \n2.Cheque Deposit");
			System.out.println("Enter your choice");
			int depositChoice = scanner.nextInt();
			if (depositChoice == 2) {
				deposit = (deposit - (deposit * 0.3));

			}
		}
		account.setBalance(account.getBalance() + deposit);
		System.out.println("Your current balance is: " + account.getBalance());

	}

	private static void withdrawFromAccount(Account account) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount to be deposited: ");
		double withdraw = scanner.nextDouble();
		Product accountProduct = account.getProduct();
		if (accountProduct instanceof SavingsMaxAccount) {
			SavingsMaxAccount savingsAccount = (SavingsMaxAccount) accountProduct;
			if ((account.getBalance() - withdraw) < savingsAccount.getMinimumBalance()) {
				System.out.println(
						"Sorry!!!!!!!!!!!!! A mininmum balance of Rs 1000 should be maintained in the account.");

			} else {
				account.setBalance(account.getBalance() - withdraw);
				System.out.println("Your current balance is: " + account.getBalance());
			}
		} else if (accountProduct instanceof LoanAccount) {
			System.out.println("Withdrawal from loan account is not possible");

		} else {
			if (withdraw > account.getBalance()) {
				System.out.println("Insufficient funds.");
			} else {
				account.setBalance(account.getBalance() - withdraw);
				System.out.println("Your current balance is: " + account.getBalance());
			}
		}
	}

	public static void displayCustomer(Customer customer) {
		System.out.println("*************************Customer-Account Details*************************");
		System.out.println("CustomerId	CustomerName		AccountType		Balance");
		ArrayList<Account> accounts = customer.getAccountList();
		for (Account account : accounts) {
			System.out.println(customer.getCustomerCode() + "	   " + customer.getCustomerName() + "		"
					+ account.getAccountType() + "		" + account.getBalance());
			System.out.println("Services Provided");
			ArrayList<Service> services = account.getProduct().getServiceList();
			for (Service service : services) {
				System.out.print(service.getServiceName() + ", ");
			}
			System.out.println();
		}

	}

}
