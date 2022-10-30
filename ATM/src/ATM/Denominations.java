package ATM;

import java.util.*;
import java.io.*;

abstract class Denominations{

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
	int noOf2000=0;
	int noOf500=0;
	int noOf200=0;
	int noOf100=0;
	int noOf50=0;
	int noOf20=0;
	int noOf10=0;
	int noOf5=0;
	int noOf2=0;
	int noOf1=0;
	//No Arguments constructor to create objects like "new withdrawStatement()".if not created it will go to the other mentioned constructor and we can't use an empty argument constructor. 
	Denominations() throws IOException,Exception,NumberFormatException{
		Data.getNotesData();
	}
	Denominations(int NoOf2000,int NoOf500,int NoOf200,int NoOf100,int NoOf50,int NoOf20,int NoOf10,int NoOf5,int NoOf2,int NoOf1){
		this.NoOf2000 = NoOf2000;
		this.NoOf500 = NoOf500;
		this.NoOf200 = NoOf200;
		this.NoOf100 = NoOf100;
		this.NoOf50 = NoOf50;
		this.NoOf20 = NoOf20;
		this.NoOf10 = NoOf10;
		this.NoOf5 = NoOf5;
		this.NoOf2 = NoOf2;
		this.NoOf1 = NoOf1;
	}
	
	protected  void denominations(String s) {
		if(Check.checkInt(s)) denominations(Integer.parseInt(s));
		else System.out.println("ERROR");
	}
	
	protected  void denominations(int i) {
		int amm = i;
		noOf2000 = amm/2000;
		amm = amm-(noOf2000*2000);
		noOf500 = amm/500;
		amm = amm-(noOf500*500);
		noOf200 = amm/200;
		amm = amm-(noOf200*200);
		noOf100 = amm/100;
		amm = amm-(noOf100*100);
		noOf50 = amm/50;
		amm = amm-(noOf50*50);
		noOf20 = amm/20;
		amm = amm-(noOf20*20);
		noOf10 = amm/10;
		amm = amm-(noOf10*10);
		noOf5 = amm/5;
		amm = amm-(noOf5*5);
		noOf2 = amm/2;
		amm = amm-(noOf2*2);
		noOf1 = amm;
	}
	
	protected boolean checkamm() throws IOException{
		if(NoOf2000<noOf2000) return false;
		else NoOf2000 = NoOf2000-noOf2000;
		if(NoOf500<noOf500) return false;
		else NoOf500 = NoOf500-noOf500;
		if(NoOf200<noOf200) return false;
		else NoOf200 = NoOf200-noOf200;
		if(NoOf100<noOf100) return false;
		else NoOf100 = NoOf100-noOf100;
		if(NoOf50<noOf50) return false;
		else NoOf50 = NoOf50-noOf50;
		if(NoOf20<noOf20) return false;
		else NoOf20 = NoOf20-noOf20;
		if(NoOf10<noOf10) return false;
		else NoOf10 = NoOf10-noOf10;
		if(NoOf5<noOf5) return false;
		else NoOf5 = NoOf5-noOf5;
		if(NoOf2<noOf2) return false;
		else NoOf2 = NoOf2-noOf2;
		if(NoOf1<noOf1) return false;
		else NoOf1 = NoOf1-noOf1;
		Data.setNotesData(NoOf2000, NoOf500, NoOf200, NoOf100, NoOf50, NoOf20, NoOf10, NoOf5, NoOf2, NoOf1);
		return true;
	}
	
	protected void close() {
		noOf2000=0;
		noOf500=0;
		noOf200=0;
		noOf100=0;
		noOf50=0;
		noOf20=0;
		noOf10=0;
		noOf5=0;
		noOf2=0;
		noOf1=0;
	}
}

interface Statement{
	public void statement(User u, int i);
}
class withdrawStatement extends Denominations implements Statement{

	withdrawStatement() throws IOException,Exception,NumberFormatException{
		Data.getNotesData();
	}
	withdrawStatement(int NoOf2000, int NoOf500, int NoOf200, int NoOf100, int NoOf50, int NoOf20, int NoOf10,int NoOf5, int NoOf2, int NoOf1) {
		super(NoOf2000, NoOf500, NoOf200, NoOf100, NoOf50, NoOf20, NoOf10, NoOf5, NoOf2, NoOf1);
	}

	public void statement(User u, int withdraw) {
		System.out.println("Name: "+u.getName()+"\nAccount number: "+u.getAccNo()+"\nAmount withdrawn: "+withdraw+"\nBalance: "+u.getAmount());
	}
	
