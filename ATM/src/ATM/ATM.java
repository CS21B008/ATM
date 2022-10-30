//Creating package ATM to call any class in it from other files.
package ATM;

import java.util.Scanner;
import java.io.IOException;

public class ATM {
	//declaring Scanner as a field to close the resource leak of input Stream.
	private static Scanner scn;

	public static void main(String[] args) throws IOException,Exception{
		scn = new Scanner(System.in);
		//Check if it's next day and reset's the total denominations.
		Data.isItNextDay();
		//The code always runs until you close the console
		while(true) {
			//To not stop code from running when an error is occurred.
			try {
				System.out.println("...WELCOME to IITTP ATM...\n\n1. Login in\n2. Create a new account");
				//Taking all the inputs of the code from the user as a String so that when user press any key instead of numbers the code works.
				String consoleIndex1  = scn.nextLine();
				
				//To check if the entered input is valid or not.
				while(true) {
					if(consoleIndex1.equals("1") || consoleIndex1.equals("2")) break;
					else{
						System.out.println("Please enter valid option\n");
						System.out.println("...WELCOME to IITTP ATM...\n\n1. Login in\n2. Create a new account");
						consoleIndex1  = scn.nextLine();
					}
				}
				
				if(consoleIndex1.equals("1")) {
					while(true) {
						System.out.println("\n1. Account \n\n2. Home");
						String consoleIndex2 = scn.nextLine();
						
						//To check validity of input.
						while(true) {
							if(consoleIndex2.equals("1") || consoleIndex2.equals("2")) break;
							else {
								System.out.println("Please enter valid option\n");
								System.out.println("\n1. Account \n\n2. Home");
								consoleIndex2  = scn.nextLine();
							}
						}
						
						if(consoleIndex2.equals("1")) {
							ATMlogin.ATMLogin();
						}
						else break;
					}
				}
				else {
					while(true) {
						System.out.println("\n1. Create \n\n2. Home");
						String consoleIndex3 = scn.nextLine();
						while(true) {
							if(consoleIndex3.equals("1") || consoleIndex3.equals("2")) break;
							else {
								System.out.println("Please enter valid option\n");
								System.out.println("\n1. Create \n\n2. Home");
								consoleIndex3  = scn.nextLine();
							}
						}
						if(consoleIndex3.equals("1")) {
							AccCreate.AccCre();
						}
						else break;
					}
				}
			}catch(Exception e) {
				//To report what error has occurred.
				System.out.println("ERROR");
				e.printStackTrace();
			}
		}
	}
}
