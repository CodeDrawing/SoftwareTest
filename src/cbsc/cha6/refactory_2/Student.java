package cbsc.cha6.refactory_2;

import java.util.ArrayList;

public class Student{
	
	private String name;
	private int bonus=0;
	private ArrayList<Rental> rentals = new ArrayList<Rental>(10);
	
	public Student(String aName){
		name = aName;
	}
	public String getName(){
		return name;
	}
	public ArrayList<Rental> getRentals(){
		return rentals;
	}
	public int getBonus(){
		return bonus;
	}
	public void addBonus(int reward){
		bonus += reward;		
	}
	public void addBook(Book aBook, int days){
		rentals.add(new Rental(aBook, days, this));
	}
	public void addRental(Rental aRental){
		rentals.add(aRental);
	}
	public String returnedMessage(){
		double totalAmount = getTotalFine();// refactor 8.2 
		String message = getRentalList(); 	// refactor 8.1
        totalAmount = discountBonus(totalAmount); // refactor 8.3
        
        message += String.format("缴纳罚金:%.2f元.\n",totalAmount);
        message += "还书奖励:"+getBonus()+"点.\n";
        
        return message;
	}
	private double discountBonus(double totalAmount) {
		while (getBonus()>=7 && totalAmount > 1){
        	addBonus(-7);
        	totalAmount--;
        }
		return totalAmount;
	}
	double getTotalFine(){
		double totalAmount = 0;
        for (Rental aRental:rentals){	
        	totalAmount += aRental.getFine(); 
        }
        return totalAmount;
	}
	String getRentalList(){
		String result="";	
		for (Rental aRental:rentals){
			result += aRental+"\n";
		}
		return result;
	}
	/*
	public int getTotalBonus(){
		int totalAmount = (int)getTotalFine(); 
        for (Rental aRental:rentals){
        	if (aRental.getDaysRented() <= 30){
        		bonus += aRental.getBook().getBonus(); 
        	}	
       	} 	       
        return totalAmount+bonus;
	}	
	*/
}
