//Creating package ATM to call any class in it from other files.
package ATM;

//java.io for file handling.
import java.io.*;
import java.util.*;

//All the validity check methods are inherited and used.
//All the Exceptions are thrown by throw keyword.
public class AccCreate extends Check{
	//declaring Scanner as a field to close the resource leak of input Stream.
	private static Scanner scn;
	
	//Using throws keyword to remove the below mentioned errors when occurred and exit from the method.
	protected static void AccCre() throws IOException,NumberFormatException,Exception{
		scn = new Scanner(System.in);
		
		System.out.println("Please fill the following credentials and pay the registration fee.\nYour Name(Only alphabets without Spaces):");
		//Taking all the inputs in the code as String.
		String name = scn.nextLine();
		
		//To check if the name is acceptable or not.
		while(true) {
			if(validName(name)==false) {
				System.out.println("Please enter valid name only\nYour Name(Only alphabets without Spaces): ");
				name = scn.nextLine();
			}
			else break;
		}
		String Pin;
		//To check if the password is valid.
		while(true) {
			System.out.println("Enter your password: ");
			Pin = scn.nextLine();
			//checkInt checks if the string is an integer.
			if(checkInt(Pin)==false) {
				System.out.println("Please enter valid number\nEnter your password: ");
				Pin = scn.nextLine();
			}
			else {
				//To conform the password. And 'conPin' is Conforming the Pin.
				System.out.println("Confirm your Pin");
				String conPin = scn.nextLine();
				if(Pin.equals(conPin)) break;
				else {
					System.out.println("Pin doesn't match\nConfirm your Pin");
					conPin = scn.nextLine();
				}
			}
		}
		
		System.out.println("Account type: \n1. Savings account\n2. Current account");
		String index = scn.nextLine();
		while(true) {
			if(index.equals("1") || index.equals("2")) break;
			else {
				System.out.println("Please enter valid number\nAccount type: \n1. Savings account\n2. Current account");
				index = scn.nextLine();
			}
		}
		//AccType- Account Type.
		String AccType;
		if(index.equals("1")) AccType = "Savings";
		else AccType = "Current";
		
		System.out.println("pay rupees 2000 to complete account creation and store 1000 in your account\n\nAmount recived in the box(asking the machine): ");
		//amm-amount taking as a String input.
		String amm = scn.nextLine();
		//Checking if the scanned amount is valid, if not, then asks the black box again.
		while(true) {
			if(checkInt(amm)==false) {
				System.out.println("Error occured scanning again\nAmount recived in the box(asking the machine): ");
				amm = scn.nextLine();
			}
			else break;
		}
		int Ammount = Integer.parseInt(amm);
		if(Ammount<2000) System.out.println("Insufficient amount recived...Account creation failed\nPlease try again and the money deposited is in the box below.");
		else if(Ammount>=2000) {
			int change=Ammount-2000;
			depositStatement d = new depositStatement();
			//a loop to ask the black box what and how many notes are deposited,if it is wrong from what the user has entered then it asks again. 
			while(true) {
				System.out.println("Asking the black box the type and number of denominations");
				if(Ammount==d.depositDenominations()) break;
				else System.out.println("Wrong total asking the information again");
			}
			//To increase the amount of notes
			//creating an object of type depositSatement.
			//Here NoOfX indicates how X denominations are present in ATM
			//noOfX indicates how X denominations are currently being withdrawn or deposited.
			//Camm-to use in the denominations below. and is the amount to be taken from the denominations.
			int Camm=2000;
			d.NoOf2000 = d.NoOf2000+d.noOf2000;
			d.NoOf500 = d.NoOf500+d.noOf500;
			d.NoOf200 = d.NoOf200+d.noOf200;
			d.NoOf100 = d.NoOf100+d.noOf100;
			d.NoOf50 = d.NoOf50+d.noOf50;
			d.NoOf20 = d.NoOf20+d.noOf20;
			d.NoOf10 = d.NoOf10+d.noOf10;
			d.NoOf5 = d.NoOf5+d.noOf5;
			d.NoOf2 = d.NoOf2+d.noOf2;
			d.NoOf1 = d.NoOf1+d.noOf1;
			//now the below code removes 2000 from the provided money.
			if(d.noOf2000>0) d.noOf2000--;
			else if(d.noOf500>0) {
				if(d.noOf500>4) d.noOf500=d.noOf500-4;
				else {
					Camm=2000-(d.noOf500*500);
					d.noOf500=0;
				}
			}
			if(Camm>0 && d.noOf200>0) {
				if(d.noOf200*200>Camm) d.noOf200=d.noOf200-(Camm/200);
				else {
					Camm=Camm-(d.noOf200*200);
					d.noOf200=0;
				}
			}
			if(Camm>0 && d.noOf100>0) {
				if(d.noOf100*100>Camm) d.noOf100=d.noOf100-(Camm/100);
				else {
					Camm=Camm-(d.noOf100*100);
					d.noOf100=0;
				}
			}
			if(Camm>0 && d.noOf50>0) {
				if(d.noOf50*50>Camm) d.noOf50=d.noOf50-(Camm/50);
				else {
					Camm=Camm-(d.noOf50*50);
					d.noOf50=0;
				}
			}
			if(Camm>0 && d.noOf20>0) {
				if(d.noOf20*20>Camm) d.noOf20=d.noOf20-(Camm/20);
				else {
					Camm=Camm-(d.noOf20*20);
					d.noOf20=0;
				}
			}
			if(Camm>0 && d.noOf10>0) {
				if(d.noOf10*10>Camm) d.noOf10=d.noOf10-(Camm/10);
				else {
					Camm=Camm-(d.noOf10*10);
					d.noOf10=0;
				}
			}
			if(Camm>0 && d.noOf5>0) {
				if(d.noOf5*5>Camm) d.noOf5=d.noOf5-(Camm/5);
				else {
					Camm=Camm-(d.noOf5*5);
					d.noOf5=0;
				}
			}
			if(Camm>0 && d.noOf2>0) {
				if(d.noOf2*2>Camm) d.noOf2=d.noOf2-(Camm/2);
				else {
					Camm=Camm-(d.noOf2*2);
					d.noOf2=0;
				}
			}
			if(Camm>0 && d.noOf1>0) {
				if(d.noOf1>Camm) d.noOf1=d.noOf1-(Camm);
				else {
					Camm=Camm-(d.noOf1);
					d.noOf1=0;
				}
			}
			//Returning the extra if the user by chance deposits extra.
			if(Ammount>2000) System.out.println("The money remaining after payment: "+change+" has been dropped in the box below");
			if(d.noOf2000>0) System.out.println("Number of 2000 notes: "+d.noOf2000);
			if(d.noOf500>0) System.out.println("Number of 500 notes: "+d.noOf500);
			if(d.noOf200>0) System.out.println("Number of 200 notes: "+d.noOf200);
			if(d.noOf100>0) System.out.println("Number of 100 notes: "+d.noOf100);
			if(d.noOf50>0) System.out.println("Number of 50 notes: "+d.noOf50);
			if(d.noOf20>0) System.out.println("Number of 20 notes: "+d.noOf20);
			if(d.noOf10>0) System.out.println("Number of 10 notes: "+d.noOf10);
			if(d.noOf5>0) System.out.println("Number of 5 coins: "+d.noOf5);
			if(d.noOf2>0) System.out.println("Number of 2 coins: "+d.noOf2);
			if(d.noOf1>0) System.out.println("Number of 1 coins: "+d.noOf1);
			// d.close() resets all noOfX to zero as withdrawing or depositing action is completed.
			d.close();
			//Storing the same in database.
			Data.setNotesData(d.NoOf2000,d.NoOf500,d.NoOf200,d.NoOf100,d.NoOf50,d.NoOf20,d.NoOf10,d.NoOf5,d.NoOf2,d.NoOf1);
			
		}
		if(Ammount>=2000) {
			int AccNo=0;
			//Randomly choosing an account number that is not already present.
			while(true) {
				AccNo =(int)(Math.random()*90000+10000);
				// converting the account number to String and checking if it is already present or not.
				String s = Integer.toString(AccNo);
				//checks if Account number is already present.
				if(validAccNo(s)==false) break;
			}
			// Creating a User object.
			User user = new User(AccType,AccNo,name,Pin,1000,3);
			//Storing user's data in the DataBase(Data.txt).
			Data.createUserData(user);
			//Giving the account number and details to user who created the account.
			System.out.println("Account succesfully created and the Account details are:\nName: "+user.getName()+"\n"+user.AccType+" Account");
			System.out.println("Account Number: "+user.getAccNo()+"\nBalance: "+user.getAmount());
		}
	}

}

