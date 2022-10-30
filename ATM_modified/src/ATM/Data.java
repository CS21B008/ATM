package ATM;

import java.io.*;
import java.text.*;
import java.util.*;

//A class to do the file handling 
//It is requested to give access to the below mentioned files for the code to run without errors.
//It is requested to not change any data by opening the files.
//All the Exceptions are thrown by throw keyword.
public class Data {
	
	//Creates a user data and stores it in the database.
	protected static void createUserData(User user) throws IOException {
		//creates a object by the name data and type .txt
		File file = new File("data.txt");
		//this method returns true if the file is not present and creates a file else returns false and doesn't change the file.
		file.createNewFile();
		//to append we use this method with true as one of the argument
		FileWriter Fwrite = new FileWriter(file,true);
		Fwrite.write(user.AccType+"\n"+user.getAccNo()+"\n"+user.getName()+"\n"+user.getPin()+"\n"+user.getAmount()+"\n"+user.triesLeft+"\n\n");
		Fwrite.close();
	}
	
	//converts the data into an array list.
	protected static void dataToArr(ArrayList<User> list) throws IOException,Exception,NumberFormatException {
		File file = new File("data.txt");
		file.createNewFile();
		String Acctype,name,pin;
		int amount,accNo;
		int notries;
		// the fileScan object is used to read the data of the file.
		Scanner fileScan = new Scanner(file);
		while(fileScan.hasNext()) {
			Acctype = fileScan.next();
			accNo = Integer.parseInt(fileScan.next());
			name = fileScan.next();
			pin = fileScan.next();
			amount = Integer.parseInt(fileScan.next());
			notries = Integer.parseInt(fileScan.next());
			User user = new User(Acctype,accNo,name,pin,amount,notries);
			list.add(user);
		}
		fileScan.close();
	}
	
	//converts the array list to database.
	protected static void ArrToData(ArrayList<User> list) throws IOException,Exception,NumberFormatException {
		File file = new File("data.txt");
		file.createNewFile();
		//creates a file writer that overrides(by false as a argument) and write nothing and close it, so as to remove all the data present in it.
		new FileWriter(file,false).close();
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			//adds each user data.
			createUserData(i.next());
		}
	}
	
	//this method takes data from the files and change the class data accordingly.
	protected static void getNotesData() throws IOException,Exception,NumberFormatException {
		int NoOf2000 = 2000;
		int NoOf500 = 2000;
		int NoOf200 = 2000;
		int NoOf100 = 1000;
		int NoOf50 = 1000;
		int NoOf20 = 1000;
		int NoOf10 = 1000;
		int NoOf5 = 1000;
		int NoOf2 = 1000;
		int NoOf1 = 1000;
		File Notesfile = new File("Notes.txt");
		Notesfile.createNewFile();
		Scanner fileScan = new Scanner(Notesfile);
		NoOf2000=Integer.parseInt(fileScan.next());
		NoOf500=Integer.parseInt(fileScan.next());
		NoOf200=Integer.parseInt(fileScan.next());
		NoOf100=Integer.parseInt(fileScan.next());
		NoOf50=Integer.parseInt(fileScan.next());
		NoOf20=Integer.parseInt(fileScan.next());
		NoOf10=Integer.parseInt(fileScan.next());
		NoOf5=Integer.parseInt(fileScan.next());
		NoOf2=Integer.parseInt(fileScan.next());
		NoOf1=Integer.parseInt(fileScan.next());	
		fileScan.close();
		//to change class data by constructor
		//using withdrawStatement class because Denominations is abstract and can't crate it's object.
		new withdrawStatement(NoOf2000,NoOf500,NoOf200,NoOf100,NoOf50,NoOf20,NoOf10,NoOf5,NoOf2,NoOf1);
	}
	
	//this sets the data file of Notes by the giving values.
	protected static void setNotesData(int NoOf2000,int NoOf500,int NoOf200,int NoOf100,int NoOf50,int NoOf20,int NoOf10,int NoOf5,int NoOf2,int NoOf1) throws IOException{
		File Notesfile = new File("Notes.txt");
		Notesfile.createNewFile();
		FileWriter Fwrite = new FileWriter(Notesfile);
		Fwrite.write(NoOf2000+"\n"+NoOf500+"\n"+NoOf200+"\n"+NoOf100+"\n"+NoOf50+"\n"+NoOf20+"\n"+NoOf10+"\n"+NoOf5+"\n"+NoOf2+"\n"+NoOf1);
		Fwrite.close();
	}
	
	//this methods checks the data file called date and compare it with current date.
	//and if  the the dates are not equals it will reset the file to current date and resets the number of denominations
	protected static void isItNextDay() throws IOException,ParseException{
		File Fdate = new File("date.txt");
		File Notesfile = new File("Notes.txt");
		//sets the format of Date.
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		//if Date file is not present it creates it and stores the current date.
		if(Fdate.createNewFile()) {
			// this object if the constructor arguments are not present it sets the Date to current.
			 Date nowDate = new Date();
			 //converts the Date object to a string.
			 String Date = dateFormatter.format(nowDate);
			 FileWriter Fwrite = new FileWriter(Fdate);
			 Fwrite.write(Date);
			 Fwrite.close();
		}
		else {
			Scanner Fscan = new Scanner(Fdate);
			String Sdateinitial = Fscan.next();
			Fscan.close();
			Date datenow = new Date();
			String Sdatenow = dateFormatter.format(datenow);
			//if the file date and current date are not equal or the Notes file is not present then it resets the number of denominations.
			if(Sdateinitial.equals(Sdatenow)==false || Notesfile.createNewFile()) {
				FileWriter Fwrite = new FileWriter(Fdate);
				Fwrite.write(Sdatenow);
				Fwrite.close();
				setNotesData(2000,2000,2000,1000,1000,1000,1000,1000,1000,1000);
			}
		}
	}
}