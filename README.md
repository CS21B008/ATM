# ATM
## CS21B008_Case Study-2_ATM

## overview

In this case study we use 4 pillars of OOPs and create an ATM in java that can store user data and can create a new user. The user can use the features like checking his/her account balance, withdrawing money, depositing money, changing their Pin. It also have private variable to improve the security of the code.

## Features

Apart from the above mentioned features, this code also does three different file handling, namely 

1. Data.txt, which stores the data of all the users.

2. Notes.txt, which stores the number of denominations currently present in ATM.

3. Date.txt, which stores the date when the program was last run and resets the denominations if it is next day.

This code also does exception handling like it uses try-catch to identify and report the error and also throws keyword to remove the mentioned errors. The error handled are 

1. IOException - exception might occur when using java.io.

2. NumberFormatException - exception might occur when using Integer.parseInt. 

3. Exception - exception might occur when using java.util.

4. ParseException - exception might occur when Date oject is converted to String object.

This code also take all the inputs as String so even when given wrong inputs it will not throw Input Mismatch Error.

## Code

The table shows all the classes

|s.no|class|
|:-----:|:-----------:|
|  1|   [ATM](#atm)|
|  2|   [AccCreate](#acccreate)|
|  3|   [ATMlogin](#atmlogin)|
|  4|   [Check](#check)|
|  5|   [Data](#data)|
|  6|   [depositStatement](#depositstatement)|
|  7|   [withdrawStatement](#withdrawstatement)|
|  8|   [User](#user)|

Apart from these the code also contains an Abstract class named Denominations and an interface Statement.

The description of the classes are as below:

### ATM

It contains only one main method and a private static field of Scanner class. It mainly calls the other functions and is the part that runs indefinetly until code is interrupted. It is first part of user console that lets them to either login to their Account or create a new Account.

### AccCreate

It inherites the class 'Check' which has all the validity check methods. It has one static method named AccCre() of void return type and a private static field of Scanner class. It is an interface that lets user create an Account and store that data in the database(Data.txt). It asks the user their name, Pin and confirms it, Account type, takes 2000rupees and if user accidently drops in extra cash then returns that amount and randomly generates a 5-digit number that is not already present. Then stores that data in the database.

### ATMlogin

It inherites the class 'Check' which has all the validity check methods. It has a private static field of Scanner class and seven methods namely:

1. ATMLogin() - void return type.It is the next part of the user interface console which checks if the entered Account number is present or not and checks if the entered pin correct or not, if not It calls TriesLeft(Account number, true) which drecreases the number of tries and returns the Tries left and resets it if the user has entered correct pin by calling resetTries(Account number). Then it gives the user five options one to Check Balance, two to withdraw amount, three to deposit amount, four to change their Account Pin and five to return to the main Menu(Log out).

2. Change(String) - void return type.It asks the user the previous Pin and if wrong Pin is typed then number of Tries left decreases. Then asks the changed Pin, if they are same it asks again and then confirms it. Then updates the database.

3. check(String) - void return type.It prints the balance available of the given Account.

4. Deposit(String) - void return type.It takes all denomiations and increases the balance of the user and stores the data.

5. resetTries(String) - void return type.It resets the number of tries left by the given Account to three.

6. TriesLeft(String,boolean) - int return type.It decreases the TriesLeft by one if boolean is true else no change. And return the tries left of the given Account.

7.  Withdraw(String) - void return type.It checks if the asked amount is available or not and makes the denomiations and gives the user the mentioned money.

### Check

It is a super class and contains four methods namely:

1. checkInt(String) - boolean return type. It returns true if the given String is an integer, else false.

2. validAccNo(String) - boolean return type. It returns true if the given account number is present in the database, else false.

3. validName(String) - boolean return type. It returns true if the given string contains atleast one character and all characters are alphabets with no whitespaces.

4. validPin(String,String) - boolean return type. It returns true if the Pin is correct for the given Account number, else false.

### Data

It has namely six methods namely:

1. ArrToData(ArrayList<User>) - void return type. It converts the given arraylist to a data file.

2. createUserData(User) - void return type. It appends the data of the single mentioned user to the database.

3. dataToArr(ArrayList<User>) - void return type. It converts the database to array list of Users.

4. getNotesData() - void return type. It converts the "Notes.txt" to the values of the class 'Denominations.'

5. isItNextDay() - void return type. It checks if the current date is different than the stored Date and then resets the number of denominations present in the ATM.

6. setNotesData(int,int,int,int,int,int,int,int,int,int) - void return type. It sets the number of denominations in both database and class to the given values.

### depositStatement 

It inherites the properties of the abstract class [Denominations](#denominations). It also implements the interface [Statement](#statement). It has a a private static field of Scanner class and two constructs, one an empty that calls getNotesData() and the other with ten int arguments that sets the number of denominations to the given values. It has two methods namely:

1. depositDenominations() - int return type. It asks to black box the type and number of denominations and returns the total sum of the input denominations.

2. statement(User,int) - void return type. It Prints the deposit statement of the given User along with how much deposited.

### withdrawStatement

It inherites the properties of the abstract class [Denominations](#denominations). It also implements the interface [Statement](#statement). It has a a private static field of Scanner class and two constructs, one an empty that calls getNotesData() and the other with ten int arguments that sets the number of denominations to the given values. It has two methods namely:

1. withdrawDenominations(int) - boolean return type. It gives one of the possible denominations of the given integer and returns true if that number of denominations is present in the ATM, else false.

2. statement(User,int) - void return type. It prints the withdraw statement of the given user along with how much money withdrawn.

### User

It contains the following variable fields:

1. private int Account number

2. private name of the user

3. private String Pin

4. private int balance available

5. String Account type

6. int number of tries left.

It contains an empty and a constructor with arguments. It has getters and setters for all the private variables.

### Statement

It contains an abstract method statement that is implemented by [depositStatement](#depositstatement) and [withdrawStatement](#withdrawstatement) classes.

### Denominations

It is inherited by [depositStatement](#depositstatement) and [withdrawStatement](#withdrawstatement). It has ten field variables of type NoOfX (X an integer) which represent number X valued denominations present in the ATM and ten other variable of type noOfX which represent number of X valued denominations that are currently in the process of getting deposited or withdrawn. It has two constructs, one an empty that calls getNotesData() and the other with ten int arguments that sets the number of denominations to the given values. It has four methods namely:

1. checkamm() - boolean return type. It checks whether the number of denominations currently under processing is less than number of denominations present in ATM and reduces the denominations by the values of processing denomiations then returns True, else no changes and return false.

2. close() - void return type. It resets the current processing denominations to zero.

3. denominations(int) - void return type. It provides one of the possible denominations to all the 'noOfX'.

4. denominations(String) - void return type. It does the same as denominations(int) but for string argument.

### OOPs concepts used :

1. Encapsulation - all the files are grouped by the package ATM.

2. Inheritance - withdrawStatement and depositStatement inherit the class Denominations and ATMlogin and AccCreate inherit the class Check.

3. Abstraction - Abstraction is achieved by the abstract class 'Denominations' and interface 'Statement'.

4. Polymorphism - Here polymorphism is achieved by having same method name(denominations) but different arguments.

### Access Specifiers used

public, private, protected are used for methods and variables.



### Arthur 
- A. Shree Balaji:[@
