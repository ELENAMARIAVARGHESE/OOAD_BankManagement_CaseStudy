package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.CustomerService;
import com.ilp.service.ManageAccounts;

public class BankingUtility {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> services = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		Customer customer = null;
		Account account = null;

		char mainChoice = 'y';
		do {
			System.out.println("********Welcome To Bank*********");
			System.out.println("1. Create Service");
			System.out.println("2. Create Product");
			System.out.println("3. Create Customer");
			System.out.println("4. Manage Accounts");
			System.out.println("5. Display Customer");
			System.out.println("6. Exit");
			System.out.println("Enter your choice ");
			int mainMenuChoice = scanner.nextInt();
			switch (mainMenuChoice) {
			case 1:
				services.addAll(CustomerService.createServiceList());
				break;
			case 2:
				productList.addAll(CustomerService.createProduct(services));
				break;
			case 3:
				if (customer == null) {
					account = CustomerService.createAccount(productList);
					customer = CustomerService.createCustomer(account);
				} else {
					account = CustomerService.createAccount(productList);
					ArrayList<Account> tempAccountList = customer.getAccountList();
					tempAccountList.add(account);
					customer.setAccountList(tempAccountList);
				}
				break;
			case 4:
				if(customer!=null) {
				ManageAccounts.manageAccounts(customer);
				}
				else {
					System.out.println("Create a customer");
				}
				break;
			case 5:
				if(customer!=null) {
				ManageAccounts.displayCustomer(customer);
				}
				else {
					System.out.println("Create a customer");
				}
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid choice");
				break;
			}
			System.out.println("Do you want to continue ? (y/n)");
			mainChoice = scanner.next().charAt(0);

		} while (mainChoice == 'y');

	}

}
