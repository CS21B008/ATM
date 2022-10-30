package ATM;

import java.io.*;
import java.text.*;
import java.util.*;

public class Data {
	
	protected static void createUserData(User user) throws IOException {
		File file = new File("data.txt");
		file.createNewFile();
		FileWriter Fwrite = new FileWriter(file,true);
		Fwrite.write(user.AccType+"\n"+user.getAccNo()+"\n"+user.getName()+"\n"+user.getPin()+"\n"+user.getAmount()+"\n"+user.triesLeft+"\n\n");
		Fwrite.close();
	}
	
	protected static void dataToArr(ArrayList<User> list) throws IOException,Exception,NumberFormatException {
		File file = new File("data.txt");
		file.createNewFile();
		String Acctype,name,pin;
		int amount,accNo;
		int notries;
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
	
	protected static void ArrToData(ArrayList<User> list) throws IOException,Exception,NumberFormatException {
		File file = new File("data.txt");
		file.createNewFile();
		new FileWriter(file,false).close();
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			createUserData(i.next());
		}
	}
	
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
		new withdrawStatement(NoOf2000,NoOf500,NoOf200,NoOf100,NoOf50,NoOf20,NoOf10,NoOf5,NoOf2,NoOf1);
	}
	
	protected static void setNotesData(int NoOf2000,int NoOf500,int NoOf200,int NoOf100,int NoOf50,int NoOf20,int NoOf10,int NoOf5,int NoOf2,int NoOf1) throws IOException{
		File Notesfile = new File("Notes.txt");
		Notesfile.createNewFile();
		FileWriter Fwrite = new FileWriter(Notesfile);
		Fwrite.write(NoOf2000+"\n"+NoOf500+"\n"+NoOf200+"\n"+NoOf100+"\n"+NoOf50+"\n"+NoOf20+"\n"+NoOf10+"\n"+NoOf5+"\n"+NoOf2+"\n"+NoOf1);
		Fwrite.close();
	}
	
	protected static void isItNextDay() throws IOException,ParseException{
		File Fdate = new File("date.txt");
		File Notesfile = new File("Notes.txt");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		if(Fdate.createNewFile()) {
			 Date nowDate = new Date();
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
			if(Sdateinitial.equals(Sdatenow)==false || Notesfile.createNewFile()) {
				FileWriter Fwrite = new FileWriter(Fdate);
				Fwrite.write(Sdatenow);
				Fwrite.close();
				setNotesData(2000,2000,2000,1000,1000,1000,1000,1000,1000,1000);
			}
		}
	}
}