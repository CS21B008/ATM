package ATM;

public class User {
	String AccType;
	private int AccNo;
	private String name;
	private String Pin;
	private int Amount=500;
	int triesLeft=3;
	User(String AccType, int AccNo, String name, String Pin, int amount, int triesLeft){
		this.AccType = AccType;
		this.AccNo = AccNo;
		this.name = name;
		this.Pin = Pin;
		this.Amount = amount;
		this.triesLeft = triesLeft;
	}
	User(){}
	public int getAccNo() {
		return AccNo;
	}
	public void setAccNo(int accNo) {
		AccNo = accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPin() {
		return Pin;
	}
	public void setPin(String pin) {
		Pin = pin;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
}