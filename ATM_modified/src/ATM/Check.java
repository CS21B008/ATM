package ATM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//check class is used to group all types of validity check functions.
//All the Exceptions are thrown by throw keyword.
public class Check {

	protected static boolean validName(String name) {
		int l = name.length();
		// not valid if name length is zero.
		if(l<0) return false;
		for(int i=0;i<l;i++) {
			if(name.charAt(i)<65 || (name.charAt(i)>90 && name.charAt(i)<97) || name.charAt(i)>122) return false;
		}
		return true;
	}
	
	// checks if the given string is an integer.
	protected static boolean checkInt(String s) {
		int l = s.length();
		if(l==0) return false;
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
