//Creating package ATM to call any class in it from other files.
package ATM;

//java.io for file handling.
import java.util.*;
import java.io.*;

public class ATMlogin extends Check {
	private static Scanner scn;
	/*private static Console c;
	 *Use the above variable to keep the password typed a secret in terminal, but not in IDE's, because IDE's don't support io.Console */
	public static void ATMLogin() throws Exception{
		scn = new Scanner(System.in);
		System.out.println("Enter your Account number");
		String AccNo = scn.nextLine();
		//to return, (to home or main method) if the typed account number is not present or it is not of integer type.
		if(checkInt(AccNo)==false || validAccNo(AccNo)==false) System.out.println("\nPlease enter valid Account number");
		else {
			System.out.println("Enter your Pin: ");
			//for secret password typing.
			/*c = System.console();
			char[] CharArr = c.readPassword();
			String Pin = String.valueOf(CharArr);*/
			String Pin = scn.nextLine();
			//checks if the entered pin is correct if not reduces the number of tries left.
			if(validPin(AccNo,Pin)==false) {
				System.out.print("\nIncorrect Pin\nNumber of tries left ");
				System.out.println(TriesLeft(AccNo,true)+"\n\n");
			}
			else if(TriesLeft(AccNo,false)<0) System.out.println("Your Account has been blocked due to frequent wrong login\nPlease contact the bank to unblock");
			else {
				System.out.println("\n1. Check Balance\n2. Withdraw Amount\n3. Deposit Credit\n4. Change pin\n\n5.Return");
				String index = scn.nextLine();
				while(true) {
					while(true) {
						if(index.equals("1") || index.equals("2") || index.equals("3") || index.equals("4") || index.equals("5")) break;
						else {
							System.out.println("Please select the given choices");
							index = scn.nextLine();
						}
					}
					if(index.equals("1")) {
						check(AccNo);
						System.out.println("\n1. Check Balance\n2. Withdraw Amount\n3. Deposit Credit\n4. Change pin\n\n5.Return");
						index = scn.nextLine();
					}
					else if(index.equals("2")) {
						Withdraw(AccNo);
						System.out.println("\n1. Check Balance\n2. Withdraw Amount\n3. Deposit Credit\n4. Change\n\n5.Return");
						index = scn.nextLine();
					}
					else if(index.equals("3")) {
						Deposit(AccNo);
						System.out.println("\n1. Check Balance\n2. Withdraw Amount\n3. Deposit Credit\n4. Change\n\n5.Return");
						index = scn.nextLine();
					}
					else if(index.equals("4")) {
						Change(AccNo);
						System.out.println("\n1. Check Balance\n2. Withdraw Amount\n3. Deposit Credit\n4. Change\n\n5.Return");
						index = scn.nextLine();
					}
					else break;
				}
			}
		}
	}

	private static void Change(String accNo) throws NumberFormatException, IOException, Exception{
		scn = new Scanner(System.in);
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			User u = i.next();
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				System.out.println("Enter your current Pin");
				String pin = scn.nextLine();
				if(validPin(accNo,pin)) {
					System.out.println("Changed Pin:");
					String Cpin = scn.nextLine();
					if(Cpin.equals(pin)) System.out.println("Pin is same as before");
					else if(checkInt(Cpin)==false) System.out.println("The entered value is not valid pin");
					else {
						System.out.println("Confirm your pin:");
						String cpin = scn.nextLine();
						while(true){
							if(cpin.equals(Cpin)) break;
							else {
								System.out.println("Pin is not matching\nEnter again");
								cpin = scn.nextLine();
							}
						}
						u.setPin(cpin);
						System.out.println("Pin has succesfully changed");
						Data.ArrToData(list);
					}
				}
				else {
					System.out.print("\nIncorrect Pin\nNumber of tries left ");
					System.out.println(TriesLeft(accNo,true)+"\n\n");	
				}
			}
		}
	}
	
	private static void Withdraw(String accNo) throws NumberFormatException, IOException, Exception{
		scn = new Scanner(System.in);
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			User u = i.next();
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				System.out.println("Enter the withdraw amount ");
				String debit = scn.nextLine();
				if(checkInt(debit)==false) {
					System.out.println("Enter valid number");
					debit = scn.nextLine();
				} 
				else {
					int amount = Integer.parseInt(debit);
					if(u.getAmount()<amount) System.out.println("Insufficient Balance");
					else {
						withdrawStatement w = new withdrawStatement();
						if(w.withdrawDenominations(amount)) {
							int Amount = u.getAmount()-amount;
							u.setAmount(Amount);
							Data.ArrToData(list);
							w.statement(u,amount);
						}
						else System.out.println("An ERROR has occurred...Insufficient balance in ATM...Balance is unchanged");
						return;
					}
				}
			}
		}
	}

	private static void Deposit(String accNo) throws NumberFormatException, IOException, Exception{
		scn = new Scanner(System.in);
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			User u = i.next();
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				System.out.println("The amount received in the box: ");
				String scredit = scn.nextLine();
				while(true) {
					if(checkInt(scredit)) break;
					else {
						System.out.println("ERROR\nRetreving the amount credited again");
						scredit = scn.nextLine();
					}
				}
				int credit = Integer.parseInt(scredit);
				depositStatement d = new depositStatement();
				int cAmm=0;
				while(cAmm!=credit) {
					cAmm=d.depositDenominations();
					if(cAmm!=credit) System.out.println("\nAn ERROR has ocurred!!!\nAsking the data again\n\n");
				}
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
				d.close();
				Data.setNotesData(d.NoOf2000,d.NoOf500,d.NoOf200,d.NoOf100,d.NoOf50,d.NoOf20,d.NoOf10,d.NoOf5,d.NoOf2,d.NoOf1);
				int amount = u.getAmount();
				amount = amount+credit;
				u.setAmount(amount);
				Data.ArrToData(list);
				d.statement(u,credit);
				return;
			}
		}
	}

	//Here this method can be used in two ways-1. to just check number of tries left(boolean b for reducing tries left) 2. to reduce and return number of tries if pin if false
	private static int TriesLeft(String accNo,boolean b) throws NumberFormatException, IOException, Exception {
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		User u = new User();
		while(i.hasNext()) {
			u = i.next();
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				if(b==true) u.triesLeft--;
				break;
			}
		}
		Data.ArrToData(list);
		return u.triesLeft;
	}

}
