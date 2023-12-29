package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class CustomerService {
	public static ArrayList<Service> createServiceList() {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> services = new ArrayList<Service>();
		char serviceChoice = 'y';
		Service service = null;
		do {
			System.out.println("****Available Services****");
			System.out.println(
					"SC001. Cash Deposit \nSC002. ATMWithdrawal \nSC003. Online Banking \nSC004. Mobile Banking \nSC005. Cheque Deposit");
			System.out.println("Enter the service code : ");
			String serviceCode = scanner.next();
			scanner.nextLine();
			System.out.println("Enter the service name : ");
			String serviceName = scanner.nextLine();
			System.out.println("Enter the service rate : ");
			double serviceRate = scanner.nextDouble();
			scanner.nextLine();

			service = new Service(serviceCode, serviceName, serviceRate);
			services.add(service);
			System.out.println("Do you want to continue ? (y/n)");
			serviceChoice = scanner.next().charAt(0);
		} while (serviceChoice == 'y');

		return services;
	}

	public static ArrayList<Product> createProduct(ArrayList<Service> services) {
		char productMenuChoice = 'y';

		Scanner scanner = new Scanner(System.in);

		ArrayList<Product> tempProductList = new ArrayList<Product>();

		char continueChoice = 'y';
		do {
			ArrayList<Service> tempServiceList = new ArrayList<Service>();

			System.out.println("Enter the product code : ");
			String productCode = scanner.next();
			System.out.println("Which type of account do you want ? ");
			System.out.println("Enter the name of the product : ");
			String productName = scanner.next();
			for (Service service : services) {
				System.out.println("Do you want to add " + service.getServiceName() + " to product (y/n)?");
				char addServiceChoice = scanner.next().charAt(0);
				if (addServiceChoice == 'y') {
					tempServiceList.add(service);
				}
			}
			switch (productName.toLowerCase()) {
			case "savingsmaxaccount":
				tempProductList.add(new SavingsMaxAccount(productCode, productName, tempServiceList, 1000));
				break;
			case "currentaccount":
				tempProductList.add(new CurrentAccount(productCode, productName, tempServiceList));
				break;
			case "loanaccount":
				tempProductList.add(new LoanAccount(productCode, productName, tempServiceList, 0.3));
				break;
			default:
				break;

			}
			System.out.println("Do you want to create more products (y/n)?");
			productMenuChoice = scanner.next().charAt(0);
		} while (productMenuChoice == 'y');

		return tempProductList;
	}

	public static Account createAccount(ArrayList<Product> productList) {
		Scanner scanner = new Scanner(System.in);
		int accountChoice, i = 1;
		System.out.println("***********Accounts Available***********\r\n");
		for (Product product : productList) {
			System.out.println(i + ". " + product.getProductName());
			i++;
		}

		System.out.print("Enter your choice: ");
		accountChoice = scanner.nextInt();

		System.out.print("Enter the account number: ");
		String accountNo = scanner.next();

		String accountName = productList.get(accountChoice - 1).getProductName();

		System.out.print("Enter the account balance: ");
		double balanceAmount = scanner.nextDouble();

		if (productList.get(accountChoice - 1) instanceof SavingsMaxAccount) {
			SavingsMaxAccount accountType = (SavingsMaxAccount) productList.get(accountChoice - 1);
			while (balanceAmount < accountType.getMinimumBalance()) {
				System.out.println("Balance should be a minimum of" + accountType.getMinimumBalance());
				System.out.print("Enter the account balance: ");
				balanceAmount = scanner.nextDouble();

			}
		}
		Account account = new Account(accountNo, accountName, balanceAmount, productList.get(accountChoice - 1));
		return account;

	}

	public static Customer createCustomer(Account account) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the customer code: ");
		String customerCode = scanner.next();

		System.out.print("Enter the customer name: ");
		String customerName = scanner.next();

		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList.add(account);

		return (new Customer(customerCode, customerName, accountList));

	}

}
