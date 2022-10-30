//Creating package ATM to call any class in it from other files.
package ATM;

//java.io for file handling.
import java.util.*;
import java.io.*;

//it extends check and uses all its validity check functions.
//All the Exceptions are thrown by throw keyword.
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
			if(TriesLeft(AccNo,false)>0) {
				if(validPin(AccNo,Pin)==false) {
					System.out.print("\nIncorrect Pin\nNumber of tries left ");
					//Here the method "TriesLeft(...,true)" decreases the number of tries and returns the number of tries left.
					System.out.println(TriesLeft(AccNo,true)+"\n\n");
				}
				//Resets the number of tries left after typing correctly.
				else resetTries(AccNo);
			}
			else if(TriesLeft(AccNo,false)<=0) System.out.println("Your Account has been blocked due to frequent wrong login\nPlease contact the bank to unblock");
			if(validPin(AccNo,Pin)==true) {
				System.out.println("\n1. Check Balance\n2. Withdraw Amount\n3. Deposit Credit\n4. Change pin\n\n5.Return");
				String index = scn.nextLine();
				//Outer loop to run the same code until return option is chosen.
				while(true) {
					//a loop to check if the entered values are correct option.
					while(true) {
						if(index.equals("1") || index.equals("2") || index.equals("3") || index.equals("4") || index.equals("5")) break;
						else {
							System.out.println("Please select the given choices");
							System.out.println("\n1. Check Balance\n2. Withdraw Amount\n3. Deposit Credit\n4. Change pin\n\n5.Return");
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
	
	//checks the balance of the given account number and prints it.
	protected static void check(String accNo) throws NumberFormatException, IOException, Exception{
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			User u = i.next();
			//using getter to get a private variable value.
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				System.out.println("The balance: "+u.getAmount());
			}
		}
	}

	//it changes the pin of the user.
	private static void Change(String accNo) throws NumberFormatException, IOException, Exception{
		scn = new Scanner(System.in);
		//creating an array list to temporarily store data from '.txt' file.
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
					// Cpin- Changed Pin.
					String Cpin = scn.nextLine();
					//the below two lines checks if pin is different from before or not valid.
					if(Cpin.equals(pin)) System.out.println("Pin is same as before");
					else if(checkInt(Cpin)==false) System.out.println("The entered value is not valid pin");
					else {
						System.out.println("Confirm your pin:");
						//cpin- Confirmation Pin.
						String cpin = scn.nextLine();
						while(true){
							//if changed pin is equals to confirmed pin.
							if(cpin.equals(Cpin)) break;
							else {
								System.out.println("Pin is not matching\nEnter again");
								System.out.println("Confirm your pin:");
								cpin = scn.nextLine();
							}
						}
						// setting the changed pin by getter and setter method as pin is a private variable of User class.
						u.setPin(cpin);
						System.out.println("Pin has succesfully changed");
						//converting array list to data.
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
						//creating an object of type withdrawStatement to create denominations when amount is withdrawn.
						withdrawStatement w = new withdrawStatement();
						//withdrawDenominations method returns true if there is sufficient number of notes in the ATM.
						if(w.withdrawDenominations(amount)) {
							int Amount = u.getAmount()-amount;
							u.setAmount(Amount);
							Data.ArrToData(list);
							//generating a statement of withdrawal
							w.statement(u,amount);
						}
						else System.out.println("An ERROR has occurred...Insufficient balance in ATM...Your Balance is unchanged");
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
				//scredit-credit amount as String.
				String scredit = scn.nextLine();
				while(true) {
					if(checkInt(scredit)) break;
					else {
						System.out.println("ERROR\nRetreving the amount credited again");
						System.out.println("The amount received in the box: ");
						scredit = scn.nextLine();
					}
				}
				//changing String to int for calculations.
				int credit = Integer.parseInt(scredit);
				//creating depositStatement object to use depositDenominations method which handles denominations during deposit process.
				depositStatement d = new depositStatement();
				//creating an checking amount variable,cAmm
				int cAmm=0;
				while(cAmm!=credit) {
					//this method asks the black box of type and number of denominations and returns its total.
					cAmm=d.depositDenominations();
					if(cAmm!=credit) System.out.println("\nAn ERROR has ocurred!!!\nAsking the data again\nThe total should be "+credit+"\n");
				}
				//increasing the number of denominations in the ATM.
				//NoOfX-current number of X valued denominations
				//noOfX-the number of X valued denominations undergoing the process of crediting.
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
				//storing the Notes data in the database.
				Data.setNotesData(d.NoOf2000,d.NoOf500,d.NoOf200,d.NoOf100,d.NoOf50,d.NoOf20,d.NoOf10,d.NoOf5,d.NoOf2,d.NoOf1);
				//d.close resets the number of denominations undergoing any process.
				d.close();
				//editing the user balance.
				int amount = u.getAmount();
				amount = amount+credit;
				u.setAmount(amount);
				Data.ArrToData(list);
				//printing the deposit Statement.
				d.statement(u,credit);
				return;
			}
		}
	}

	//Here this method can be used in two ways- 1. to just check number of tries left(boolean b for reducing tries left) 
	//2. to reduce and return number of tries if pin if false
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

	//this method resets the number of tries.
	private static void resetTries(String accNo) throws NumberFormatException, IOException, Exception{
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		User u = new User();
		while(i.hasNext()) {
			u = i.next();
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				u.triesLeft=3;
				break;
			}
		}
		Data.ArrToData(list);
		return;
	}
}
