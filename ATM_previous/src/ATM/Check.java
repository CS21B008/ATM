package ATM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Check {

	protected static boolean validName(String name) {
		int l = name.length();
		for(int i=0;i<l;i++) {
			if(name.charAt(i)<65 || (name.charAt(i)>90 && name.charAt(i)<97) || name.charAt(i)>122) return false;
		}
		return true;
	}
	
	protected static void check(String accNo) throws NumberFormatException, IOException, Exception{
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			User u = i.next();
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				System.out.println(u.getAmount());
			}
		}
	}
	
	protected static boolean checkInt(String s) {
		int l = s.length();
		for(int i=0;i<l;i++) {
			if(s.charAt(i)<48 || s.charAt(i)>57) return false;
		}
		return true;
	}
	
	protected static boolean validPin(String accNo, String pin) throws NumberFormatException, IOException, Exception {
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			User u = i.next();
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				if(u.getPin().equals(pin)) return true;
				else return false;
			}
		}
		return false;
	}
	
	protected static boolean validAccNo(String accNo) throws NumberFormatException, IOException, Exception {
		ArrayList<User> list = new ArrayList<User>();
		Data.dataToArr(list);
		Iterator<User> i = list.iterator();
		while(i.hasNext()) {
			User u = i.next();
			if(u.getAccNo()==Integer.parseInt(accNo)) {
				return true;
			}
		}
		return false;
	}
}
