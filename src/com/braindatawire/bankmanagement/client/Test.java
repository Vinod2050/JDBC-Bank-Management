package com.braindatawire.bankmanagement.client;



import com.braindatawire.bankmanagement.service.*;
import com.braindatawire.bankmanagement.serviceImpl.Sbi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Rbi bank = new Sbi();

		Scanner sc = new Scanner(System.in);

		try {
			boolean flag = true;
			System.out.println("Press 0 to show Available Options...");
			while (flag) {

				int choice = sc.nextInt();

				switch (choice) {
				case 0:
					System.out.println("-----------Bank Management System ------------");
					System.out.println();
					System.out.println(" Select Your Choice.........!! ");
					System.out.println("Press 1 for Account Creation : ");
					System.out.println("Press 2 for  View All Account Details : ");
					System.out.println("Press 3 for Deposit Amount : ");
      				System.out.println("Press 4 for Withdraw Amount : ");
     				System.out.println("Press 5 for Check balance : ");
     				System.out.println("Press 6 for Update Details : ");
    				System.out.println("Press 7 for Exit Appication : ");
     				System.out.println();
    				System.out.println("---------------Thank You----------------------------");
     				System.out.println();


					break;
				case 1:
					bank.createAccount();
					break;
				case 2:
					bank.displayAllDetails();
					break;
				case 3:
					   bank.depositeMoney();
					   break;
					
				case 4:
					   bank.withdrawal();
					   break;
				case 5:
					   bank.balanceCheck();
					   break;
				case 6:	
					   bank.updateDetails();
					   break;
				case 7:
					
					
					flag = false;
					System.out.println("Thank you..........!!");
					break;
					
				default:
					System.out.println("Enter Valid Choice:");
                   
				}
				

			}

		}

		catch (InputMismatchException e) {

			System.out.println("kindly Presss number only...!!");
			System.out.println("Press 0 to show Available Options...");

		}
		
		
System.out.println("the end");

System.out.println("----------------");

System.out.println("-------Thank you ------------------");
		

	}
}
