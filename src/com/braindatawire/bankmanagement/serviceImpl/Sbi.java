package com.braindatawire.bankmanagement.serviceImpl;

import com.braindatawire.bankmanagement.model.Account;
import com.braindatawire.bankmanagement.service.Rbi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import DbConfigue.DbConfigue;

public class Sbi implements Rbi {
	Scanner sc = new Scanner(System.in);
	Account ac = null;
	Random r = new Random();

	public void createAccount() {
		ac = new Account();

		try {

			Connection con = DbConfigue.getConnection();
			long accNo = 10000000000L + (long) (r.nextDouble() * 90000000000L);
			System.out.println(accNo);
			ac.setAccNo(accNo);

			System.out.println("Enter Full Name  Name : ");
			String name = sc.next() + sc.nextLine();
			ac.setName(name);

			System.out.println("Enter 10 Digit Mobile no : ");
			String mobNo = sc.next();
			ac.setMobNo(mobNo);

			System.out.println("Enter Adhar No :");
			String adharNo = sc.next();
			ac.setAdharNo(adharNo);

			System.out.println("Enter PAN Number :");
			String panNo = sc.next();
			ac.setPanNo(panNo);

			System.out.println("Enter Email ID :");
			String emailId = sc.next();
			ac.setEmailId(emailId);

			System.out.println("Enter Gender : ");
			String gender = sc.next();
			ac.setGender(gender);

			System.out.println("Enter Initial Balance minimum 1000 :");
			double balance = sc.nextDouble();
			ac.setBalance(balance);
			// Step 3 Create Sql Query

			String sql = "insert into Account values(" + ac.getAccNo() + " , '" + ac.getName() + "' , " + ac.getMobNo()
					+ ",'" + ac.getAdharNo() + "','" + ac.getPanNo() + "','" + ac.getEmailId() + "','" + ac.getGender()
					+ "'," + ac.getAge() + ", " + ac.getBalance() + ")";

			// step 4: Create Statement (I) Object
			Statement stmt = con.createStatement();

			// step 5: Execute the SQL Query
			stmt.execute(sql);

			// step 6: Close the Resources
			con.close();

			System.out.println("Done...!");

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

//=============== View Details 	 ==================================================================
//===============                ===================================================================	
	public void displayAllDetails()

	{
		try {
			Connection con = DbConfigue.getConnection();

			// Step 3 Create Sql Query

			String fetch = "Select * from Account";

			// Step 4: Create Statement Object;

			Statement stmt = con.createStatement();

			// Step5: Execute Sql Query

			stmt.executeQuery(fetch);

			// step5: Execute the SQL Query
			ResultSet rs = stmt.executeQuery(fetch);
			while (rs.next()) {
				System.out.println("******************************");
				System.out.println("Account No           : " + rs.getLong(1));
				System.out.println("Account Holders Name : " + rs.getString(2));
				System.out.println("Mobile No            : " + rs.getLong(3));
				System.out.println("Adhar No             : " + rs.getString(4));
				System.out.println("PAN No               : " + rs.getString(5));
				System.out.println("Email ID             : " + rs.getString(6));
				System.out.println("Gender               : " + rs.getString(7));
				System.out.println("Age                  : " + rs.getInt(8));
				System.out.println("Available Balance    : " + rs.getDouble(9));
				
				
			}

			// step 6 : CLose the Resources
			con.close();
			System.out.println("Done...!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//========================= Deposit Amount ===================================================
//==========================               ==================================================	
	public void depositeMoney() {

		try {
			Connection con = DbConfigue.getConnection();

			// step3 Create Sql Query

			System.out.println("Enter Account No to Deposit amount :");
			long accNo = sc.nextLong();

			System.out.println("Enter amount to Deposit :");
			double amount = sc.nextDouble();

			String getBalance = "Select balance from account where accNo=" + accNo + "";

			// Step 4 :Create Statement object

			Statement stmt = con.createStatement();

			// Step 5 :Excute Sql Query

			// step5: Execute the SQL Query
			ResultSet rs = stmt.executeQuery(getBalance);

			while (rs.next()) {

				// System.out.println("Balance :" + rs.getDouble(9));

				double availBalance = rs.getDouble(1);

				double dpAmount = availBalance + amount;

				Statement stmt1 = con.createStatement();

				String upadte = "update account set balance= " + dpAmount + " where accNo= " + accNo + "";

				stmt1.executeUpdate(upadte);
				System.out.println("Deposit Done SuccessFully...");

			}

			// Step 6:Close Resource

			con.close();

			System.out.println("Done..");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

//============== Withdraw Amount ====================================================================
////============                  =========================================================		

	public void withdrawal() {

		try {
			Connection con = DbConfigue.getConnection();

			// step3 Create Sql Query

			System.out.println("Enter Account No to Withdrwa amount :");
			long accNo = sc.nextLong();

			System.out.println("Enter amount to Withdraw :");
			double amount = sc.nextDouble();

			String AvBalance = "Select balance from account where accNo=" + accNo + "";

			// Step 4 :Create Statement object

			Statement stmt = con.createStatement();

			// Step 5 :Excute Sql Query

			// step5: Execute the SQL Query
			ResultSet rs = stmt.executeQuery(AvBalance);

			while (rs.next()) {

				// System.out.println("Balance :" + rs.getDouble(9));

				double availBalance = rs.getDouble(1);

				if (availBalance > amount) {
					double wAmount = availBalance - amount;

					Statement stmt1 = con.createStatement();

					String upadte = "update account set balance= " + wAmount + " where accNo= " + accNo + "";

					stmt1.executeUpdate(upadte);
					System.out.println("Withdraw Done SuccessFully...");
				} else {
					System.out.println("Insufficient Balance....");

					System.out.println("Kindly Enter Amount less than or equal to available balance");
					System.out.println("Try Again with valid amount");
				}

			}

			// Step 6:Close Resource

			con.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
//============ get Balance =======================================================================================
//=======================================================================================================================

	public void balanceCheck() {

		try {
			Connection con = DbConfigue.getConnection();

			// step3 Create Sql Query

			System.out.println("Enter Account No to Check Available  amount :");
			long accNo = sc.nextLong();

			System.out.println("Account number :" + accNo);

			String AvBalance = "Select balance from account where accNo=" + accNo + "";

			// Step 4 :Create Statement object

			Statement stmt = con.createStatement();

			// Step 5 :Excute Sql Query

			// step5: Execute the SQL Query
			ResultSet rs = stmt.executeQuery(AvBalance);

			while (rs.next()) {

				System.out.println(" Available Balance : " + rs.getDouble(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
//======================= update Details =================================================
//==================================================================================================

	public void updateDetails() {
		try {
			boolean flag = true;

			System.out.println("Enter Account Number to Upadate Details:");
			long accNo = sc.nextLong();
			
			System.out.println("Press 0 to see options...");
			
			
			Connection con = DbConfigue.getConnection();
			while (flag) {
				
				int choice = sc.nextInt();
				switch (choice) {

				case 0:

					System.out.println("------ To Upadate Details select any option-------");
					System.out.println("Select 1. to update Name");
					System.out.println("Select 2. to update Mobile Number ");
					System.out.println("Select 3. to update Adhar No ");
					System.out.println("Select 4. to update Pan No");
					System.out.println("Select 5. to update Email Id");
					System.out.println("Select 6. to update Gender");
					System.out.println("Select 7. to update Age");
					break;
				case 1:
					// Step3 Create Sql Query
					
					System.out.println("Enter New Name to update : ");
					String name = sc.next()+sc.nextLine();

					String sql = " update account set name ='" + name + "' where accNo=" + accNo + "";
					// Create Statement object
					Statement stmt = con.createStatement();

					// Step 5: Execute Sql Query
					stmt.executeUpdate(sql);
			
					System.out.println("Name Upadte SuccessFully...!!!");

					// Step 6: Close resources

					con.close();
                      break;
				case 2:
					// Step3 Create Sql Query
				
					System.out.println("Enter New Mobile number to update : ");
					long  mobNo = sc.nextLong();
					
					String updateMobsql  = " update Account set mobNo =" + mobNo + " where accNo=" + accNo + "";
					// Create Statement object
					Statement stmt1 = con.createStatement();

					// Step 5: Execute Sql Query

					stmt1.executeUpdate(updateMobsql);
					System.out.println("Mobile Number  Upadte Successfully...!!!");

					// Step 6: Close resources

					con.close();
                      break;
				case 3:
					
					System.out.println("Enter New Adhar number to update : ");
					String aadharNo =sc.next()+sc.nextLine();

					String updateAdharsql  = " update Account set adharNo =" + aadharNo + " where accNo=" + accNo + "";
					// Create Statement object
					Statement stmt2 = con.createStatement();

					// Step 5: Execute Sql Query

					stmt2.executeUpdate(updateAdharsql);
					System.out.println(" Adhar Number Upadated SuccessFully...");

					// Step 6: Close resources

					con.close();
                      break;
				case 4:
					System.out.println("Enter New Pan number to update : ");
					String panNo =sc.next()+sc.nextLine();

					String updatePansql  = " update Account set panNo ='" + panNo + "' where accNo=" + accNo + "";
					// Create Statement object
					Statement stmt3 = con.createStatement();

					// Step 5: Execute Sql Query

					stmt3.executeUpdate(updatePansql);
					System.out.println(" PAN Number Upadated SuccessFully...");

					// Step 6: Close resources

					con.close();
                      break;
				case 5:
					System.out.println("Enter New Email Id  to update : ");
					String emailId =sc.next()+sc.nextLine();

					String updateEmailsql  = " update Account set emailId ='" +emailId + "' where accNo=" + accNo + "";
					// Create Statement object
					Statement stmt4 = con.createStatement();

					// Step 5: Execute Sql Qu

					stmt4.executeUpdate(updateEmailsql);
					System.out.println(" Email id  Upadated Successfully...");

					// Step 6: Close resources

					con.close();
                      break;
				case 6:
					
					System.out.println("Enter gender  to update : ");
					String gender =sc.next()+sc.nextLine();

					String updateGendersql  = " update Account set gender='" +gender+ "' where accNo=" + accNo + "";
					// Create Statement object
					Statement stmt5 = con.createStatement();

					// Step 5: Execute Sql Qu

					stmt5.executeUpdate(updateGendersql);
					System.out.println("Gender Upadated Successfully...");

					// Step 6: Close resources

					con.close();
                      break;
                      
				case 7:
					System.out.println("Enter Age  to update : ");
					int age =sc.nextInt();

					String updateAgesql  = " update Account set age =" +age+ " where accNo=" + accNo + "";
					// Create Statement object
					Statement stmt6 = con.createStatement();

					// Step 5: Execute Sql Qu

					stmt6.executeUpdate(updateAgesql);
					System.out.println("Age Upadated Successfully...");

					// Step 6: Close resources

					con.close();
                      break;
				case 8:
					
					flag = false;
					System.out.println("Thank you..........!!");
					break;
				default :
					 System.out.println(" Enter Valid Option");
					
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	


}