	public boolean withdrawDenominations(int i) throws IOException{
		super.denominations(i);
		if(checkamm()) {
			System.out.println("Amount in the box below");
			if(noOf2000>0) System.out.println("Number of 2000 notes: "+noOf2000);
			if(noOf500>0) System.out.println("Number of 500 notes: "+noOf500);
			if(noOf200>0) System.out.println("Number of 200 notes: "+noOf200);
			if(noOf100>0) System.out.println("Number of 100 notes: "+noOf100);
			if(noOf50>0) System.out.println("Number of 50 notes: "+noOf50);
			if(noOf20>0) System.out.println("Number of 20 notes: "+noOf20);
			if(noOf10>0) System.out.println("Number of 10 notes: "+noOf10);
			if(noOf5>0) System.out.println("Number of 5 coins: "+noOf5);
			if(noOf2>0) System.out.println("Number of 2 coins: "+noOf2);
			if(noOf1>0) System.out.println("Number of 1 coins: "+noOf1);
			System.out.println("Total: "+i);
		}
		close();
		return checkamm();
	}
	
}
class depositStatement extends Denominations implements Statement{

	depositStatement() throws IOException,Exception,NumberFormatException{
		Data.getNotesData();
	}
	depositStatement(int NoOf2000, int NoOf500, int NoOf200, int NoOf100, int NoOf50, int NoOf20, int NoOf10, int NoOf5,int NoOf2, int NoOf1) {
		super(NoOf2000, NoOf500, NoOf200, NoOf100, NoOf50, NoOf20, NoOf10, NoOf5, NoOf2, NoOf1);
	}

	private static Scanner scn;
	
	 public void statement(User u, int deposit) {
		 System.out.println("Name: "+u.getName()+"\nAccount number: "+u.getAccNo()+"\nAmount deposited: "+deposit+"\nBalance: "+u.getAmount());
	 }
	 
	 public int depositDenominations() throws Exception{
		 scn = new Scanner(System.in);
		 System.out.println("Number of 2000 notes: ");
		 String snoOf2000 = scn.nextLine();
		 while(Check.checkInt(snoOf2000)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf2000 = scn.nextLine();
		 }
		 noOf2000=Integer.parseInt(snoOf2000);
		 
		 System.out.println("Number of 500 notes: ");
		 String snoOf500 = scn.nextLine();
		 while(Check.checkInt(snoOf500)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf500 = scn.nextLine();
		 }
		 noOf500=Integer.parseInt(snoOf500);
		 
		 System.out.println("Number of 200 notes: ");
		 String snoOf200 = scn.nextLine();
		 while(Check.checkInt(snoOf200)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf200 = scn.nextLine();
		 }
		 noOf200=Integer.parseInt(snoOf200);
		 
		 System.out.println("Number of 100 notes: ");
		 String snoOf100 = scn.nextLine();
		 while(Check.checkInt(snoOf100)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf100 = scn.nextLine();
		 }
		 noOf100=Integer.parseInt(snoOf100);
		 
		 System.out.println("Number of 50 notes: ");
		 String snoOf50 = scn.nextLine();
		 while(Check.checkInt(snoOf50)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf50 = scn.nextLine();
		 }
		 noOf50=Integer.parseInt(snoOf50);
		 
		 System.out.println("Number of 20 notes: ");
		 String snoOf20 = scn.nextLine();
		 while(Check.checkInt(snoOf20)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf20 = scn.nextLine();
		 }
		 noOf20=Integer.parseInt(snoOf20);
		 
		 System.out.println("Number of 10 notes: ");
		 String snoOf10 = scn.nextLine();
		 while(Check.checkInt(snoOf10)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf10 = scn.nextLine();
		 }
		 noOf10=Integer.parseInt(snoOf10);
		 
		 System.out.println("Number of 5 coins: ");
		 String snoOf5 = scn.nextLine();
		 while(Check.checkInt(snoOf5)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf5 = scn.nextLine();
		 }
		 noOf5=Integer.parseInt(snoOf5);
		 
		 System.out.println("Number of 2 coins: ");
		 String snoOf2 = scn.nextLine();
		 while(Check.checkInt(snoOf2)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf2 = scn.nextLine();
		 }
		 noOf2=Integer.parseInt(snoOf2);
		 
		 System.out.println("Number of 1 coins: ");
		 String snoOf1 = scn.nextLine();
		 while(Check.checkInt(snoOf1)==false) {
			 System.out.println("Invalid Input...Enter again");
			 snoOf1 = scn.nextLine();
		 }
		 noOf1=Integer.parseInt(snoOf1);
		 
		 int total=(2000*noOf2000)+(500*noOf500)+(200*noOf200)+(100*noOf100)+(50*noOf50)+(20*noOf20)+(10*noOf10)+(5*noOf5)+(2*noOf2)+(1*noOf1);
		 
		 return total;
	 }
}
